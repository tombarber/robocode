package org.example.tombarber.robocode.components;

import org.decimal4j.util.DoubleRounder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class TargetingSystemTest {

    private static final String ENEMY_ROBOT_1 = "ENEMY_ROBOT_1";
    private static final String ENEMY_ROBOT_2 = "ENEMY_ROBOT_2";

    private TargetingSystem targetingSystem;
    @Mock(lenient = true)
    private ScannedRobotEvent scannedRobotEvent;
    @Mock
    private AdvancedRobot advancedRobot;

    @BeforeEach
    void setupTestRobot() {
        targetingSystem = new TargetingSystem(advancedRobot);

        given(scannedRobotEvent.getEnergy()).willReturn(0.0);
        given(scannedRobotEvent.getBearing()).willReturn(0.0);
        given(scannedRobotEvent.getDistance()).willReturn(0.0);
        given(scannedRobotEvent.getHeading()).willReturn(0.0);
        given(scannedRobotEvent.getVelocity()).willReturn(0.0);
        given(scannedRobotEvent.getName()).willReturn("");
        given(scannedRobotEvent.isSentryRobot()).willReturn(false);
    }

    @Test
    void isMountedToARobot() {
        assertThat(new TargetingSystem(advancedRobot).getRobot()).isEqualTo(advancedRobot);
    }

    @Test
    void cannotBeCreatedWithNullRobot() {
        String message = assertThrows(NullPointerException.class, () -> new TargetingSystem(null)).getMessage();
        assertThat(message).isEqualTo("robot cannot be null");
    }

    @Test
    void willTargetFirstDiscoveredEnemy() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        targetingSystem.onScannedRobot(scannedRobotEvent);
        assertThat(targetingSystem.getCurrentTarget().get().getName()).isEqualTo(ENEMY_ROBOT_1);
    }

    @Test
    void willContinueToTargetCurrentTargetIfDetectedAgain() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        given(scannedRobotEvent.getEnergy()).willReturn(1.0);
        targetingSystem.onScannedRobot(scannedRobotEvent);
        given(scannedRobotEvent.getEnergy()).willReturn(2.0);
        targetingSystem.onScannedRobot(scannedRobotEvent);
        assertThat(targetingSystem.getCurrentTarget().get().getName()).isEqualTo(ENEMY_ROBOT_1);
        assertThat(targetingSystem.getCurrentTarget().get().getEnergy()).isEqualTo(2.0);
    }

    @Test
    void willSwitchToCloserTarget() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        given(scannedRobotEvent.getDistance()).willReturn(2.0);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_2);
        given(scannedRobotEvent.getDistance()).willReturn(1.0);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        assertThat(targetingSystem.getCurrentTarget().get().getName()).isEqualTo(ENEMY_ROBOT_2);
    }

    @Test
    void willNotSwitchToFurtherTarget() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_2);
        given(scannedRobotEvent.getDistance()).willReturn(1.0);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        given(scannedRobotEvent.getDistance()).willReturn(2.0);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        assertThat(targetingSystem.getCurrentTarget().get().getName()).isEqualTo(ENEMY_ROBOT_2);
    }

    @Test
    void willAbandonTargetIfDestroyed() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        targetingSystem.onRobotDeath(new RobotDeathEvent(ENEMY_ROBOT_1));

        assertThat(targetingSystem.getCurrentTarget().isPresent()).isFalse();
    }

    @Test
    void willRetainTargetIfAnotherRobotIsDestroyed() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        targetingSystem.onRobotDeath(new RobotDeathEvent(ENEMY_ROBOT_2));

        assertThat(targetingSystem.getCurrentTarget().get().getName()).isEqualTo(ENEMY_ROBOT_1);
    }

    @Test
    void keepsTrackOfAllDetectedEnemies() {
        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_1);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        given(scannedRobotEvent.getName()).willReturn(ENEMY_ROBOT_2);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        assertThat(targetingSystem.getKnownEnemies()).hasSize(2);
        assertThat(targetingSystem.getKnownEnemies().get(ENEMY_ROBOT_1).getName()).isEqualTo(ENEMY_ROBOT_1);
        assertThat(targetingSystem.getKnownEnemies().get(ENEMY_ROBOT_2).getName()).isEqualTo(ENEMY_ROBOT_2);
    }

    // TODO: Add more tests
    @ParameterizedTest
    @CsvSource({
            "0.0, 1.0, 2.0, 35, 4.9, 3.8, 6.0"
    })
    void calculatesCurrentPositionOfTargetWhenDetected(double robotHeading, double robotX, double robotY, double enemyBearing, double enemyDistance, double expectedX, double expectedY) {
        given(scannedRobotEvent.getBearing()).willReturn(enemyBearing);
        given(scannedRobotEvent.getDistance()).willReturn(enemyDistance);
        given(advancedRobot.getX()).willReturn(robotX);
        given(advancedRobot.getY()).willReturn(robotY);
        given(advancedRobot.getHeading()).willReturn(robotHeading);
        targetingSystem.onScannedRobot(scannedRobotEvent);

        assertThat(DoubleRounder.round(targetingSystem.getCurrentTarget().get().getX(), 1)).isEqualTo(expectedX);
        assertThat(DoubleRounder.round(targetingSystem.getCurrentTarget().get().getY(), 1)).isEqualTo(expectedY);
    }
}
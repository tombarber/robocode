package org.example.tombarber.robocode.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    @Mock
    private ScannedRobotEvent scannedRobotEvent;
    @Mock
    private AdvancedRobot advancedRobot;

    @BeforeEach
    void setupTestRobot() {
        targetingSystem = new TargetingSystem(advancedRobot);
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
}
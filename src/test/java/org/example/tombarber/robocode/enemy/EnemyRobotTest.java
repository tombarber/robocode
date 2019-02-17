package org.example.tombarber.robocode.enemy;

import org.junit.jupiter.api.Test;
import robocode.ScannedRobotEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EnemyRobotTest {

    @Test
    void canCreateWithScannedEnemyEvent() {
        new EnemyRobot(mock(ScannedRobotEvent.class));
    }

    @Test
    void requiresScannedEnemyEvent() {
        final String message = assertThrows(NullPointerException.class, () -> new EnemyRobot(null)).getMessage();
        assertThat(message).isEqualTo("scannedRobotEvent must not be null");
    }

    @Test
    void enemyRobotPopulatedWithScannedEventValues() {

        final double energy = 1.0;
        final double bearing = 2.0;
        final double distance = 3.0;
        final double heading = 4.0;
        final double velocity = 5.0;
        final String name = "SomeBot";
        final boolean isSentry = true;

        ScannedRobotEvent scannedRobotEvent = mock(ScannedRobotEvent.class);

        given(scannedRobotEvent.getEnergy()).willReturn(energy);
        given(scannedRobotEvent.getBearing()).willReturn(bearing);
        given(scannedRobotEvent.getDistance()).willReturn(distance);
        given(scannedRobotEvent.getHeading()).willReturn(heading);
        given(scannedRobotEvent.getVelocity()).willReturn(velocity);
        given(scannedRobotEvent.getName()).willReturn(name);
        given(scannedRobotEvent.isSentryRobot()).willReturn(isSentry);

        EnemyRobot enemyRobot = new EnemyRobot(scannedRobotEvent);

        assertThat(enemyRobot.getEnergy()).isEqualTo(energy);
        assertThat(enemyRobot.getBearing()).isEqualTo(bearing);
        assertThat(enemyRobot.getDistance()).isEqualTo(distance);
        assertThat(enemyRobot.getHeading()).isEqualTo(heading);
        assertThat(enemyRobot.getVelocity()).isEqualTo(velocity);
        assertThat(enemyRobot.getName()).isEqualTo(name);
        assertThat(enemyRobot.isSentryRobot()).isEqualTo(isSentry);

    }
}

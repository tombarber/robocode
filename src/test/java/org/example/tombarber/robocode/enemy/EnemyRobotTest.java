package org.example.tombarber.robocode.enemy;

import org.decimal4j.util.DoubleRounder;
import org.example.tombarber.robocode.enemy.EnemyRobot.EnemyRobotBuilder;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnemyRobotTest {

    @Nested
    class BuilderMandatoryFieldTests {
        @Test
        void requiresEnergy() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withEnergy(null).build()).getMessage();
            assertThat(message).isEqualTo("energy must not be null");
        }

        @Test
        void requiresBearing() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withBearing(null).build()).getMessage();
            assertThat(message).isEqualTo("bearing must not be null");
        }

        @Test
        void requiresDistance() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withDistance(null).build()).getMessage();
            assertThat(message).isEqualTo("distance must not be null");
        }

        @Test
        void requiresHeading() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withHeading(null).build()).getMessage();
            assertThat(message).isEqualTo("heading must not be null");
        }

        @Test
        void requiresVelocity() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withVelocity(null).build()).getMessage();
            assertThat(message).isEqualTo("velocity must not be null");
        }

        @Test
        void requiresName() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withName(null).build()).getMessage();
            assertThat(message).isEqualTo("name must not be null");
        }

        @Test
        void requiresIsSentry() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withIsSentry(null).build()).getMessage();
            assertThat(message).isEqualTo("isSentry must not be null");
        }

        @Test
        void requiresX() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withX(null).build()).getMessage();
            assertThat(message).isEqualTo("x must not be null");
        }

        @Test
        void requiresY() {
            final String message = assertThrows(NullPointerException.class, () -> populatedBuilder().withY(null).build()).getMessage();
            assertThat(message).isEqualTo("y must not be null");
        }
    }

    @Test
    void enemyRobotPopulatedByBuilder() {

        final double energy = 1.0;
        final double bearing = 2.0;
        final double distance = 3.0;
        final double heading = 4.0;
        final double velocity = 5.0;
        final String name = "SomeBot";
        final boolean isSentry = true;
        final double x = 2.0;
        final double y = 3.0;

        EnemyRobot enemyRobot = EnemyRobotBuilder.builder()
                .withEnergy(energy)
                .withBearing(bearing)
                .withDistance(distance)
                .withHeading(heading)
                .withVelocity(velocity)
                .withName(name)
                .withIsSentry(isSentry)
                .withX(x)
                .withY(y)
                .build();

        assertThat(enemyRobot.getEnergy()).isEqualTo(energy);
        assertThat(enemyRobot.getBearing()).isEqualTo(bearing);
        assertThat(enemyRobot.getDistance()).isEqualTo(distance);
        assertThat(enemyRobot.getHeading()).isEqualTo(heading);
        assertThat(enemyRobot.getVelocity()).isEqualTo(velocity);
        assertThat(enemyRobot.getName()).isEqualTo(name);
        assertThat(enemyRobot.isSentryRobot()).isEqualTo(isSentry);
        assertThat(enemyRobot.getX()).isEqualTo(x);
        assertThat(enemyRobot.getY()).isEqualTo(y);
    }

    @Test
    void canCalculateFutureXPositionIfMaintainingCurrentVelocityAndHeading() {
        double currentX = 0.0;
        double heading = 45.0;
        double velocity = 10;
        long time = 50;
        double expectedFutureX = 353.55;
        EnemyRobot enemyRobot = populatedBuilder().withX(currentX).withHeading(heading).withVelocity(velocity).build();

        assertThat(DoubleRounder.round(enemyRobot.getFutureX(time), 2)).isEqualTo(expectedFutureX);

    }

    @Test
    void canCalculateFutureYPositionIfMaintainingCurrentVelocityAndHeading() {
        double currentY = 0.0;
        double heading = 45.0;
        double velocity = 10;
        long time = 50;
        double expectedFutureY = 353.55;
        EnemyRobot enemyRobot = populatedBuilder().withY(currentY).withHeading(heading).withVelocity(velocity).build();

        assertThat(DoubleRounder.round(enemyRobot.getFutureY(time), 2)).isEqualTo(expectedFutureY);

    }

    private EnemyRobotBuilder populatedBuilder() {
        return EnemyRobotBuilder.builder()
                .withEnergy(1.0)
                .withBearing(1.0)
                .withDistance(1.0)
                .withHeading(1.0)
                .withVelocity(1.0)
                .withName("name")
                .withIsSentry(false)
                .withX(1.0)
                .withY(2.0);
    }
}

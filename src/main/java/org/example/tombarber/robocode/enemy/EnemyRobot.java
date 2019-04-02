package org.example.tombarber.robocode.enemy;

import static java.util.Objects.requireNonNull;

public class EnemyRobot {

    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private double velocity;
    private String name;
    private boolean isSentry;
    private double x;
    private double y;

    private EnemyRobot(Double bearing, Double distance, Double energy, Double heading, Double velocity, String name, Boolean isSentry, Double x, Double y) {

        this.bearing = requireNonNull(bearing, "bearing must not be null");
        this.distance = requireNonNull(distance, "distance must not be null");
        this.energy = requireNonNull(energy, "energy must not be null");
        this.heading = requireNonNull(heading, "heading must not be null");
        this.velocity = requireNonNull(velocity, "velocity must not be null");
        this.name = requireNonNull(name, "name must not be null");
        this.isSentry = requireNonNull(isSentry, "isSentry must not be null");
        this.x = requireNonNull(x, "x must not be null");
        this.y = requireNonNull(y, "y must not be null");
    }

    public double getBearing() {
        return bearing;
    }

    public double getDistance() {
        return distance;
    }

    public double getEnergy() {
        return energy;
    }

    public double getHeading() {
        return heading;
    }

    public double getVelocity() {
        return velocity;
    }

    public String getName() {
        return name;
    }

    public boolean isSentryRobot() {
        return isSentry;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getFutureX(long time) {
        return this.x + Math.sin(Math.toRadians(this.getHeading())) * this.velocity * time;
    }

    public double getFutureY(long time) {
        return this.y + Math.cos(Math.toRadians(heading)) * velocity * time;
    }

    public static final class EnemyRobotBuilder {
        private Double bearing;
        private Double distance;
        private Double energy;
        private Double heading;
        private Double velocity;
        private String name;
        private Boolean isSentry;
        private Double x;
        private Double y;

        private EnemyRobotBuilder() {
        }

        public static EnemyRobotBuilder builder() {
            return new EnemyRobotBuilder();
        }

        public EnemyRobotBuilder withBearing(Double bearing) {
            this.bearing = bearing;
            return this;
        }

        public EnemyRobotBuilder withDistance(Double distance) {
            this.distance = distance;
            return this;
        }

        public EnemyRobotBuilder withEnergy(Double energy) {
            this.energy = energy;
            return this;
        }

        public EnemyRobotBuilder withHeading(Double heading) {
            this.heading = heading;
            return this;
        }

        public EnemyRobotBuilder withVelocity(Double velocity) {
            this.velocity = velocity;
            return this;
        }

        public EnemyRobotBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EnemyRobotBuilder withIsSentry(Boolean isSentry) {
            this.isSentry = isSentry;
            return this;
        }

        public EnemyRobotBuilder withX(Double x) {
            this.x = x;
            return this;
        }

        public EnemyRobotBuilder withY(Double y) {
            this.y = y;
            return this;
        }

        public EnemyRobot build() {

            return new EnemyRobot(bearing, distance, energy, heading, velocity, name, isSentry, x, y);
        }
    }
}

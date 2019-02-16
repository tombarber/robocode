package org.example.tombarber.robocode.enemy;

import robocode.ScannedRobotEvent;

import static java.util.Objects.requireNonNull;

public class EnemyRobot {

    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private double veolocity;
    private String name;
    private boolean isSentry;

    public EnemyRobot(final ScannedRobotEvent scannedRobotEvent) {
        requireNonNull(scannedRobotEvent, "scannedRobotEvent must not be null");
        this.bearing = scannedRobotEvent.getBearing();
        this.distance = scannedRobotEvent.getDistance();
        this.energy = scannedRobotEvent.getEnergy();
        this.heading = scannedRobotEvent.getHeading();
        this.veolocity = scannedRobotEvent.getVelocity();
        this.name = scannedRobotEvent.getName();
        this.isSentry = scannedRobotEvent.isSentryRobot();
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

    public double getVeolocity() {
        return veolocity;
    }

    public String getName() {
        return name;
    }

    public boolean isSentryRobot() {
        return isSentry;
    }
}

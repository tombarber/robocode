package org.example.tombarber.robocode;

import robocode.ScannedRobotEvent;

public class ScannedRobotEventBuilder {
    private double bearing;
    private double distance;
    private double energy;
    private double heading;
    private double velocity;
    private String name;
    private boolean isSentry;

    private ScannedRobotEventBuilder() {}

    public static ScannedRobotEventBuilder builder() {
        return new ScannedRobotEventBuilder();
    }

    public ScannedRobotEventBuilder setBearing(double bearing) {
        this.bearing = bearing;
        return this;
    }

    public ScannedRobotEventBuilder setDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public ScannedRobotEventBuilder setEnergy(double energy) {
        this.energy = energy;
        return this;
    }

    public ScannedRobotEventBuilder setHeading(double heading) {
        this.heading = heading;
        return this;
    }

    public ScannedRobotEventBuilder setVelocity(double velocity) {
        this.velocity = velocity;
        return this;
    }

    public ScannedRobotEventBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ScannedRobotEventBuilder setSentry(boolean sentry) {
        isSentry = sentry;
        return this;
    }

    public ScannedRobotEvent build(){
        return new ScannedRobotEvent(name, energy, bearing, distance, heading, velocity, isSentry);
    }
}

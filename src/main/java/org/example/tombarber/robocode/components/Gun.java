package org.example.tombarber.robocode.components;

import robocode.Robot;

public class Gun {
    private Robot robot;

    public Gun(Robot robot, GunTemperatureControl temperatureControl) {

        this.robot = robot;
    }

    public long getBulletTravelTime(double firePower, double targetDistance) {
        final double bulletSpeed = 20 - firePower * 3;
        return (long) (targetDistance / bulletSpeed);
    }
}

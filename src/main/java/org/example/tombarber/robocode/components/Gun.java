package org.example.tombarber.robocode.components;

import robocode.Robot;

public class Gun {
    private Robot robot;

    public Gun(Robot robot) {

        this.robot = robot;
    }

    public boolean isCool() {
        return robot.getGunHeat() == 0;
    }

    public long getBulletTravelTime(double firePower, double targetDistance) {
        final double bulletSpeed = 20 - firePower * 3;
        return (long) (targetDistance / bulletSpeed);
    }
}

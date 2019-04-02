package org.example.tombarber.robocode.components;

import robocode.Robot;

public class GunTemperatureControl {
    private Robot robot;

    public GunTemperatureControl(Robot robot) {
        this.robot = robot;
    }

    public boolean isCool() {
        return robot.getGunHeat() == 0;
    }
}

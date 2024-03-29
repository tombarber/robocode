package org.example.tombarber.robocode.components;

import robocode.AdvancedRobot;

import java.awt.Color;

import static java.util.Objects.requireNonNull;

public class Turret {

    // TODO: Break down into smaller component parts - something to aim,

    private AdvancedRobot robot;

    public Turret(AdvancedRobot robot) {
        this.robot = requireNonNull(robot, "robot cannot be null");
        robot.setBulletColor(Color.RED);
        robot.setGunColor(Color.DARK_GRAY);
    }

    public AdvancedRobot getRobot() {
        return robot;
    }
}

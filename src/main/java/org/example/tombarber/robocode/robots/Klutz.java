package org.example.tombarber.robocode.robots;

import org.example.tombarber.robocode.components.TargetingSystem;
import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class Klutz extends AdvancedRobot {

    private TargetingSystem targetingSystem;

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        // TODO: rename this method and update params maybe?
        targetingSystem.onRobotDeath(event);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        targetingSystem.onScannedRobot(event);
        // TODO: tracks + turret onScanned...
    }

    @Override
    public void run() {

    }
}

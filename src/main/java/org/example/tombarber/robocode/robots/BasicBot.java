package org.example.tombarber.robocode.robots;

import org.example.tombarber.robocode.components.Gun;
import org.example.tombarber.robocode.components.GunPowerControl;
import org.example.tombarber.robocode.components.GunTemperatureControl;
import org.example.tombarber.robocode.components.TargetingSystem;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class BasicBot extends Robot {

    private Gun gun;
    private TargetingSystem targetingSystem;

    public void run() {

        assembleRobot();

        while (true) {
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    private void assembleRobot() {
        targetingSystem = new TargetingSystem(this);
        assembleGun();
    }

    private void assembleGun() {
        GunTemperatureControl gunTemperatureControl = new GunTemperatureControl(this);
        GunPowerControl gunPowerControl = new GunPowerControl();
        gun = new Gun(this, gunTemperatureControl, gunPowerControl);
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        targetingSystem.onScannedRobot(e);
        if(targetingSystem.getCurrentTarget().isPresent()) {
            gun.fireAtTarget(targetingSystem.getCurrentTarget().get());
        }
    }
}

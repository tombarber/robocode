package org.example.tombarber.robocode.robots;

import org.example.tombarber.robocode.components.Gun;
import org.example.tombarber.robocode.components.GunPowerControl;
import org.example.tombarber.robocode.components.GunTemperatureControl;
import org.example.tombarber.robocode.components.TargetingSystem;
import org.example.tombarber.robocode.components.tracks.Tracks;
import org.example.tombarber.robocode.enemy.EnemyRobot;
import robocode.Robot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

public class TomBarberBasic extends Robot {

    private Gun gun;
    private Tracks tracks;
    private TargetingSystem targetingSystem;

    public void run() {

        setAdjustRadarForRobotTurn(true);
        assembleRobot();

        while (true) {
            turnRadarRight(360);
            //ahead(100);
            //turnGunRight(360);
            //back(100);
            //turnGunRight(360);
        }
    }

    private void assembleRobot() {
        targetingSystem = new TargetingSystem(this);
        tracks = new Tracks(this);
        assembleGun();
    }

    private void assembleGun() {
        GunTemperatureControl gunTemperatureControl = new GunTemperatureControl(this);
        GunPowerControl gunPowerControl = new GunPowerControl();
        gun = new Gun(this, gunTemperatureControl, gunPowerControl);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        targetingSystem.onScannedRobot(e);
        if (targetingSystem.getCurrentTarget().isPresent()) {
            EnemyRobot target = targetingSystem.getCurrentTarget().get();
            gun.fireAtTarget(target);
            tracks.move(target);
        }
    }

    @Override
    public void onRobotDeath(RobotDeathEvent robotDeathEvent) {
        targetingSystem.onRobotDeath(robotDeathEvent);
    }
}

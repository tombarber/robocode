package org.example.tombarber.robocode.components;

import org.example.tombarber.robocode.enemy.EnemyRobot;
import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class TargetingSystem {

    private AdvancedRobot robot;
    private Optional<EnemyRobot> currentTarget;

    public TargetingSystem(AdvancedRobot robot) {
        this.robot = requireNonNull(robot, "robot cannot be null");
        clearCurrentTarget();
    }

    public AdvancedRobot getRobot() {
        return robot;
    }

    public void onScannedRobot(ScannedRobotEvent scannedRobotEvent) {
        acquireBestTarget(scannedRobotEvent);
    }

    private void acquireBestTarget(ScannedRobotEvent scannedRobotEvent) {
        if (hasNoCurrentTarget() || isCurrentTarget(scannedRobotEvent.getName()) || isCloserThanCurrentTarget(scannedRobotEvent.getDistance())) {
            currentTarget = Optional.of(new EnemyRobot(scannedRobotEvent));
        }
    }

    private boolean hasNoCurrentTarget() {
        return !currentTarget.isPresent();
    }

    private boolean isCurrentTarget(String name) {
        return currentTarget.map((current) -> current.getName().equals(name)).orElse(false);
    }

    private boolean isCloserThanCurrentTarget(double distance) {
        return currentTarget.map((current) -> distance < current.getDistance()).orElse(false);
    }

    public Optional<EnemyRobot> getCurrentTarget() {
        return currentTarget;
    }

    public void onRobotDeath(RobotDeathEvent robotDeathEvent) {
        if (isCurrentTarget(robotDeathEvent.getName())) {
            clearCurrentTarget();
        }
    }

    private void clearCurrentTarget() {
        currentTarget = Optional.empty();
    }
}

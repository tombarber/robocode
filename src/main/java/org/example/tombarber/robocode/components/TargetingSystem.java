package org.example.tombarber.robocode.components;

import org.example.tombarber.robocode.enemy.EnemyRobot;
import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class TargetingSystem {

    // TODO: Remove enemies from map when they die?
    private final AdvancedRobot robot;
    private final Map<String, EnemyRobot> knownEnemies = new HashMap<>();
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
        addToKnownEnemies(new EnemyRobot(scannedRobotEvent));
    }

    private void addToKnownEnemies(EnemyRobot enemyRobot) {
        knownEnemies.put(enemyRobot.getName(), enemyRobot);
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

    public Map<String, EnemyRobot> getKnownEnemies() {
        return knownEnemies;
    }
}

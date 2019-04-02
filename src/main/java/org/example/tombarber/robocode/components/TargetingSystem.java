package org.example.tombarber.robocode.components;

import org.example.tombarber.robocode.enemy.EnemyRobot;
import robocode.Robot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class TargetingSystem {

    // TODO: Remove enemies from map when they die?
    private final Robot robot;
    private final Map<String, EnemyRobot> knownEnemies = new HashMap<>();
    private Optional<EnemyRobot> currentTarget;

    public TargetingSystem(Robot robot) {
        this.robot = requireNonNull(robot, "robot cannot be null");
        clearCurrentTarget();
    }

    public Robot getRobot() {
        return robot;
    }

    public void onScannedRobot(ScannedRobotEvent scannedRobotEvent) {
        acquireBestTarget(scannedRobotEvent);
        addToKnownEnemies(buildEnemyRobot(scannedRobotEvent));
    }

    private EnemyRobot buildEnemyRobot(ScannedRobotEvent scannedRobotEvent) {
        return EnemyRobot.EnemyRobotBuilder.builder()
                .withEnergy(scannedRobotEvent.getEnergy())
                .withBearing(scannedRobotEvent.getBearing())
                .withDistance(scannedRobotEvent.getDistance())
                .withHeading(scannedRobotEvent.getHeading())
                .withVelocity(scannedRobotEvent.getVelocity())
                .withName(scannedRobotEvent.getName())
                .withIsSentry(scannedRobotEvent.isSentryRobot())
                .withX(calculateEnemyX(scannedRobotEvent.getBearing(), scannedRobotEvent.getDistance()))
                .withY(calculateEnemyY(scannedRobotEvent.getBearing(), scannedRobotEvent.getDistance()))
                .build();

    }

    private double calculateEnemyX(double enemyBearing, double enemyDistance) {
        return robot.getX() + Math.sin(Math.toRadians(getEnemyAbsoluteBearingInDegrees(enemyBearing))) * enemyDistance;
    }

    private double calculateEnemyY(double enemyBearing, double enemyDistance) {
        return robot.getY() + Math.cos(Math.toRadians(getEnemyAbsoluteBearingInDegrees(enemyBearing))) * enemyDistance;
    }

    private double getEnemyAbsoluteBearingInDegrees(double enemyBearing) {
        double absBearingDeg = robot.getHeading() + enemyBearing;
        if (absBearingDeg < 0) absBearingDeg += 360;
        return absBearingDeg;
    }

    private void addToKnownEnemies(EnemyRobot enemyRobot) {
        knownEnemies.put(enemyRobot.getName(), enemyRobot);
    }

    private void acquireBestTarget(ScannedRobotEvent scannedRobotEvent) {
        if (hasNoCurrentTarget() || isCurrentTarget(scannedRobotEvent.getName()) || isCloserThanCurrentTarget(scannedRobotEvent.getDistance())) {
            currentTarget = Optional.of(buildEnemyRobot(scannedRobotEvent));
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

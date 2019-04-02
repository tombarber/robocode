package org.example.tombarber.robocode.components.tracks;

import org.example.tombarber.robocode.enemy.EnemyRobot;
import robocode.Robot;

public class Tracks {
    private Robot robot;
    private int moveDirection = 1;
    private long timeOfLastDirectionChange = 0;

    public Tracks(Robot robot) {
        this.robot = robot;
    }

    public void move(EnemyRobot target) {
        int moveDistance = 50;
        if (robot.getVelocity() == 0) {
            changeDirection();
            moveDistance = moveDistance * 2;
        } else if (isTimeToChangeDirection()) {
            changeDirection();
        }

        robot.turnRight(target.getBearing() + 90 - 15 * moveDirection);
        robot.ahead(moveDistance * moveDirection);
    }

    private boolean isTimeToChangeDirection() {
        return robot.getTime() - timeOfLastDirectionChange > 20;
    }

    private void changeDirection() {
        moveDirection *= -1;
        timeOfLastDirectionChange = robot.getTime();
    }

    /*public double getFutureX(long time) {
        return this.x + Math.sin(Math.toRadians(robot)) * this.velocity * time;
    }

    public double getFutureY(long time) {
        return this.y + Math.cos(Math.toRadians(heading)) * velocity * time;
    }*/
}

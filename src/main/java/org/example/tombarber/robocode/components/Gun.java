package org.example.tombarber.robocode.components;

import org.example.tombarber.robocode.BearingUtils;
import org.example.tombarber.robocode.enemy.EnemyRobot;
import robocode.Robot;

public class Gun {
    private Robot robot;
    private GunTemperatureControl gunTemperatureControl;
    private GunPowerControl gunPowerControl;

    public Gun(Robot robot, GunTemperatureControl gunTemperatureControl, GunPowerControl gunPowerControl) {

        this.robot = robot;
        this.gunTemperatureControl = gunTemperatureControl;
        this.gunPowerControl = gunPowerControl;
    }

    // TODO: pull this out elsewhere
    public long getBulletTravelTime(double firePower, double targetDistance) {
        final double bulletSpeed = 20 - firePower * 3;
        return (long) (targetDistance / bulletSpeed);
    }

    public void fireAtTarget(EnemyRobot target) {
        double firePower = gunPowerControl.calculateFirePower(target.getDistance());

        aim(target, firePower);
        if(gunTemperatureControl.isCool()) {
            robot.fire(firePower);
        }
    }

    private void aim(final EnemyRobot target, final double firePower) {
        double targetDistance = target.getDistance();
        final long time = getBulletTravelTime(firePower, targetDistance);

        //calculate gun turn to predicted x,y location
        final double targetFutureX = target.getFutureX(time);
        final double targetFutureY = target.getFutureY(time);

        final double absDegToTarget = BearingUtils.getAbsoluteBearing(robot.getX(), robot.getY(), targetFutureX, targetFutureY);

        // turn the fun to the predicted location
        // TODO: Break out the actual turning as AdvancedRobot would use setTurnGunRight
        robot.turnGunRight(BearingUtils.normalizeBearing(absDegToTarget - robot.getGunHeading()));
    }
}

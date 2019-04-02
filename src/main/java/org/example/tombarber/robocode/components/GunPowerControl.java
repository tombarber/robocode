package org.example.tombarber.robocode.components;

public class GunPowerControl {

    public double calculateFirePower(double targetDistance) {
        return Math.min(500 / targetDistance, 3.0d);
    }
}

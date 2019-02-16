package org.example.tombarber.robocode;

import java.awt.geom.Point2D;

public class BearingUtils {
    public static double getAbsoluteBearing(double x1, double y1, double x2, double y2) {
        double xo = x2 - x1;
        double yo = y2 - y1;
        double hyp = Point2D.distance(x1, y1, x2, y2);
        double arcSin = Math.toDegrees(Math.asin(xo / hyp));

        double bearing = 0; // north

        if (xo > 0 && yo > 0) { // both pos: 0 < target bearing < 90
            bearing = arcSin;
        } else if (xo > 0 && yo == 0) { //directly "east"
            bearing = 90.0;
        } else if (xo > 0 && yo < 0) { // x pos, y neg: 90 < target bearing < 180
            bearing = 180 - arcSin;
        } else if (xo == 0 && yo < 0) { //directly "south"
            bearing = 180.0;
        } else if (xo < 0 && yo < 0) { // both neg: 180 < target bearing < 270
            bearing = 180 - arcSin; // arcsin is negative here, actually 180 + ang
        } else if (xo < 0 && yo == 0) { // directly "west"
            bearing = 270.0;
        } else if (xo < 0 && yo > 0) { // x neg, y pos: 270 < target bearing < 360
            bearing = 360 + arcSin; // arcsin is negative here, actuall 360 - ang
        }

        return bearing;
    }

    // normalizes a bearing to between +180 and -180
    public static double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}

package org.example.tombarber.robocode.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robocode.Robot;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GunTest {

    @Mock
    private Robot robot;

    @Mock GunTemperatureControl temperatureControl;

    private Gun gun;

    @BeforeEach
    void setUp() {
        gun = new Gun(robot, temperatureControl);
    }

    @ParameterizedTest
    @CsvSource({"1.0, 17.0, 1",
            "2.0, 28, 2"})
    void canCalculateBulletTimeToTarget(double firePower, double targetDistance, long expectedTimeInMillis) {
        /*
        http://robowiki.net/wiki/Robocode/Game_Physics#Bullets
        Bullet velocity
        20 - 3 * firepower
        */

        assertThat(gun.getBulletTravelTime(firePower, targetDistance)).isEqualTo(expectedTimeInMillis);
    }

    @Test
    void name() {
    }
}

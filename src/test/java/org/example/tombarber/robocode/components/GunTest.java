package org.example.tombarber.robocode.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robocode.Robot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GunTest {

    @Mock
    private Robot robot;
    private Gun gun;

    @BeforeEach
    void setUp() {
        gun = new Gun(robot);
    }

    @ParameterizedTest
    @CsvSource({"0.1, false",
            "10.0, false",
            "0.0, true"})
    void shouldKnowIfGunIsCool(double gunHeat, boolean isCool) {
        given(robot.getGunHeat()).willReturn(gunHeat);
        assertThat(gun.isCool()).isEqualTo(isCool);
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
}

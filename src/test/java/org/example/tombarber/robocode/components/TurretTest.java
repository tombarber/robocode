package org.example.tombarber.robocode.components;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robocode.AdvancedRobot;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class TurretTest {

    @Mock
    private AdvancedRobot advancedRobot;

    @Test
    void isMountedToARobot() {
        assertThat(new Turret(advancedRobot).getRobot()).isEqualTo(advancedRobot);
    }

    @Test
    void cannotBeCreatedWithNullRobot() {
        String message = assertThrows(NullPointerException.class, () -> new Turret(null)).getMessage();
        assertThat(message).isEqualTo("robot cannot be null");
    }

    @Test
    void shouldFireRedBullets() {
        new Turret(advancedRobot);
        then(advancedRobot).should(times(1)).setBulletColor(Color.RED);
    }

    @Test
    void shouldBeADarkGreyTurret() {
        new Turret(advancedRobot);
        then(advancedRobot).should(times(1)).setGunColor(Color.DARK_GRAY);
    }
}

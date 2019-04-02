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
public class GunTemperatureControlTest {

    @Mock
    private Robot robot;
    private GunTemperatureControl temperatureControl;

    @BeforeEach
    void setUp() {
        temperatureControl = new GunTemperatureControl(robot);
    }

    @ParameterizedTest
    @CsvSource({"0.1, false",
            "10.0, false",
            "0.0, true"})
    void shouldKnowIfGunIsCool(double gunHeat, boolean isCool) {
        given(robot.getGunHeat()).willReturn(gunHeat);
        assertThat(temperatureControl.isCool()).isEqualTo(isCool);
    }
}

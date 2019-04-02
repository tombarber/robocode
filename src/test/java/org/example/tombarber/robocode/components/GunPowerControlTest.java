package org.example.tombarber.robocode.components;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GunPowerControlTest {

    private GunPowerControl gunPowerControl;

    @BeforeEach
    void setUp() {
        gunPowerControl = new GunPowerControl();
    }

    @ParameterizedTest
    @CsvSource({"10, 3.0",
            "167, 3.0",
            "200, 2.5",
            "300, 1.67"
    })
    void firepowerShouldBeMinOf500DividedByTargetDistanceAnd3(double targetDistance, double expectedFirepower) {
        assertThat(gunPowerControl.calculateFirePower(targetDistance)).isCloseTo(expectedFirepower, Assertions.offset(0.1d));
    }
}

package org.example.tombarber.robocode;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BearingUtilsTest {

    @Test
    void willNormaliseBearingBetweenZeroAnd180() {
        assertThat(BearingUtils.normalizeBearing(180.0)).isEqualTo(180.0);
    }

    @Test
    void willNormaliseBearingOver180() {
        assertThat(BearingUtils.normalizeBearing(181.0)).isEqualTo(-179.0);
    }

    @Test
    void willNormaliseBearingWayOver180() {
        assertThat(BearingUtils.normalizeBearing(181.0 + 360 + 360)).isEqualTo(-179.0);
    }

    @Test
    void willNormaliseBearingBetweenZeroAndMinus180() {
        assertThat(BearingUtils.normalizeBearing(-180.0)).isEqualTo(-180.0);
    }

    @Test
    void willNormaliseBearingUnderMinus180() {
        assertThat(BearingUtils.normalizeBearing(-181.0)).isEqualTo(179.0);
    }

    @Test
    void willNormaliseBearingWayUnderMinus180() {
        assertThat(BearingUtils.normalizeBearing(-181.0 - 360 - 360)).isEqualTo(179.0);
    }
}

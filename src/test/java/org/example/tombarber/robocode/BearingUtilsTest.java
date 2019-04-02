package org.example.tombarber.robocode;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BearingUtilsTest {

    private static final Offset<Double> OFFSET = Offset.offset(0.00001);

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

    @Test
    void canCalculateBearingToEnemyWithSameXGreaterY() {
        assertThat(BearingUtils.getAbsoluteBearing(1.0, 1.0, 1.0, 2.0)).isEqualTo(0.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithGreaterXAndGreaterY() {
        assertThat(BearingUtils.getAbsoluteBearing(1.0, 1.0, 2.0, 2.0)).isEqualTo(45.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithGreaterXAndSameY() {
        assertThat(BearingUtils.getAbsoluteBearing(1.0, 1.0, 2.0, 1.0)).isEqualTo(90.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithGreaterXAndLowerY() {
        assertThat(BearingUtils.getAbsoluteBearing(1.0, 2.0, 2.0, 1.0)).isEqualTo(135.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithSameXAndLowerY() {
        assertThat(BearingUtils.getAbsoluteBearing(1.0, 2.0, 1.0, 1.0)).isEqualTo(180.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithLowerXAndLowerY() {
        assertThat(BearingUtils.getAbsoluteBearing(2.0, 2.0, 1.0, 1.0)).isEqualTo(225.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithLowerXAndSameY() {
        assertThat(BearingUtils.getAbsoluteBearing(2.0, 2.0, 1.0, 2.0)).isEqualTo(270.0, OFFSET);
    }

    @Test
    void canCalculateBearingToEnemyWithLowerXAndGreaterY() {
        assertThat(BearingUtils.getAbsoluteBearing(2.0, 1.0, 1.0, 2.0)).isEqualTo(315.0, OFFSET);
    }
}

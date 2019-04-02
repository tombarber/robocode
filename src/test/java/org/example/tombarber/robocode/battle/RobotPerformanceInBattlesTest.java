package org.example.tombarber.robocode.battle;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.testing.RobotTestBed;

import static org.assertj.core.api.Assertions.assertThat;

/***
 * Based on https://hiraidekeone.wordpress.com/2013/02/26/robocode-quality-assurance-and-junit-testing/
 */
@RunWith(JUnit4.class)
public class RobotPerformanceInBattlesTest extends RobotTestBed {

    @BeforeClass
    public static void setRobocodeHome() {
        System.setProperty("robocode.home", "C:/robocode");
    }

    /**
     * The names of the robots that want battling is specified.
     * @return The names of the robots we want battling.
     */
    @Override
    public String getRobotNames() {
        return "sample.Walls, org.example.tombarber.robocode.robots.BasicBot";
    }

    /**
     * Pick the amount of rounds that we want our robots to battle for.
     *
     * @return Amount of rounds we want to battle for.
     */
    @Override
    public int getNumRounds() {
        return 10;
    }

    /**
     * Tests to see if our robot won all rounds.
     * @param event Holds information about the battle has been completed.
     */
    @Override
    public void onBattleCompleted(BattleCompletedEvent event) {
        // Return the results in order of getRobotNames.
        BattleResults[] battleResults = event.getIndexedResults();

        BattleResults firstEnteredRobot = battleResults[0];
        assertThat(firstEnteredRobot.getTeamLeaderName()).isEqualTo("org.example.tombarber.robocode.robots.BasicBot");

        // Check to make sure the firstEnteredRobot won at least won over half the rounds.
        assertThat(firstEnteredRobot.getFirsts()).isGreaterThan(getNumRounds() / 2);
    }
}
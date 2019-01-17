package de.drumcat.riotapichallengefx.domain;

import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;
import net.rithms.riot.api.endpoints.match.dto.ParticipantTimeline;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ParticipantStatsTimelineTest {

    private ParticipantStatsTimeline testObject;
    private ParticipantStats testStats;
    private ParticipantTimeline testTimeline;

    @Before
    public void setUp() {
        testObject = new ParticipantStatsTimeline();
        testStats = new ParticipantStats();
        testTimeline = new ParticipantTimeline();
        testObject.setStats(testStats);
        testObject.setTimeline(testTimeline);
    }

    @Test
    public void getKDARatio() {
        try {
            FieldUtils.writeDeclaredField(testStats, "kills", 4, true);
            FieldUtils.writeDeclaredField(testStats, "deaths", 13, true);
            FieldUtils.writeDeclaredField(testStats, "assists", 9, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }

        assertEquals(1, testObject.getKDARatio(), 0.001);
    }

    @Test
    public void getKillParticipation() {
        try {
            FieldUtils.writeDeclaredField(testStats, "kills", 4, true);
            FieldUtils.writeDeclaredField(testStats, "assists", 9, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(46.4, testObject.getKillParticipation(), 0.009);
    }

    @Test
    public void getDamagePerDeath() {
        try {
            FieldUtils.writeDeclaredField(testStats, "totalDamageDealtToChampions", 12665, true);
            FieldUtils.writeDeclaredField(testStats, "deaths", 13, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(974, testObject.getDamagePerDeath());

    }

    @Test
    public void getDamagePerGold() {
        try {
            FieldUtils.writeDeclaredField(testStats, "totalDamageDealtToChampions", 12665, true);
            FieldUtils.writeDeclaredField(testStats, "goldEarned", 10348, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(1.22, testObject.getDamagePerGold(), 0.009);
    }

    @Test
    public void getCSPerMinute() {
        try {
            FieldUtils.writeDeclaredField(testStats, "totalMinionsKilled", 154, true);
            FieldUtils.writeDeclaredField(testStats, "neutralMinionsKilled", 4, true);
            testObject.setTimePlayed(1918);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(4.7, testObject.getCSPerMinute(), 0.009);
    }

    @Test
    public void getDamageShare() {
        try {
            FieldUtils.writeDeclaredField(testStats, "totalDamageDealtToChampions", 12665, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(15.8, testObject.getDamageShare(), 0.009);
    }

    @Test
    public void getVisionScorePerHour() {
        try {
            FieldUtils.writeDeclaredField(testStats, "visionScore", 26, true);
            testObject.setTimePlayed(1918);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(48.8, testObject.getVisionScorePerHour(), 0.009);
    }

    @Test
    public void getEarlyCSAdvantage() {
        Map<String, Double> csDiff = new HashMap<>();
        csDiff.put("0-10", 1.3);
        try {
            FieldUtils.writeDeclaredField(testTimeline, "csDiffPerMinDeltas", csDiff, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(1.3, testObject.getEarlyCSAdvantage(), 0.009);

    }

    @Test
    public void getEarlyGoldAdvantage() {
        Map<String, Double> goldDiff = new HashMap<>();
        goldDiff.put("0-10", 305.9);
        try {
            FieldUtils.writeDeclaredField(testTimeline, "goldPerMinDeltas", goldDiff, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(305.9, testObject.getEarlyGoldAdvantage(), 0.009);
    }

    @Test
    public void getObjectiveControlRatio() {
        try {
            FieldUtils.writeDeclaredField(testStats, "kills", 4, true);
        } catch (IllegalAccessException e) {
            fail("field does not exist: " + e.getMessage());
        }
        assertEquals(25.0, testObject.getObjectiveControlRatio(), 0.009);
    }
}
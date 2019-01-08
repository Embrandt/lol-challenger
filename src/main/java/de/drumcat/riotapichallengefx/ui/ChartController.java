package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.domain.DiagramStats;
import de.drumcat.riotapichallengefx.domain.UserStatsDto;
import de.drumcat.riotapichallengefx.service.BuddyApiService;
import de.drumcat.riotapichallengefx.service.StatsApiService;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.function.Function;

@Controller
public class ChartController {

    private final Map<String, String> nameToPUUID = new HashMap<>();
    private final Map<String, List<UserStatsDto>> pUUIDToStats = new HashMap<>();
    public AreaChart<String, Number> kdaRatioChart;
    public AreaChart<String, Number> killParticipationChart;
    public AreaChart<String, Number> damagePerDeathChart;
    public AreaChart<String, Number> damageShareChart;
    public AreaChart<String, Number> earlyGoldDiffChart;
    public AreaChart<String, Number> earlyCSDiffChart;
    public AreaChart<String, Number> damagePerGoldChart;
    public AreaChart<String, Number> CSPerMinuteChart;
    public AreaChart<String, Number> objectiveControlChart;
    public AreaChart<String, Number> visionPerHourChart;
    public AreaChart<String, Number> roamDominanceChart;
    public AreaChart<String, Number> killConversionChart;

    /**
     * Filter list of user stats according to parameters set previously
     * And reorders the list, so oldest data comes first
     * This method still needs work, currently filters on fixed parameters
     *
     * @param userStatsByPuuid list of user stats to filter
     * @return a new list with the filtered data in reverse order
     */
    private List<UserStatsDto> filterData(List<UserStatsDto> userStatsByPuuid) {
        List<UserStatsDto> filteredList = new ArrayList<>();
        int i = 0;
        for (UserStatsDto userStatsDto : userStatsByPuuid) {
            // TODO This part should be configurable
            if (userStatsDto.getLane().equals("MID") && userStatsDto.getQueueType().equals("rank5solo")) {
                filteredList.add(userStatsDto);
                i++;
            }
            //TODO the number of entries should be configurable
            if (i == 10) {
                Collections.reverse(filteredList);
                break;
            }
        }
        return filteredList;
    }

    /**
     * Fills a given chart with a value computed from the given stats
     * and adds this as new series for the chart
     *
     * @param chartToFill   the chart that should be populated with data
     * @param summoner      the name of the summoner this data belongs to
     * @param listOfStats   list of stats to fill the chart with
     * @param valueFunction function to compute the Y-value from the stats with
     */
    private void addSeriesToChart(XYChart<String, Number> chartToFill, String summoner, List<UserStatsDto> listOfStats, Function<DiagramStats, Number> valueFunction) {
        XYChart.Series<String, Number> seriesME = new XYChart.Series<>();
        seriesME.setName(summoner);
        int i = 0;
        List<XYChart.Data<String, Number>> lSeriesData = new ArrayList<>();
        for (UserStatsDto userStatsDto : filterData(listOfStats)) {
            String entryNumber = Integer.toString(i);
            DiagramStats lStats = userStatsDto.getStats().getCareerStats();
            Number computedValue = valueFunction.apply(lStats);
            lSeriesData.add(new XYChart.Data<>(entryNumber, computedValue));
            i++;
        }
        seriesME.getData().addAll(lSeriesData);
        chartToFill.getData().add(seriesME);
    }

    /**
     * Fills all available charts with the data for a summoner
     *
     * @param summonerName name of the summoner
     * @param userData     data to add to the charts
     */
    private void fillChartsForUser(String summonerName, List<UserStatsDto> userData) {
        addSeriesToChart(killParticipationChart, summonerName, userData, DiagramStats::getKillParticipation);
        addSeriesToChart(kdaRatioChart, summonerName, userData, DiagramStats::getKDARatio);
        addSeriesToChart(damagePerDeathChart, summonerName, userData, DiagramStats::getDamagePerDeath);
        addSeriesToChart(damageShareChart, summonerName, userData, DiagramStats::getDamageShare);
        addSeriesToChart(damagePerGoldChart, summonerName, userData, DiagramStats::getDamagePerGold);
        addSeriesToChart(earlyCSDiffChart, summonerName, userData, DiagramStats::getCsDiffAtLaningEnd);
        addSeriesToChart(earlyGoldDiffChart, summonerName, userData, DiagramStats::getGoldDiffAtLaningEnd);
        addSeriesToChart(CSPerMinuteChart, summonerName, userData, DiagramStats::getCSPerMinute);
        addSeriesToChart(objectiveControlChart, summonerName, userData, DiagramStats::getObjectiveControlRatio);
        addSeriesToChart(visionPerHourChart, summonerName, userData, DiagramStats::getVisionScorePerHour);
        addSeriesToChart(roamDominanceChart, summonerName, userData, DiagramStats::getRoamDominanceScore);
        addSeriesToChart(roamDominanceChart, summonerName, userData, DiagramStats::getRoamDominanceScore);
        addSeriesToChart(killConversionChart, summonerName, userData, DiagramStats::getKillConversionRatio);
    }

    /**
     * Show the data for a specific summoner in the charts
     * The data is filtered according to the settings and then shown in the charts
     * And retrieved from the client if not already present
     *
     * @param summonerName name of the summoner for which to show data
     */
    public void showUserData(String summonerName) {
        StatsApiService statsApiService = new StatsApiService();
        BuddyApiService buddyApiService = new BuddyApiService();
        if (!nameToPUUID.containsKey(summonerName)) {
            nameToPUUID.put(summonerName, buddyApiService.getSummonerByName(summonerName).getPuuid());
            List<UserStatsDto> userStatsByPuuid = statsApiService.getUserStatsByPuuid(nameToPUUID.get(summonerName));
            userStatsByPuuid.sort(Comparator.comparing(UserStatsDto::getTimestamp).reversed());
            pUUIDToStats.put(nameToPUUID.get(summonerName), userStatsByPuuid);
        }
        String pUUID = nameToPUUID.get(summonerName);
        fillChartsForUser(summonerName, pUUIDToStats.get(pUUID));

    }

    @FXML
    public void initialize() {
        StatsApiService statsApiService = new StatsApiService();
        // TODO this should be the current logged in summoner
        showUserData("BADembrandt");
        List<UserStatsDto> otheruser = statsApiService.getUserStatsByPuuid("2bef99bb-3544-510a-a1d5-c184f89d67b0");
        otheruser.sort(Comparator.comparing(UserStatsDto::getTimestamp).reversed());
    }
}

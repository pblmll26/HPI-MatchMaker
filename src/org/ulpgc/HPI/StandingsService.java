package org.ulpgc.HPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StandingsService {

    public Standings generateStandings(League league) {
        List<Team> rows = new ArrayList<>(league.getTeams());

        rows.sort(Comparator
                .comparingInt(Team::getPoints).reversed()
                .thenComparing(Comparator.comparingInt(Team::getGoalDifference).reversed())
                .thenComparing(Comparator.comparingInt(Team::getGoalsFor).reversed())
                .thenComparing(Team::getName));

        return new Standings(rows);
    }
}

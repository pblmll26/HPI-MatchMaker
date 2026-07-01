package org.ulpgc.HPI;

import java.util.ArrayList;
import java.util.List;

public class League {

    private final List<Team> teams = new ArrayList<>();
    private final List<Match> matches = new ArrayList<>();

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public Team getTeam(int position) {
        return teams.get(position);
    }

    public int size() {
        return teams.size();
    }
}

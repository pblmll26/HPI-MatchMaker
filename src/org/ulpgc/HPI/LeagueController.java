package org.ulpgc.HPI;

public class LeagueController {

    private final ScoringRules rules;

    public LeagueController(ScoringRules rules) {
        this.rules = rules;
    }

    public void registerTeam(League league, String name) {
        league.addTeam(new Team(name));
    }

    public void addResult(League league, int homePosition, int awayPosition, int homeGoals, int awayGoals) {
        Team home = league.getTeam(homePosition);
        Team away = league.getTeam(awayPosition);

        league.addMatch(new Match(home, away, homeGoals, awayGoals));

        home.registerResult(homeGoals, awayGoals, rules);
        away.registerResult(awayGoals, homeGoals, rules);
    }
}

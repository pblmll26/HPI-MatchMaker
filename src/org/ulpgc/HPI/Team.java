package org.ulpgc.HPI;

public class Team {

    private final String name;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int points;

    public Team(String name) {
        this.name = name;
    }

    public void registerResult(int scored, int conceded, ScoringRules rules) {
        played++;
        goalsFor += scored;
        goalsAgainst += conceded;
        if (scored > conceded) {
            won++;
            points += rules.getPointsWin();
        } else if (scored == conceded) {
            drawn++;
            points += rules.getPointsDraw();
        } else {
            lost++;
            points += rules.getPointsLoss();
        }
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getDrawn() {
        return drawn;
    }

    public int getLost() {
        return lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalsFor - goalsAgainst;
    }

    public int getPoints() {
        return points;
    }
}

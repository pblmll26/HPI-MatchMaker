package org.ulpgc.HPI;

public class ScoringRules {

    private final int pointsWin;
    private final int pointsDraw;
    private final int pointsLoss;

    public ScoringRules(int pointsWin, int pointsDraw, int pointsLoss) {
        this.pointsWin = pointsWin;
        this.pointsDraw = pointsDraw;
        this.pointsLoss = pointsLoss;
    }

    public int getPointsWin() {
        return pointsWin;
    }

    public int getPointsDraw() {
        return pointsDraw;
    }

    public int getPointsLoss() {
        return pointsLoss;
    }
}

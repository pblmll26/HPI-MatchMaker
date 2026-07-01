package org.ulpgc.HPI;

import java.util.List;

public class Standings {

    private final List<Team> rows;

    public Standings(List<Team> rows) {
        this.rows = rows;
    }

    public List<Team> getRows() {
        return rows;
    }
}

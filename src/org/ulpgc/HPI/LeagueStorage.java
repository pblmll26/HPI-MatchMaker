package org.ulpgc.HPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeagueStorage {

    private final Path file;

    public LeagueStorage(String fileName) {
        this.file = Path.of(fileName);
    }

    public boolean exists() {
        return Files.exists(file);
    }

    public void delete() {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            System.out.println("Could not delete the league: " + e.getMessage());
        }
    }

    public void save(League league) {
        List<String> lines = new ArrayList<>();
        for (Team team : league.getTeams()) {
            lines.add("T;" + team.getName());
        }
        for (Match match : league.getMatches()) {
            lines.add("M;" + match.getHome().getName() + ";" + match.getAway().getName()
                    + ";" + match.getHomeGoals() + ";" + match.getAwayGoals());
        }
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            System.out.println("Could not save the league: " + e.getMessage());
        }
    }

    public League load(LeagueController controller) {
        League league = new League();
        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                if (line.startsWith("T;")) {
                    controller.registerTeam(league, line.substring(2));
                }
            }
            for (String line : lines) {
                if (line.startsWith("M;")) {
                    String[] parts = line.split(";");
                    int home = positionOf(league, parts[1]);
                    int away = positionOf(league, parts[2]);
                    controller.addResult(league, home, away,
                            Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load the league: " + e.getMessage());
        }
        return league;
    }

    private int positionOf(League league, String name) {
        for (int i = 0; i < league.size(); i++) {
            if (league.getTeam(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }
}

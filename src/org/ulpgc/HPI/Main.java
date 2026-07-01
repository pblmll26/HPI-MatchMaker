package org.ulpgc.HPI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ScoringRules rules = new ScoringRules(3, 1, 0);
        LeagueController controller = new LeagueController(rules);
        StandingsService service = new StandingsService();
        LeagueStorage storage = new LeagueStorage("league.txt");

        League league;
        if (storage.exists()) {
            league = storage.load(controller);
            System.out.println("Existing league loaded with " + league.size() + " teams.");
        } else {
            league = createLeague(sc, controller);
        }

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=== MATCHMAKER MAIN MENU ===");
            System.out.println("1. Add a match result");
            System.out.println("2. Show standings");
            System.out.println("3. Delete league and start a new one");
            System.out.println("4. Save and Exit");
            int option = readInt(sc, "Choose an option (1-4):", 1, 4);

            if (option == 1) {
                addResult(sc, league, controller);
            } else if (option == 2) {
                showStandings(service.generateStandings(league));
            } else if (option == 3) {
                storage.delete();
                league = createLeague(sc, controller);
            } else {
                storage.save(league);
                System.out.println("Data saved. Goodbye.");
                running = false;
            }
        }

        sc.close();
    }

    public static League createLeague(Scanner sc, LeagueController controller) {
        League league = new League();
        System.out.println("Welcome. Let's create the league.");
        while (true) {
            System.out.println("Enter a team name (or type 'DONE' to finish):");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("DONE")) {
                if (league.size() == 0) {
                    System.out.println("Register at least one team first.");
                    continue;
                }
                return league;
            }
            if (!name.isEmpty()) {
                controller.registerTeam(league, name);
            }
        }
    }

    public static void addResult(Scanner sc, League league, LeagueController controller) {
        for (int i = 0; i < league.size(); i++) {
            System.out.println((i + 1) + ". " + league.getTeam(i).getName());
        }
        int home = readInt(sc, "Enter the number of the HOME team:", 1, league.size()) - 1;
        int away = readInt(sc, "Enter the number of the AWAY team:", 1, league.size()) - 1;
        int homeGoals = readInt(sc, "Goals of " + league.getTeam(home).getName() + ":", 0, 999);
        int awayGoals = readInt(sc, "Goals of " + league.getTeam(away).getName() + ":", 0, 999);
        controller.addResult(league, home, away, homeGoals, awayGoals);
    }

    public static void showStandings(Standings standings) {
        System.out.printf("%-20s %3s %3s %3s %3s %3s %3s%n", "TEAM", "P", "W", "D", "L", "GD", "PTS");
        for (Team t : standings.getRows()) {
            System.out.printf("%-20s %3d %3d %3d %3d %3d %3d%n",
                    t.getName(), t.getPlayed(), t.getWon(), t.getDrawn(),
                    t.getLost(), t.getGoalDifference(), t.getPoints());
        }
    }

    public static int readInt(Scanner sc, String message, int min, int max) {
        while (true) {
            System.out.println(message);
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (Exception e) {
            }
            System.out.println("Introduce a number between " + min + " and " + max + ".");
        }
    }
}

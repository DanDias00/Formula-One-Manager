import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class Formula1ChampionshipManager implements ChampionshipManager{
    static Scanner sc = new Scanner(System.in);
    public static ArrayList<Formula1Driver> champion = new ArrayList<>();
    static int count = 0;
    Formula1Driver driver;

    public static void main(String[] args) {

        while (true) {
            printMenu();
            String choice = sc.next().toUpperCase();
            switch (choice) {
                case "A":
                    System.out.println("Enter driver name : ");
                    String dName = sc.next();
                    System.out.println("Enter driver team : ");
                    String dTeam = sc.next();
                    System.out.println("Enter driver location : ");
                    String location = sc.next();
                    String state = "yes";
                       /* for (int x = 0; x <=champion.size()+1; x++) {
                            if (champion.get(x).getDriverTeam().equals(dTeam)) {
                                state = "y";
                                break;
                            } else {
                                state = "no";
                            }
                        }*/
                    champion.add(new Formula1Driver(dName, dTeam, location));
                    count++;
                    championshipDrivers();
                    break;

                case "D":
                    System.out.println("Enter Driver name to delete : ");
                    String dname = sc.next();
                    System.out.println("Enter Driver team to delete : ");
                    String dteam = sc.next();
                    String state01 = "yes";
                    for (int i = 0; i < champion.size(); i++) {
                        if (champion.get(i).getDriverName().equals(dname) && (champion.get(i).getDriverTeam().equals(dteam))) {
                            champion.get(i).deleteDriverAndTeam();
                            state01 = "y";

                            break;

                        } else {
                            state01 = "no";
                            continue;
                        }


                    }
                    if (state01.equals("no")) {
                        System.out.println("No driver found");
                        break;
                    }
                    championshipDrivers();
                    break;

                case "U":
                    System.out.println("Enter team name to change driver : ");
                    String team = sc.next();
                    System.out.println("Enter new driver name : ");
                    String newName = sc.next();
                    String state0 = "yes";
                    for (int i = 0; i < champion.size(); i++) {
                        if (champion.get(i).getDriverTeam().equals(team)) {
                            champion.get(i).UpdateTeamDriver(newName);
                            state0 = "y";
                            break;
                        } else {
                            state0 = "no";
                            continue;
                        }
                    }
                    if (state0.equals("no")) {
                        System.out.println("No team found");
                        break;
                    }
                    championshipDrivers();
                    break;

                case "S":
                    System.out.println("Enter driver name to get satistics : ");
                    String statName = sc.next();
                    String state1 = "yes";
                    for (int i = 0; i < champion.size(); i++) {
                        if (champion.get(i).getDriverName().equals(statName)) {
                            champion.get(i).statistics();
                            state1 = "y";
                            break;
                        } else {
                            state1 = "no";
                            continue;
                        }
                    }
                    if (state1.equals("no")) {
                        System.out.println("No driver found");
                        break;
                    }
                    break;
                case "X":
                    System.out.println("Enter driver name to add stats : ");
                    String name = sc.next();
                    System.out.println("Enter first place positions : ");
                    int one = sc.nextInt();
                    System.out.println("Enter second place positions : ");
                    int two = sc.nextInt();
                    System.out.println("Enter third place positions : ");
                    int three = sc.nextInt();
                    System.out.println("total points : ");
                    int points = sc.nextInt();
                    System.out.println("Enter total races : ");
                    int races = sc.nextInt();
                    String state2 = "yes";
                    for (int i = 0; i <= champion.size(); i++) {
                        if (champion.get(i).getDriverName().equals(name)) {
                            champion.get(i).addStats(one, two, three, points, races);
                            state2 = "y";
                            break;
                        } else {
                            state2 = "no";
                            continue;
                        }
                    }
                    if (state2.equals("no")) {
                        System.out.println("No driver found");
                        break;
                    }
                    break;
                case "Z":
                    f1Table();



            }
        }


    }



    public static void printMenu() {
        System.out.println("\nMake a selection: ");
        System.out.println("A. Add a driver to championship");
        System.out.println("D. Delete driver and team");
        System.out.println("U. Change driver from existing team.");
        System.out.println("S .Display statistics of a driver.");
        System.out.println("F .Display F1 table.");
        System.out.println("R .Add a race completed");
        System.out.println("S .Store program.");
        System.out.println("L . Load program");
        System.out.println("E. Exit\n");
    }


    public static void championshipDrivers() {
        System.out.println("Drivers participating in the F1 championship 2021");
        for (Formula1Driver driver : champion) {
            if (!(driver.getDriverTeam().equals("empty"))) {
                System.out.println("Driver name: " + driver.getDriverName() + "|| Driver Team : " + driver.getDriverTeam());
            }
        }
    }


    public static void f1Table() {
        Collections.sort(champion,Collections.reverseOrder());
        for (Formula1Driver s: champion) {
            System.out.println(s.getDriverName()+ " " + s.getTotalPoints()
                    + " " + s.getDriverTeam());
        }


    }
}





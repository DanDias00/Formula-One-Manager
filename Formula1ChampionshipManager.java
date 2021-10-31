import java.util.Scanner;


public class Formula1ChampionshipManager implements ChampionshipManager {
    static Scanner sc = new Scanner(System.in);
    public static Formula1Driver[] champion = new Formula1Driver[10];
    static int count = 0;

    public static void main(String[] args) {


        Formula1Driver driver;
        initialise();


        while (true) {
            printMenu();
            String choice = sc.next().toUpperCase();
            switch (choice) {
                case "A":
                    for (int i = 0; i < champion.length; i++) {
                        System.out.println("Enter driver name : ");
                        String dName = sc.next();
                        System.out.println("Enter driver team : ");
                        String dTeam = sc.next();
                        String state = "yes";
                        for (int x = 0; x < champion.length; x++) {
                            if (champion[x].getDriverTeam().equals(dTeam)) {
                                state = "y";
                                break;
                            } else {
                                state = "no";
                            }
                        }
                        if (!(state.equals("y"))) {
                            champion[count].addDriver(dName, dTeam);
                            count++;
                            System.out.println("Add another driver? y/n");
                            String answer = sc.next().toUpperCase();
                            if (answer.equals("N")) {
                                break;
                            }
                        } else {
                            System.out.println("Team already there");
                            break;
                        }

                    }
                    championshipDrivers();
                    break;

                case "D":
                    System.out.println("Enter Driver name to delete : ");
                    String dName = sc.next();
                    System.out.println("Enter Driver team to delete : ");
                    String dTeam = sc.next();
                    for (int i = 0; i < champion.length; i++) {
                        if (champion[i].getDriverName().equals(dName) && (champion[i].getDriverTeam().equals(dTeam))) {
                            champion[i].deleteDriverAndTeam();
                            break;

                        } else {
                            continue;
                        }

                    }
                    championshipDrivers();
                    break;
                case "U":
                    System.out.println("Enter team name to change driver : ");
                    String team = sc.next();
                    System.out.println("Enter new driver name : ");
                    String newName = sc.next();
                    for (int i = 0; i < champion.length; i++) {
                        if (champion[i].getDriverTeam().equals(team)) {
                            champion[i].UpdateTeamDriver(newName);
                            break;
                        } else {
                            continue;
                        }
                    }
                    championshipDrivers();
                    break;
                case "S":
                    System.out.println("Enter driver name to get satistics : ");
                    String statName = sc.next();
                    String state="yes";
                    for(int i=0;i<champion.length;i++){
                        if(champion[i].getDriverName().equals(statName)){
                            champion[i].statistics();
                            state="y";
                            break;
                        }else {
                            state="no";
                            continue;
                        }
                    }
                    if(state.equals("no")){
                        System.out.println("No driver found");
                        break;
                    }

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


    public static void initialise() {
        //printing out all of the objects with their default value that is empty
        for (int i = 0; i < champion.length; i++) {
            champion[i] = new Formula1Driver("empty", "empty");
            // System.out.println("this position " + (i+1) + " is taken by the team " + champion[i].getDriverTeam());

        }

    }

    public static void championshipDrivers() {
        System.out.println("Drivers participating in the F1 championship 2021");
        for (Formula1Driver driver : champion) {
            if (!(driver.getDriverTeam().equals("empty"))) {
                System.out.println("Driver name: " + driver.getDriverName() + "|| Driver Team : " + driver.getDriverTeam());
            }
        }
    }


}

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;;


public class Formula1ChampionshipManager implements ChampionshipManager {

    static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    public static ArrayList<Formula1Driver> championDrivers = new ArrayList<>();
    public static ArrayList<Race> races = new ArrayList<>();
    public static HashMap<Integer, String> perRaceMap = new HashMap<>();


    @Override
    public void printMenu() {
        System.out.println("\nMake a selection: ");
        System.out.println("A. Add a driver to championship");
        System.out.println("D. Delete driver and team");
        System.out.println("U. Change driver from existing team.");
        System.out.println("S .Display statistics of a driver.");
        System.out.println("F .Display F1 table.");
        System.out.println("R .Add a race completed");
        System.out.println("X .Add a driver stats");
        System.out.println("S .Store program.");
        System.out.println("L . Load program");
        System.out.println("E. Exit\n");
    }

    @Override
    public void addDriver() {
        System.out.println("Enter driver name ");
        String dName = sc.next().toUpperCase();
        dName += sc.nextLine();
        while (!dName.matches("^[a-zA-Z_ ]*$")) { //checking if input is only string
            System.out.println("Please enter a string input only");
            System.out.println("Enter driver name : ");
            dName = sc.nextLine().toUpperCase();

        }
        String driverState = "no";
        for (int i = 0; i < championDrivers.size(); i++) {
            if (dName.equals(championDrivers.get(i).getDriverName())) { //checking if driver name already exist
                driverState = "y";
                break;
            } else {
                driverState = "no";
            }
        }
        if (driverState == "y") {
            System.out.println("Sorry driver with same name exist..try a different name");
        } else {

            System.out.println("Enter driver team : ");
            String dTeam = sc.next().toUpperCase();
            dTeam += sc.nextLine();
            while (!dTeam.matches("^[a-zA-Z_ ]*$")) {
                System.out.println("Please enter a string input only");
                System.out.println("Enter driver team : ");
                dTeam = sc.nextLine().toUpperCase();
            }

            String teamState = "no";
            for (int x = 0; x < championDrivers.size(); x++) {
                if (dTeam.equals(championDrivers.get(x).getDriverTeam())) { //checking if team already has a driver
                    teamState = "y";
                    break;
                } else {
                    teamState = "no";
                }
            }

            if (teamState == "y") {
                System.out.println("Sorry team already has a driver...try again");
            } else {

                System.out.println("Enter driver location : ");
                String location = sc.next().toUpperCase();
                dTeam += sc.nextLine();
                while (!location.matches("^[a-zA-Z_ ]*$")) {
                    System.out.println("Please enter a string input only");
                    System.out.println("Enter driver location : ");
                    location = sc.nextLine().toUpperCase();
                }

                championDrivers.add(new Formula1Driver(dName, dTeam, location));//adding a driver

            }
        }
    }

    @Override
    public void update() {
        System.out.println("Enter team name to change driver : ");
        String team = sc.next().toUpperCase();
        team += sc.nextLine();
        while (!team.matches("^[a-zA-Z_ ]*$")) {
            System.out.println("Please enter string input only");
            System.out.println("Enter team name to change driver : ");
            team = sc.nextLine().toUpperCase();
        }
        System.out.println("Enter new driver name : ");
        String newName = sc.next().toUpperCase();
        newName += sc.nextLine();
        while (!newName.matches("^[a-zA-Z_ ]*$")) {
            System.out.println("Please enter a string input only");
            System.out.println("Enter new driver name : ");
            newName = sc.nextLine().toUpperCase();
        }
        String driverList = "check";
        if (championDrivers.isEmpty()) {
            System.out.println("Driver list is empty. No driver to update");
        }
        for (int i = 0; i < championDrivers.size(); i++) {
            try {
                if (championDrivers.get(i).getDriverTeam().equals(team)) { //checking for driver in a team
                    championDrivers.get(i).setDriverName(newName); //setting new driver to existing team
                    driverList = "y";
                    break;
                } else {
                    driverList = "n";
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Error");
            }

        }
        if (driverList.equals("n")) {
            System.out.println("team entered wrong..try again");
        }
    }

    @Override
    public void deleteDriverTeam() {

        System.out.println("Enter driver name to delete : ");
        String dname = sc.next().toUpperCase();
        dname += sc.nextLine();
        while (!dname.matches("^[a-zA-Z_ ]*$")) {
            System.out.println("Please enter a string input only");
            System.out.println("Enter driver name to delete : ");
            dname = sc.nextLine().toUpperCase();
        }
        System.out.println("Enter Driver team to delete : ");
        String dTeam = sc.next().toUpperCase();
        dTeam += sc.nextLine();
        while (!dTeam.matches("^[a-zA-Z_ ]*$")) {
            System.out.println("Please enter a string input only");
            System.out.println("Enter Driver team to delete : ");
            dTeam = sc.nextLine().toUpperCase();
        }
        String state01 = "check";
        if (championDrivers.isEmpty()) {
            System.out.println("Driver list is empty.No drivers to delete");
        }
        for (int i = 0; i < championDrivers.size(); i++) {
            if (championDrivers.get(i).getDriverName().equals(dname) && (championDrivers.get(i).getDriverTeam().equals(dTeam))) {
                championDrivers.remove(i);
                state01 = "y";

                break;

            } else {
                state01 = "n";
                continue;
            }

        }
        if (state01.equals("n")) {
            System.out.println("Driver name or team added incorrectly");
        }

    }

    public void championshipDrivers() {
        System.out.println("Drivers participating in the F1 championship 2021");
        for (Formula1Driver driver : championDrivers) {
            if (!(driver.getDriverTeam().equals("empty"))) {
                System.out.println("Driver name: " + driver.getDriverName() + "| Driver Team : " + driver.getDriverTeam());
            }
        }
    }

    @Override
    public void addStats() {
        int one = 0;
        int two = 0;
        int three = 0;
        int points = 0;
        int races = 0;

        System.out.println("Enter driver name to add stats : ");
        String name = sc.next().toUpperCase();
        name += sc.nextLine();
        System.out.println("Enter first place positions : ");
        try {
            one = sc.nextInt();
            System.out.println("Enter second place positions : ");
            two = sc.nextInt();
            System.out.println("Enter third place positions : ");
            three = sc.nextInt();
            System.out.println("total points : ");
            points = sc.nextInt();
            System.out.println("Enter total races : ");
            races = sc.nextInt();
        } catch (InputMismatchException e) {
        }
        if (championDrivers.isEmpty()) {
            System.out.println("No driver list.Cannot add driver stat");
        }
        String driverCheck = "yes";

        for (int i = 0; i < championDrivers.size(); i++) {
            if (championDrivers.get(i).getDriverName().equals(name)) {
                championDrivers.get(i).addStats(one, two, three, points, races);
                driverCheck = "y";
                break;
            } else {
                driverCheck = "no";
                continue;
            }
        }
        if (driverCheck.equals("no")) {
            System.out.println("No driver found");
        }
    }

    @Override
    public void displayStats() {

        System.out.println("Enter driver name to get statistics : ");
        String statName = sc.next().toUpperCase();
        statName += sc.nextLine();
        while (!statName.matches("^[a-zA-Z_ ]*$")) {
            System.out.println("Please enter a valid driver name");
            System.out.println("Enter driver name to get statistics : ");
            statName = sc.nextLine().toUpperCase();
        }
        if (championDrivers.isEmpty()) {
            System.out.println("Driver list is empty.No driver found");
        }
        String driverCheck = "yes";
        for (int i = 0; i < championDrivers.size(); i++) {
            if (championDrivers.get(i).getDriverName().equals(statName)) {
                championDrivers.get(i).statistics();
                driverCheck = "y";
                break;
            } else {
                driverCheck = "no";
                continue;
            }
        }
        if (driverCheck.equals("no")) {
            System.out.println("No driver found");
        }

    }

    @Override
    public void race() throws ParseException {
        Calendar cal = Calendar.getInstance();

        //https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html

        HashMap<Integer, String> driverMap = new HashMap<>();
        Date date1;

        boolean active = false;
        //implements race date
        if (championDrivers.size() < 10) {
            System.out.println("Cannot start race with less than 10 drivers");
        } else {

            Window[] y = Window.getWindows();//check if gui window frame is active
            for (int i = 0; i < y.length; i++) {
                if (y[i].getName().equals("Driver championship table")) ;
                active = true;
                break;
            }
            while (true)
                if (active) { //if active date is generated automatically
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                    date1 = new Date();
                    System.out.println(formatter.format(date1));
                    break;

                } else { //manually enter date
                    System.out.println("Add race date in the format dd-MMM-yyyy HH:mm:ss");
                    String date = sc.next();
                    try {

                        SimpleDateFormat date2 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                        date1 = date2.parse(date);



                        Collections.shuffle(championDrivers); //shuffling the drivers names

                        for (int i = 0; i < championDrivers.size(); i++) {
                            driverMap.put(i, championDrivers.get(i).getDriverName()); //adding the random driver names to a hashmap
                        }

                        perRaceMap.putAll(driverMap); //copying the hashmap to static  hashMap
                        races.add(new Race(date1, driverMap));//adding the race hashmap to race class alongside the date
                        statUpdate();//calling the update stat method
                        break;
                    } catch (Exception e) {
                        System.out.println("Incorrect format entered");
                    }


                }



                    // System.out.println(championDrivers);


        }
    }



    @Override
    public void showRaces() {

        try {
            for (int i = 0; i < races.size(); i++) { //printing out the race hashmaps in the race class
                System.out.println("Race " + (i + 1));//displaying race number
                System.out.println(races.get(i).getDate()); //displaying date of race
                races.get(i).getRaceMap().forEach((key, value) -> System.out.println("Position " + ((int) key + 1) + " " + value));//displaying races in the race class

                System.out.println("-------------------------------------");

            }
        } catch (Exception e) {
            System.out.println("Error occurred in show races");
        }


    }

    @Override
    public void statUpdate() {
/*
        try {
            for (int i = 0; i < championDrivers.size(); i++) {
                championDrivers.set((i), championDrivers.get(listOfDriverValues.get(i))); //assigning the values of the array to the driver array
                //  System.out.println(championDrivers.get(i));
            }
        } catch (Exception e) {
            System.out.println("Error occurred in stat update");
          //  System.out.println(championDrivers.size());
        }*/
        //adding the stats to drivers according to their position
        if (championDrivers.size() < 10) {

        } else {
            try {
                championDrivers.get(0).addStats(1, 0, 0, 25, 1);
                championDrivers.get(1).addStats(0, 1, 0, 18, 1);
                championDrivers.get(2).addStats(0, 0, 1, 15, 1);
                championDrivers.get(3).addStats(0, 0, 0, 12, 1);
                championDrivers.get(4).addStats(0, 0, 0, 10, 1);
                championDrivers.get(5).addStats(0, 0, 0, 8, 1);
                championDrivers.get(6).addStats(0, 0, 0, 6, 1);
                championDrivers.get(7).addStats(0, 0, 0, 4, 1);
                championDrivers.get(8).addStats(0, 0, 0, 2, 1);
                championDrivers.get(9).addStats(0, 0, 0, 1, 1);
                System.out.println("Stats updated");

                for (int i = 0; i < championDrivers.size(); i++) {
                    if (i > 9) {
                        championDrivers.get(i).addStats(0, 0, 0, 0, 1);
                        System.out.println(championDrivers.get(i).getDriverName());
                        //  System.out.println("hi");
                        System.out.println(championDrivers.size());
                        continue;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error!! unable to conduct the race. Need at least 10 drivers to start a race");
            }

        }
    }

    @Override
    public void f1Table() {
        Collections.sort(championDrivers);

        if (championDrivers.isEmpty()) {
            System.out.println("No drivers to show in table");
        } else {
            System.out.println(String.format("%20s %10s %20s %11s %20s %10s %20s  %9s %20s  %13s %20s  %10s %10s", "Driver name ", "|", "Driver Team ", "|", "Total points ", "|", "First positions", "|", "Second positions ", "|", "Third position ", "|", "Total races "));
            System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"));


            for (Formula1Driver f1 : championDrivers) {
                System.out.println(String.format("%-20s %10s  %-20s %10s %20d %10s %20s %10s %20s %14s %10d %21s %8s ", f1.getDriverName(), "|", f1.getDriverTeam(), "|",
                        f1.getTotalPoints(), "|", f1.getFirstPosition(), "|", f1.getSecondPosition(), "|", f1.getThirdPosition(), "|", f1.getTotalRaces()));

            }

        }
    }

    @Override
    public void Store() throws IOException {
        File file = new File("DriverDetails.txt");

        FileOutputStream output = new FileOutputStream(file);
        ObjectOutputStream driverObj = new ObjectOutputStream(output);

        for (int i = 0; i < championDrivers.size(); i++) {
            driverObj.writeObject(championDrivers.get(i));
        }


        driverObj.close();
        output.close();
        System.out.println(" Driver written successfully");

        //Adding races to save method

        File raceFile = new File("Races.txt");

        FileOutputStream raceOutput = new FileOutputStream(raceFile);
        ObjectOutputStream raceObj = new ObjectOutputStream(raceOutput);


        for (int i = 0; i < races.size(); i++) {
            raceObj.writeObject(races.get(i));
        }

        raceObj.close();
        output.close();
        System.out.println("Written race successfully");

    }

    @Override
    public void Load() throws IOException {
        FileInputStream driverInput = new FileInputStream("DriverDetails.txt");
        ObjectInputStream driverObj = new ObjectInputStream(driverInput);
        while (true) {
            try {
                Formula1Driver f1 = (Formula1Driver) driverObj.readObject();

                championDrivers.add(f1);

                System.out.println(f1);


            } catch (IOException | ClassNotFoundException e) {
                break;
            }


        }

        try {

            FileInputStream raceInput = new FileInputStream("Races.txt");
            ObjectInputStream raceObj = new ObjectInputStream(raceInput);


            while (raceInput.available() != 0) {
                try {
                    Race r1 = (Race) raceObj.readObject();

                    races.add(r1);

                    System.out.println(r1);


                }  catch (IOException | ClassNotFoundException e) {

                    break;
                }


            }


        }catch (EOFException e) {

        }
    }
}





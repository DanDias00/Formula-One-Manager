import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class testClass extends Thread{
    static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();



    public static void main(String[] args) {

     //   new Thread(() -> test.main(null)).start();




        while (true) {
            manager.printMenu();
            String choice = sc.next().toUpperCase();
            switch (choice) {
                case "A":
                    manager.addDriver();
                    manager.championshipDrivers();
                    break;

                case "D":
                    manager.deleteDriverTeam();
                    break;

                case "U":
                    manager.update();
                    manager.championshipDrivers();
                    break;

                case "DS":
                    manager.displayStats();

                    break;
                case "X":
                    manager.addStats();
                    break;
                case "F":
                    manager.f1Table();
                    break;

                case "R":
                    try {
                        manager.race();
                    } catch (ParseException e) {
                        System.out.println("Enter proper date format");
                    }
                    break;
                case "S":
                    try {
                        manager.Store();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "L":
                    try {
                        manager.Load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "SR":
                    manager.showRaces();
                    break;
                case "G":
                    new Thread(() -> Swing.main(null)).start();//starting a new thread to run the swing implementation


                break;

                default:
                    System.out.println("Invalid input..try again");

            }
        }

    }
}

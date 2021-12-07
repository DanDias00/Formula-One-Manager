import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class testClass extends Thread {
    static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    static Formula1ChampionshipManager manager = new Formula1ChampionshipManager();


    public static void main(String[] args) throws IOException {
        manager.Load();

        while (true) {

            manager.printMenu();

            String choice = sc.next().toUpperCase();
            switch (choice) {
                case "A":
                    manager.addDriver();
                    break;
                case "D":
                    manager.deleteDriverTeam();
                    break;
                case "U":
                    manager.update();
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
                        manager.race(null);
                    } catch (ParseException e) {
                        System.out.println("Enter proper date format");
                    }
                    break;
                case "S":
                    manager.Store();
                    break;

                case "L":
                    manager.Load();
                    break;
                case "SR":
                    manager.showRaces();
                    break;
                case "G":
                    new Swing();
                    break;
                case "E":
                    System.out.println("You decided to leave..Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input..try again");

            }
        }

    }
}

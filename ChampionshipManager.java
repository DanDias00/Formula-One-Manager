
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;


public interface ChampionshipManager {

 void printMenu();
 void addDriver();
 void update();
 void deleteDriverTeam();
 void addStats();
void displayStats();
 void race(LocalDate dateInput) throws ParseException;
 void showRaces();
 void statUpdate();
 void f1Table();
 void Store() throws IOException;
 void Load() throws IOException;

}

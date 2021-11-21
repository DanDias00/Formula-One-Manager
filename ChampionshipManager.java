import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public interface ChampionshipManager {

 void printMenu();
 void addDriver();
 void update();
 void deleteDriverTeam();
 void championshipDrivers();
 void addStats();
void displayStats();
void race() throws ParseException;
void showRaces();
 void statUpdate();
 void f1Table();
 void Store() throws IOException;
 void Load() throws IOException;

}

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class Race implements Serializable {
    private LocalDate date;
    private HashMap raceMap;

    public Race(LocalDate date, HashMap raceMap) {
        this.date = date;
        this.raceMap = raceMap;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Race{" +
                "date=" + date +
                ", raceMap=" + raceMap +
                '}';
    }

    public HashMap getRaceMap() {
        return raceMap;
    }

}
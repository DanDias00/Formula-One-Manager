import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class Race implements Serializable {
    private  Date date;
    private HashMap raceMap;

    public Race(Date date, HashMap raceMap) {
        this.date = date;
        this.raceMap = raceMap;
    }



    public Date getDate() {
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
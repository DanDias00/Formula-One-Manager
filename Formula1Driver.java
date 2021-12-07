import java.io.Serializable;

public class Formula1Driver extends Driver implements Comparable, Serializable {

    private String driverTeam;
    private int totalPoints;
    private int totalRaces;
    private int firstPosition;
    private int secondPosition;
    private int thirdPosition;

    public Formula1Driver(String driverName, String driverTeam, String location) {
        super(driverName, location);
        this.driverTeam = driverTeam;
        this.totalPoints = 0;
        this.totalRaces = 0;
        this.firstPosition = 0;
        this.secondPosition = 0;
        this.thirdPosition = 0;
    }


    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int points) {

    }

    public int getTotalRaces() {
        return totalRaces;
    }

    public void setTotalRaces(int races) {
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int position) {
        firstPosition = firstPosition + position;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(int position) {

        secondPosition = secondPosition + position;
    }

    public int getThirdPosition() {
        return thirdPosition;
    }

    public void setThirdPosition(int position) {

        thirdPosition = thirdPosition + position;
    }

    public void statistics() {
        System.out.println("Statistics of " + getDriverTeam() + " driver , " + getDriverName());
        System.out.println("First place positions : " + getFirstPosition());
        System.out.println("second place positions : " + getSecondPosition());
        System.out.println("third place positions : " + getThirdPosition());
        System.out.println("Total points : " + getTotalPoints());
        System.out.println("Total races : " + getTotalRaces());

    }

    public void addStats(int one, int two, int three, int points, int races) {

        totalPoints = totalPoints + points;
        this.setTotalPoints(totalPoints);
        totalRaces = totalRaces + races;
        this.setTotalRaces(totalRaces);
        this.setFirstPosition(one);
        this.setSecondPosition(two);
        this.setThirdPosition(three);
    }


    @Override
    public String toString() {
        return "Formula1Driver{" +
                "driverTeam='" + driverTeam + '\'' +
                ", totalPoints=" + totalPoints +
                ", totalRaces=" + totalRaces +
                ", firstPosition=" + firstPosition +
                ", secondPosition=" + secondPosition +
                ", thirdPosition=" + thirdPosition +
                '}';
    }

    @Override
    public int compareTo(Object obj) {
        int points = ((Formula1Driver) obj).getTotalPoints();
        if (points == this.totalPoints) {
            int position = ((Formula1Driver) obj).getFirstPosition();
            return position - this.firstPosition;
        } else {
            return points - this.totalPoints;
        }
    }

}

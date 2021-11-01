import java.util.Comparator;


public class Formula1Driver extends Driver implements Comparator<Formula1Driver> {

    private String driverTeam;
    private int totalPoints;
    private int totalRaces;
    private int firstPosition;
    private int secondPosition;
    private int thirdPosition;

    public Formula1Driver(String driverName, String driverTeam, String location){
        super(driverName,location);
        this.driverTeam=driverTeam;
        this.totalPoints = 0;
        this.totalRaces = 0;
        this.firstPosition = 0;
        this.secondPosition = 0;
        this.thirdPosition = 0;
    }

    public Formula1Driver(String driverName, String location) {
        super(driverName, location);
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

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getTotalRaces() {
        return totalRaces;
    }

    public void setTotalRaces(int totalRaces) {
        this.totalRaces = totalRaces;
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(int firstPosition) {
        this.firstPosition = firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setSecondPosition(int secondPosition) {
        this.secondPosition = secondPosition;
    }

    public int getThirdPosition() {
        return thirdPosition;
    }

    public void setThirdPosition(int thirdPosition) {
        this.thirdPosition = thirdPosition;
    }

    public void statistics() {
        System.out.println("Statistics of " + getDriverTeam() + " driver , " + getDriverName());
        System.out.println("First place positions : " + getFirstPosition());
        System.out.println("First place positions : " + getSecondPosition());
        System.out.println("First place positions : " + getThirdPosition());
        System.out.println( "Total points : " + getTotalPoints());
        System.out.println("Total races : " + getTotalRaces());

    }


    public void deleteDriverAndTeam(){
        this.setDriverTeam("empty");
        this.setDriverTeam("empty");
        System.out.println("Deleted successfully");
    }

    public void UpdateTeamDriver(String name){
        this.setDriverName(name);
        System.out.println("Driver updated");

    }

    public void addStats(int one,int two, int three, int points,int races){
        this.setFirstPosition(one);
        this.setSecondPosition(two);
        this.setThirdPosition(three);
        this.setTotalPoints(points);
        this.setTotalRaces(races);
        System.out.println("All set");
    }


    @Override
    public int compare(Formula1Driver o1, Formula1Driver o2) {

        int point1 = o1.getTotalPoints();
        int point2 = o2.getTotalPoints();
        return point2-point1;
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
}

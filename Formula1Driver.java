public class Formula1Driver extends Driver{
    private String driverName;
     private String driverTeam;
    private String location;

    private int totalPoints;
    private int totalRaces;
    private int firstPosition;
    private int secondPosition;
    private int thirdPosition;

    public Formula1Driver(String driverName, String driverTeam) {
        this.driverName = driverName;
        this.driverTeam = driverTeam;
    }

    @Override
    public void setDriverName(String name) {
        this.driverName=name;
    }

    public String getDriverName() {
        return driverName;
    }

    @Override
    public void setLocation(String location) {

    }

    @Override
    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public String getLocation() {
        return location;
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

    @Override
    public void statistics() {
        System.out.println("Statistics of " + getDriverTeam() + " driver , " + getDriverName());
        System.out.println("First place positions : " + this.getFirstPosition());
        System.out.println("First place positions : " + getSecondPosition());
        System.out.println("First place positions : " + getThirdPosition());
        System.out.println( "Total points : " + getTotalPoints());
        System.out.println("Total races : " + getTotalRaces());

    }


    public void addDriver(String name, String team){
        if(this.getDriverName().equals("empty")){
            this.setDriverName(name);
            this.setDriverTeam(team);
            System.out.println("added");
        }else{
            System.out.println("Already assigned");
        }
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
}

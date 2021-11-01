public abstract class Driver {

    private String driverName;
    private String location;

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public Driver(String driverName, String location) {
        this.driverName = driverName;
        this.location = location;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




}

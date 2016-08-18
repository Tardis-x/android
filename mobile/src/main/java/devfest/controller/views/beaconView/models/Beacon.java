package devfest.controller.views.beaconView.models;

public class Beacon {

    private int id;
    private double xCoordinate;
    private double yCoordinate;
    private double deviceDistToBeacon;

    public Beacon() {
    }

    public Beacon(double xCoordinate, double yCoordinate, double deviceDistToBeacon) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.deviceDistToBeacon = deviceDistToBeacon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getDeviceDistToBeacon() {
        return deviceDistToBeacon;
    }

    public void setDeviceDistToBeacon(double deviceDistToBeacon) {
        this.deviceDistToBeacon = deviceDistToBeacon;
    }
}

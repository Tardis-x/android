package devfest.controller.views.beaconView.models;

import java.util.List;

public class BeaconResponse {

    private double roomHeight;
    private double roomWidth;

    private List<Beacon> beaconList;

    public BeaconResponse() {

    }

    public BeaconResponse(double roomHeight, double roomWidth, List<Beacon> beaconList) {
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
        this.beaconList = beaconList;
    }

    public double getRoomHeight() {
        return roomHeight;
    }

    public void setRoomHeight(double roomHeight) {
        this.roomHeight = roomHeight;
    }

    public double getRoomWidth() {
        return roomWidth;
    }

    public void setRoomWidth(double roomWidth) {
        this.roomWidth = roomWidth;
    }

    public List<Beacon> getBeaconList() {
        return beaconList;
    }

    public void setBeaconList(List<Beacon> beaconList) {
        this.beaconList = beaconList;
    }
}

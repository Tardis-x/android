package devfest.controller.views.beaconView;

import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import devfest.controller.views.beaconView.models.Beacon;
import devfest.controller.views.beaconView.models.BeaconResponse;
import devfest.controller.views.beaconView.models.Coordinates;
import devfest.controller.views.beaconView.utils.BeaconJsonDecoder;
import devfest.controller.views.beaconView.utils.Triangulation;
import devfest.controller.views.beaconView.views.BeaconView;

public class BeaconViewProcessor {

    private double viewHeight;
    private double viewWidth;

    private BeaconView beaconView;
    private BeaconResponse beaconResponse;

    public BeaconViewProcessor(BeaconView beaconView, String beaconResponse) {
        this.beaconView = beaconView;
        this.beaconResponse = BeaconJsonDecoder.decodeBeacon(beaconResponse);
        viewHeight = beaconView.getHeight();
        viewWidth = beaconView.getWidth();
    }

    public void draw() {
//        beaconView.setLength(480);
//        beaconView.setWidth(720);
        beaconView.setStrokeColor(Color.BLACK);
        beaconView.setBeaconsCoordinates(getBeaconsCoordinates(beaconResponse));
        beaconView.setUserCoordinates(getUserLocation(beaconResponse.getBeaconList()));
    }

    private List<Coordinates> getBeaconsCoordinates(BeaconResponse beaconResponse) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        Log.d("beaconResponse", beaconResponse.toString());
        for (Beacon beacon : beaconResponse.getBeaconList())
            coordinatesList.add(new Coordinates(
                    getRelativeCoordinate(viewWidth, beaconResponse.getRoomWidth(), beacon.getxCoordinate()),
                    getRelativeCoordinate(viewHeight, beaconResponse.getRoomHeight(), beacon.getyCoordinate())));

        return coordinatesList;
    }

    private Coordinates getUserLocation(List<Beacon> beaconResponseList) {
        return triangulate(getNearestTwoBeacons(beaconResponseList));
    }

    private List<Beacon> getNearestTwoBeacons(List<Beacon> beacons) {
        Collections.sort(beacons, new Comparator<Beacon>() {
            @Override
            public int compare(Beacon beacon1, Beacon beacon2) {
                if (beacon1.getDeviceDistToBeacon() < beacon2.getDeviceDistToBeacon())
                    return -1;
                else if (beacon1.getDeviceDistToBeacon() == beacon2.getDeviceDistToBeacon())
                    return 0;
                else
                    return 1;
            }
        });

        for (Beacon beacon : beacons)
            Log.d("Sorted beacon distance", String.valueOf(beacon.getDeviceDistToBeacon()));

        List<Beacon> nearestBeacons = new ArrayList<>();
        nearestBeacons.add(beacons.get(0));
        nearestBeacons.add(beacons.get(1));

        return nearestBeacons;
    }

    private Coordinates triangulate(List<Beacon> beaconResponseList) {
        Triangulation triangulation = new Triangulation(beaconResponseList.get(0), beaconResponseList.get(1));
        return new Coordinates(triangulation.getX(), triangulation.getY());
    }

    private double getRelativeCoordinate(double viewSideLength, double realSideLength, double realCoordinate) {
        return (viewSideLength / realSideLength) * realCoordinate;
    }
}

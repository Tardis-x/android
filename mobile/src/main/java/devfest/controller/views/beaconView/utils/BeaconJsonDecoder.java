package devfest.controller.views.beaconView.utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import devfest.controller.views.beaconView.models.Beacon;
import devfest.controller.views.beaconView.models.BeaconResponse;

public class BeaconJsonDecoder {

    public static BeaconResponse decodeBeacon(String json) {
        BeaconResponse beaconResponse = new BeaconResponse();
        try {
            JSONObject jsonObject = new JSONObject(json);
            beaconResponse.setRoomHeight(jsonObject.getDouble("roomHeight"));
            beaconResponse.setRoomWidth(jsonObject.getDouble("roomWidth"));
            beaconResponse.setBeaconList(decodeBeaconList(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return beaconResponse;
    }

    private static List<Beacon> decodeBeaconList(JSONObject jsonObject) throws JSONException {
        JSONArray jsonBeaconsArray = jsonObject.getJSONArray("beaconList");
        List<Beacon> beaconList = new ArrayList<>();
        for (int i = 0; i < jsonBeaconsArray.length(); i++) {
            JSONObject jsonBeacon = jsonBeaconsArray.getJSONObject(i);
            Beacon beacon = new Beacon();
            beacon.setId(jsonBeacon.getInt("id"));
            beacon.setxCoordinate(jsonBeacon.getDouble("xCoordinate"));
            beacon.setyCoordinate(jsonBeacon.getDouble("yCoordinate"));
            beacon.setDeviceDistToBeacon(jsonBeacon.getDouble("distance"));
            beaconList.add(beacon);
        }
        return beaconList;
    }
}

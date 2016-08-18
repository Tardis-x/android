package devfest.controller.views.beaconView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadMe {

    /**
     * To init view you should do following:
     * <p>
     * BeaconViewProcessor beaconViewProcessor = new BeaconViewProcessor(
     *      (BeaconView) findViewById(R.id.bcv), mockJson());
     * beaconViewProcessor.draw();
     *
     */

    private String mockJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("roomHeight", 10);
            jsonObject.put("roomWidth", 20);

            JSONArray jsonArray = new JSONArray();
            jsonArray.put(getJsonObject(1, 1, 1, 7.6));
            jsonArray.put(getJsonObject(2, 11, 1, 4.4));
            jsonArray.put(getJsonObject(3, 11, 11, 7.8));

            jsonObject.put("beaconList", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private JSONObject getJsonObject(int id, double xCoord, double yCoord, double distance) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("xCoordinate", xCoord);
        jsonObject.put("yCoordinate", yCoord);
        jsonObject.put("distance", distance);

        return jsonObject;
    }
}

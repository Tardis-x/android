package devfest.controller.utils;

import android.text.TextUtils;

/**
 * Created by oliver on 25/07/16.
 */
public class Utils {

    public static String capitalizeName(String name) {
        if (TextUtils.isEmpty(name))
            return name;

        String[] nameParts = name.split(" ");
        String result = "";
        for (String namePart: nameParts) {
            if (namePart.length()>1) {
                result += namePart.substring(0,1).toUpperCase() + namePart.substring(1).toLowerCase() + " ";
            } else {
                result += namePart.toUpperCase() + " ";
            }
        }
        return  result.trim();
    }
}

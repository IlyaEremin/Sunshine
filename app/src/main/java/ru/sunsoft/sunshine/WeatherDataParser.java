package ru.sunsoft.sunshine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by IlyaSun on 7/27/2014.
 */
public class WeatherDataParser {

    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex) throws JSONException {
        JSONObject jsonObject = new JSONObject(weatherJsonStr);
        JSONArray weatherList = jsonObject.getJSONArray("list");
        return weatherList.getJSONObject(dayIndex).getJSONObject("temp").getDouble("max");
    }
}

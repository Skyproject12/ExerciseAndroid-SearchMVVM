package com.example.viewmodel;

import org.json.JSONObject;

import java.text.DecimalFormat;

// make class object for response item
public class WeatherItems {
    private int id;
    private String name;
    private String currentWether;
    private String deskription;
    private String temperature;

    public WeatherItems(int id, String name, String currentWether, String deskription, String temperature) {
        this.id = id;
        this.name = name;
        this.currentWether = currentWether;
        this.deskription = deskription;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentWether() {
        return currentWether;
    }

    public void setCurrentWether(String currentWether) {
        this.currentWether = currentWether;
    }

    public String getDeskription() {
        return deskription;
    }

    public void setDeskription(String deskription) {
        this.deskription = deskription;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    // call response with parse object manual
    WeatherItems(JSONObject object) {
        // take id from api
        try {
            int id = object.getInt("id");
            // take name from api
            String name = object.getString("name");
            // take main in array weather
            String currentWeather = object.getJSONArray("weather").getJSONObject(0).getString("main");
            String deskription = object.getJSONArray("weather").getJSONObject(0).getString("description");
            double tempInKelvin = object.getJSONObject("main").getDouble("temp");
            // make format celcius from kelfin
            double tempInCelcius = tempInKelvin - 273;
            String temperature = new DecimalFormat("##.##").format(tempInCelcius);

            this.id = id;
            this.name = name;
            this.currentWether = currentWeather;
            this.deskription = deskription;
            this.temperature = temperature;
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

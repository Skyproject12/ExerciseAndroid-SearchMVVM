package com.example.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

// catatan
// dengan postValue kita bisa mengubah isi item dari arraylist

public class MainViewModel extends ViewModel {
    // save key api
    private static final String API_KEY= "deeef61ad76ee882d9e068d7400279d6";
    // take livedata mutable
    public MutableLiveData<ArrayList<WeatherItems>> listWather= new MutableLiveData<>() ;
    final ArrayList<WeatherItems> listItem= new ArrayList<>();
    void setWeather(final String cities) {
        // request api
        AsyncHttpClient client= new AsyncHttpClient();
        String url= "https://api.openweathermap.org/data/2.5/group?id="+ cities + "&units=metric&appid="+ API_KEY ;
        Log.d("SAMPAI", "setWeather: ");
        // melakukan perubahan value dari item viewmodel sesuai dengan retrive item dari api
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    Log.d("ARRAY", result);
                    // call result from api android
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(weather);
                        // add item to arraylist android
                        listItem.add(weatherItems);
                        Log.d("TAG", weatherItems.getTemperature());
                    }
                    // refresh in adafter
                    listWather.postValue(listItem);
                }
                catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("FAILURE", error.getMessage());
            }
        });
    }
    // get weather in another class
    LiveData<ArrayList<WeatherItems>> getWeather() {
        // livedata merupakan suatu fungsi bagian dari library livecryle yang hanya bisa mengread  item yang telah di buat
        return listWather;
    }
}

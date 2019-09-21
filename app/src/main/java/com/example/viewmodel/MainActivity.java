package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;


// catatan
// memanggil class viewmodel dengan ViewmodelProviders.of
// observe (this, getWatherSearch) berfungsi memberi nilai terbaru dari hasil get viewmodel kepada fungsi getWeatherSearch
public class MainActivity extends AppCompatActivity {

    private WeatherAdapter adapter;
    private EditText inputCity;
    private ProgressBar progressBar;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter= new WeatherAdapter();
        adapter.notifyDataSetChanged();

        RecyclerView recyclerView= findViewById(R.id.recyclerSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        inputCity= findViewById(R.id.inputCity);
        progressBar= findViewById(R.id.progressBar);

        // definition view model
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        // get liveitem from Viewmodel
        mainViewModel.getWeather().observe(this, getWeatherSearch);

        findViewById(R.id.buttonCity).setOnClickListener(listener);

    }

    // get weather Viewmodel
    private Observer<ArrayList<WeatherItems>> getWeatherSearch= new Observer<ArrayList<WeatherItems>>() {
        @Override
        // berfungsi mengambil item terbaru dari viewmodel lalu mengeset ke dalam adapter
        public void onChanged(ArrayList<WeatherItems> weatherItems) {
            // weather != null
            if(weatherItems!=null ) {
                // set item all in  adapter
                adapter.setItems(weatherItems);
                Toast.makeText(MainActivity.this, "weather"+weatherItems, Toast.LENGTH_SHORT).show();
                showLoading(false);

            }
        }
    };

    View.OnClickListener listener= new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            String city= inputCity.getText().toString();
            if(TextUtils.isEmpty(city))return;

            // ketika button di beri event maka viewmodel akan di set kembali mengambil api di server melalui fungsi setWeather
            mainViewModel.setWeather(city);
            Toast.makeText(MainActivity.this, "main", Toast.LENGTH_SHORT).show();
            showLoading(true);

        }
    };

    private void showLoading(Boolean state) {
        // mengeset progress bar
        if(state) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

}

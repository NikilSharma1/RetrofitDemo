package com.example.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<CountryModel> countryList;
    ArrayList<String> countries;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countries=new ArrayList<>();
        getCountries();
//        for(CountryModel c:countryList){
//            countries.add(c.getName());
//        }
        listView=findViewById(R.id.countryListView);
        arrayAdapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,countries);
        //Log.i("info",String.valueOf(countries.size()));
        listView.setAdapter(arrayAdapter);
    }

    private Object getCountries() {
        GetRetrofitService getRetrofitService=GetRetrofitInstance.result();
        Call<FullData>call=getRetrofitService.getService();
        call.enqueue(new Callback<FullData>() {
            @Override
            public void onResponse(Call<FullData> call, Response<FullData> response) {
                FullData fullData=response.body();
                if(fullData!=null && fullData.getResult()!=null){
                    countryList=(ArrayList<CountryModel>) fullData.getResult();

                }
                //Log.i("info",String.valueOf(countryList.size()));
                for(CountryModel c:countryList){
                    Log.i("info",c.getName());
                    countries.add(c.getName());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<FullData> call, Throwable t) {
                //Log.i("info","Failure occurred");
            }
        });
        return countryList;
    }

}
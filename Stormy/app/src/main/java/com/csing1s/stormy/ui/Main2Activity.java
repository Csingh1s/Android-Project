package com.csing1s.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.csing1s.stormy.R;
import com.csing1s.stormy.Weather.Day;
import com.csing1s.stormy.adapters.DayAdapter;

import java.util.Arrays;

public class Main2Activity extends ListActivity {
    private Day[] days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Parcelable[] parcebles = intent.getParcelableArrayExtra(MainActivity.DAILY_FORCAST);
        days = Arrays.copyOf(parcebles, parcebles.length, Day[].class);//Day it shows us what type of array we are going to use
        DayAdapter dayAdapter = new DayAdapter(this,days);
        setListAdapter(dayAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek= days[position].getDayofTheweek();
        String conditions= days[position].getSummary();
        String hightem= days[position].getTemperatureMax()+"";
        String messege= String.format("On %s the Temperature will be %s degree Fahrenheight %s",dayOfTheWeek,hightem,conditions);
        Toast.makeText(this, messege, Toast.LENGTH_SHORT).show();



    }
}



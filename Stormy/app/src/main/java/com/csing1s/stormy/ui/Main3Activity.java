package com.csing1s.stormy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.csing1s.stormy.R;
import com.csing1s.stormy.Weather.Hour;
import com.csing1s.stormy.adapters.HourAdapter;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class
Main3Activity extends AppCompatActivity {
    private Hour[] mHours;
    @BindView(R.id.recycleView) RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hourly_forcast);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Parcelable[]parcelables = intent.getParcelableArrayExtra(MainActivity.HOURLY_FORCAST);
         mHours= Arrays.copyOf(parcelables,parcelables.length,Hour[].class);
        HourAdapter adapter = new HourAdapter(mHours);
        mRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
         mRecyclerView.setLayoutManager(layoutManager);
         mRecyclerView.setHasFixedSize(true);
    }
}

package com.csing1s.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.csing1s.stormy.R;
import com.csing1s.stormy.Weather.CurretWeather;
import com.csing1s.stormy.Weather.Day;
import com.csing1s.stormy.Weather.Forcast;
import com.csing1s.stormy.Weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String  DAILY_FORCAST="DAILY_FORCAST";
    public static final String  HOURLY_FORCAST="HOURLY_FORCAST";
  // private CurretWeather mCurrentWeather;
    private Forcast mForcast;
    private TextView mTemperature;
    private TextView mTimetable;
    private TextView mHumidityLabel;
    private TextView mPreciep;
    private  TextView mSummary;
    private ImageView mRefreshButtonView;
    private ProgressBar mProgressbar;
    private ImageView mIcon;
    private Button  mButton;
  // @BindView(R.id.TimeView) TextView mTimetable;

  //@BindView(R.id.Temperaturelabel) TextView mTemperature;
  //  @BindView(R.id.HumidityLabel) TextView mHumidityLabel;
 //   @BindView(R.id.PreciepLavel) TextView Preciep;
    //@BindView(R.id.Summery) TextView summery;
    //@BindView(R.id.IconImageView) TextView mIconImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressbar= (ProgressBar) findViewById(R.id.progressBar);
        mTemperature= (TextView) findViewById(R.id.Temperaturelabel);
        mTimetable = (TextView) findViewById(R.id.TimeView);
        mHumidityLabel=(TextView)findViewById(R.id.HumidityValue);
        mPreciep=(TextView) findViewById(R.id.PreciepLevel);
        mSummary=(TextView) findViewById(R.id.Summery);
        mIcon=(ImageView) findViewById(R.id.iconImageView);
        mProgressbar.setVisibility(View.INVISIBLE);
        mButton= (Button) findViewById(R.id.DailyButton);
      //  Drawable drawable = getResources().getDrawable(mCurrentWeather.getRefreshButton());
        mRefreshButtonView = (ImageView) findViewById(R.id.imageButton);
        /*
       mButton.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
               startActivity();
            }
       });
       */
     final  double latitude =  37.3058333;
       final double longitude = -89.5180556;
        mRefreshButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForcast(latitude, longitude);

            }
        });


        getForcast(latitude, longitude);

        Log.d(TAG, "UI is working dont worry!");


    }


    private void getForcast(double latitude,double longitude) {
       // String apiKey = "ce7184f51c7c5a03869258efd889de78";https://api.forecast.io/forecast/ce7184f51c7c5a03869258efd889de78/37.8267,-122.423
        String apiKey = "de5359d38a1b7507a847f83420737e6f";
        //https://api.forecast.io/forecast/de5359d38a1b7507a847f83420737e6f/37.8267,-122.423
        String forcasturl = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + "," + longitude;
        if (IsNetworkAvailabel())
        { // IsNetworkAvailable methods check if network is available
            ToogleRefresh();
            OkHttpClient client = new OkHttpClient();// this object will help to transfer datas and medias
           // Request request = .url(forcasturl).build();//this object will make request from url
            Request request = new Request.Builder().url(forcasturl).build();
            Call call = client.newCall(request);//call object will take data from
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                  runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                          //  ToogleRefresh();
                        }
                    });
                    AlertUsertError();

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToogleRefresh();
                        }
                    });

                    try {
                        String jsondata = response.body().string();
                        Log.v(TAG, jsondata);

                        if (response.isSuccessful()) {
                           //    mCurrentWeather = getCurrentDetails(jsondata);
                            mForcast= parseForcasr(jsondata);
                            runOnUiThread(new Runnable() {//this method giving direction of IsUpdate to Run on UI thread
                                @Override
                                public void run() {
                                    IsUpdate();

                                }
                            });


                        } else {
                            AlertUsertError();
                        }
                    }
                    catch (IOException e)
                    {
                        Log.e(TAG, "Exeption Caught:", e);
                    }
                    catch (JSONException e) {
                        Log.e(TAG, "Exeption Caught:", e);
                    }

                }
            });
        } else {
            Toast.makeText(this, "Opps Network is not available", Toast.LENGTH_LONG).show();
        }
    }

    private void ToogleRefresh() {
        //if()

       if (mProgressbar.getVisibility()== View.INVISIBLE)
        {
            mProgressbar.setVisibility(View.VISIBLE);
            mRefreshButtonView.setVisibility(View.INVISIBLE);
        }
        else
        {
            mProgressbar.setVisibility(View.INVISIBLE);
            mRefreshButtonView.setVisibility(View.VISIBLE);
        }
    }


    private void IsUpdate() { //it will  show current weather or will update
       // mTemperature.setText(mCurrentWeather.getTemperature()+"");
              CurretWeather mCurrentWeather = mForcast.getCurretWeather();
        mTemperature.setText(Double.toString(mCurrentWeather.getTemperature()));
        mTimetable.setText("AT"+" "+mCurrentWeather.getFormattedTime()+" "+"it will be");
        mHumidityLabel.setText(mCurrentWeather.getHumidity()+" ");
        mPreciep.setText(mCurrentWeather.getPrecipeChance()+" "+"%");
        mSummary.setText(mCurrentWeather.getSummery());
        Drawable drawable = getResources().getDrawable(mCurrentWeather.getIconId());
        mIcon.setImageDrawable(drawable);

    }

   private Forcast parseForcasr(String jsondata)throws JSONException
     {
         Forcast forcast = new Forcast();
         forcast.setCurretWeather(getCurrentDetails(jsondata));
         forcast.setDailyForcast(getDailyForcast(jsondata));
         forcast.setHourlyForcast(getHourlyForcast(jsondata));

         return forcast;
     }

    private Hour[] getHourlyForcast(String jsondata )throws  JSONException {
        JSONObject forcast = new JSONObject(jsondata);
        String timezon= forcast.getString("timezone");
        JSONObject hourly = forcast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");
        Hour[] hours = new Hour[data.length()];
        for (int i =0; i <data.length(); i++)
        {
            JSONObject jasonHour= data.getJSONObject(i);
            Hour hour = new Hour();
            hour.setTemperature(jasonHour.getDouble("temperature"));
            hour.setTime(jasonHour.getLong("time"));
            hour.setIcon(jasonHour.getString("icon"));
            hour.setTimeZone(timezon);
            hour.setSummary(jasonHour.getString("summary"));
            hours[i]= hour;
        }
          return   hours;
    }


    private Day[] getDailyForcast(String jsondata) throws  JSONException{
         //Day[]mdays= new Day[100];
        JSONObject  forcast =  new JSONObject(jsondata);
        String timezone = forcast.getString("timezone");
         JSONObject dailyforcast= forcast.getJSONObject("daily");
        JSONArray dailyweather = dailyforcast.getJSONArray("data");

        Day[] days = new Day[dailyweather.length()];
        for( int i =0; i <dailyweather.length(); i++)
        {
            JSONObject jasday= dailyweather.getJSONObject(i);
            Day day = new Day();

            day.setSummary(jasday.getString("summary"));
            day.setIcon(jasday.getString("icon"));
            day.setTemperatureMax(jasday.getDouble("temperatureMax"));
            day.setTime(jasday.getLong("time"));
            day.setTimeZone(timezone);
            days[i]= day;

        }
        return days;//days arry have summary,icon,temperature and time

    }


    private CurretWeather getCurrentDetails(String jsondata) throws JSONException { //this class can hold anyobject reprented in jason format
        JSONObject forcast = new JSONObject(jsondata);
        String timezone = forcast.getString("timezone");
        Log.i(TAG, "From Json" + timezone);
        JSONObject currently = forcast.getJSONObject("currently");//get json object currently with key currently.
        CurretWeather currentweather = new CurretWeather();
        currentweather.setHumidity(currently.getDouble("humidity"));
        currentweather.setTime(currently.getLong("time"));
        currentweather.setIcon(currently.getString("icon"));
        currentweather.setTemperature(currently.getDouble("temperature"));
        currentweather.setSummery(currently.getString("summary"));
        currentweather.setPrecipeChance(currently.getDouble("precipProbability"));
        currentweather.setTimeZone(timezone);
        Log.d(TAG, currentweather.getFormattedTime());
        return currentweather;

    }

    private void AlertUsertError() // when this methods will be called the dialoge box will comeout on the screen
    {
        AlertUserAboutError alert = new AlertUserAboutError();
        alert.show(getFragmentManager(), "error_dialoge");

    }

    private boolean IsNetworkAvailabel()// when this method will be called ,it will tell users about network before using this we have give permisson in Manifest   <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = manager.getActiveNetworkInfo();
        boolean isAvailabel = false;
        if (network != null && network.isConnected()) {
            isAvailabel = true;
        }
        return isAvailabel;

    }
    @OnClick(R.id.DailyButton)//when you will pres 7 DAY button you will have new activity
        public void startActivity (View v)
        {
            Intent intent = new Intent(this, Main2Activity.class);

            intent.putExtra(DAILY_FORCAST,mForcast.getDailyForcast());
            startActivity(intent);
        }
    @OnClick(R.id.hourlyButton)
    public void startHourlyActivity(View view)
    {
        Intent intent= new Intent(this,Main3Activity.class);
        intent.putExtra(HOURLY_FORCAST,mForcast.getHourlyForcast());
        startActivity(intent);
    }



}

package com.csing1s.stormy.Weather;

import com.csing1s.stormy.R;

/**
 * Created by Csing1s on 6/13/2016.
 */
public class Forcast {
    private CurretWeather mCurretWeather;
    private Day[] mDailyForcast;
    private Hour[]mHourlyForcast;

    public CurretWeather getCurretWeather() {
        return mCurretWeather;
    }

    public void setCurretWeather(CurretWeather curretWeather) {
        mCurretWeather = curretWeather;
    }

   // public Day[] getDailyForcast() {
     //   return mDailyForcast;
    //}


    public Day[] getDailyForcast() {
        return mDailyForcast;
    }

    public void setDailyForcast(Day[] dailyForcast) {
        mDailyForcast = dailyForcast;
    }

    public Hour[] getHourlyForcast() {
        return mHourlyForcast;
    }

    public void setHourlyForcast(Hour[] hourlyForcast) {
        mHourlyForcast = hourlyForcast;
    }
    public static  int getIconId(String mIcon)
    {
        int iconId= R.drawable.clear_day;
        if(mIcon.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if(mIcon.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if(mIcon.equals("rain")) {

            iconId = R.drawable.rain;
        }
        else if (mIcon.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (mIcon.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (mIcon.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (mIcon.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (mIcon.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (mIcon.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (mIcon.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;
    }

}

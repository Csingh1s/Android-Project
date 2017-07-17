package com.csing1s.stormy.Weather;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Csing1s on 6/5/2016.
 */
  public class CurretWeather {
    private String mIcon;
    private String mSummery;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPrecipeChance;
    private String mTimeZone;
    private int mRefreshButton;

    public String getIcon() {
        return mIcon;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getRefreshButton() {
        return mRefreshButton;
    }

    public void setRefreshButton(int refreshButton) {
        mRefreshButton = refreshButton;
    }

    public  int getIconId()
    {

            return Forcast.getIconId(mIcon);
    }

    public String getSummery() {
        return mSummery;
    }

    public void setSummery(String summery) {
        mSummery = summery;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }
public String getFormattedTime()// provide simple time formate using SimpleDateFormate object
{
    SimpleDateFormat timeformate = new SimpleDateFormat("h:mm a");
  //  timeformate.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
    timeformate.setTimeZone(TimeZone.getTimeZone(getTimeZone()));
    Date date = new Date(getTime()*1000);
    String timeString = timeformate.format(date);
     return timeString;
}

    public int getTemperature() {
        return  (int) Math.round(mTemperature);
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public int getPrecipeChance() {
        double precieppercentage = mPrecipeChance*100;
        return (int) Math.round(precieppercentage);
    }

    public void setPrecipeChance(double precipeChance) {
        mPrecipeChance = precipeChance;
    }
}

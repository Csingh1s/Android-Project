package com.csing1s.stormy.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Csing1s on 6/13/2016.
 */
public class Hour implements Parcelable {
    private long mTime;
    private String mIcon;
    private  double Temperature;
    private String mTimeZone;
    private String mSummary;
  public Hour(){

  }
    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getIcon() {
        return mIcon;
    }
    public int getIconId()
    {
        return Forcast.getIconId(mIcon);
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getTemperature() {
        return (int) Math.round(Temperature);
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }
    public String getHour()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        Date date = new Date(mTime*1000);
        return formatter.format(date);
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {//parcel or write out this class elemets to the dest that is in parameter
        dest.writeLong(mTime);
        dest.writeDouble(Temperature);
        dest.writeString(mSummary);
        dest.writeString(mIcon);
        dest.writeString(mSummary);


    }
    private Hour(Parcel in)
    {
        mTime= in.readLong();
        Temperature= in.readDouble();
        mSummary=in.readString();
        mIcon=in.readString();
        mSummary=in.readString();
    }// private constructor will help to read elemets from parcel
    public  static  final Creator<Hour> CREATOR= new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel source) {
            return new Hour(source);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };
}

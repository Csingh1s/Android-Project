package com.csing1s.stormy.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Csing1s on 6/13/2016.
 */
public class Day implements Parcelable {
    private long mTime;
    private String mIcon;
    private double TemperatureMax;
    private String mTimeZone;
    private String mSummary;

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getTemperatureMax() {
        return (int) Math.round(TemperatureMax);
    }

    public void setTemperatureMax(double temperatureMax) {
        TemperatureMax = temperatureMax;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getIconId() {
        return Forcast.getIconId(mIcon);
    }

    public String getDayofTheweek() {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date daytime = new Date(mTime * 1000);
        return formatter.format(daytime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.mTime);
        dest.writeDouble(this.TemperatureMax);
        dest.writeString(this.mIcon);
        dest.writeString(this.mSummary);
        dest.writeString(this.mTimeZone);
    }

    private Day(Parcel in) {
      this.mTime = in.readLong();
      this.TemperatureMax = in.readDouble();
       this.mIcon = in.readString();
       this.mSummary = in.readString();
       this.mTimeZone = in.readString();
    }

public Day(){}
    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel sources) {
            return new Day(sources);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

}




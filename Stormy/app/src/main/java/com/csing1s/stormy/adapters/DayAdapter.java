package com.csing1s.stormy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.csing1s.stormy.R;
import com.csing1s.stormy.Weather.Day;

/**
 * Created by Csing1s on 6/15/2016.
 */
public class DayAdapter extends BaseAdapter {
    private Context mContext;
    private Day[] mDays;// going to adapt this array

    public DayAdapter(Context context, Day[] day)
    {
        mContext=context;
        mDays=day;
    }
    @Override
    public int getCount() { //How many items are in the data set represented by this Adapter.
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {//Get the data item associated with the specified position in the data set.
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//parent is nothing the listview passed inside
        ViewHolder holder; //that will hold views
                    if(convertView==null )// setting all views to resuse it when we will scroll(getView is called)
                    {
                        convertView= LayoutInflater.from(mContext).inflate(R.layout.daily_list_item,null);
                        //LayoutInflater will take view from  layout and convert int <code></code
                        holder = new ViewHolder();
                        holder.mdaylabel=(TextView) convertView.findViewById(R.id.dayNameLabel);
                        holder.miconImageView= (ImageView) convertView.findViewById(R.id.iconImageView);
                       holder.mtemperaturelabel =(TextView) convertView.findViewById(R.id.temperaturelabel);
                         convertView.setTag(holder);
                    }
        else
                    {
                        holder= (ViewHolder) convertView.getTag();
                    }
        Day day = mDays[position];// first row  means  current day
        holder.miconImageView.setImageResource(day.getIconId());
        holder.mtemperaturelabel.setText(day.getTemperatureMax()+"");
        if(position==0)
        {
            holder.mdaylabel.setText("Today");
        }
        else {
            holder.mdaylabel.setText(day.getDayofTheweek());
        }


        return convertView;
    }
    private  static class ViewHolder
    {
        ImageView miconImageView;//public by dfault
        TextView mtemperaturelabel;
        TextView mdaylabel;
    }
}

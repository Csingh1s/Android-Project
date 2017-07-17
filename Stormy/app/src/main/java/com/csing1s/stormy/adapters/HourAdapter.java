package com.csing1s.stormy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csing1s.stormy.R;
import com.csing1s.stormy.Weather.Hour;

/**
 * Created by Csing1s on 6/20/2016.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    private Hour[]mHours;
     public HourAdapter(Hour[] hours)
     {
         mHours=hours;
     }
    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {// this is going to be called whenever a new view holder is needed
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item,parent,false);
        HourViewHolder viewHolder = new HourViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
         holder.bindHour(mHours[position]);
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder
   implements  View.OnClickListener {
        public TextView mTimelable;
        public TextView mSummarylable;
        public TextView mTemperature;
        public ImageView mIconImageView;

        public HourViewHolder(View itemView)// when the view holder will be cxreted this constructirr will be called
        {

            super(itemView);
            mTimelable = (TextView) itemView.findViewById(R.id.timelable);
            mSummarylable = (TextView) itemView.findViewById(R.id.summary);
            mTemperature = (TextView) itemView.findViewById(R.id.temperature);
            mIconImageView = (ImageView) itemView.findViewById(R.id.iconImageView);
        }
        public  void bindHour(Hour hour)
        {
            mTimelable.setText(hour.getHour());
            mSummarylable.setText(hour.getSummary());
            mTemperature.setText(hour.getTemperature()+" ");
            mIconImageView.setImageResource(hour.getIconId());
        }

        @Override
        public void onClick(View v) {

        }
    }


}

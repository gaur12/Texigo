package com.example.user.texigo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.texigo.Model.FlightModel;
import com.example.user.texigo.R;

import java.util.List;

/**
 * Created by USER on 08-Apr-17.
 */

public class DestinationAdapter extends RecyclerView.Adapter<DestinationAdapter.MyViewHolder> {

    private Context mContext;
    private List<FlightModel> destinationList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView place, country;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            place = (TextView) view.findViewById(R.id.place);
            country = (TextView) view.findViewById(R.id.country);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }


    public DestinationAdapter(Context mContext, List<FlightModel> destinationList) {
        this.mContext = mContext;
        this.destinationList = destinationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.destination_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        FlightModel flight = destinationList.get(position);
        holder.place.setText(flight.getName());
        holder.country.setText(flight.getStateName() == null ? flight.getName().toUpperCase() : flight.getStateName().toUpperCase());

        // loading album cover using Glide library
        Glide.with(mContext).load(flight.getImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }
}


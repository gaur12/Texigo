package com.example.user.texigo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.texigo.Activity.DestinationDetails;
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
        public TextView place, country, price, destinationCategory;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            place = (TextView) view.findViewById(R.id.place);
            price = (TextView) view.findViewById(R.id.price);
            country = (TextView) view.findViewById(R.id.country);
            destinationCategory = (TextView) view.findViewById(R.id.destination_category);
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
        final FlightModel flight = destinationList.get(position);
        holder.place.setText(flight.getName());
        holder.country.setText(flight.getStateName() == null ? flight.getName().toUpperCase() : flight.getStateName().toUpperCase());
        holder.price.setText("from ₹"+flight.getPrice());
        List<String> destinationCategories = flight.getDestinationCategories();
        if (destinationCategories.contains("Destination")) destinationCategories.remove("Destination");
        if (destinationCategories.contains("Things To Do")) destinationCategories.remove("Things To Do");
        if (destinationCategories.contains("Places To Visit")) destinationCategories.remove("Places To Visit");
        if (destinationCategories.contains("Weekend Gateway")) destinationCategories.remove("Weekend Gateway");
        if (destinationCategories.size() > 1) destinationCategories.remove("City");
        String category = "";
        if (destinationCategories.size() > 0) category = destinationCategories.get(0);
        if (destinationCategories.size() > 1) category = category + " . " + destinationCategories.get(1);
        holder.destinationCategory.setText(category);
        // loading album cover using Glide library
        Glide.with(mContext).load(flight.getImage()).into(holder.thumbnail);
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DestinationDetails.class);
                intent.putExtra("FLIGHT_MODEL", flight);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return destinationList.size();
    }
}


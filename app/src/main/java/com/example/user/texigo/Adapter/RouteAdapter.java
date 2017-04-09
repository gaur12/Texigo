package com.example.user.texigo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.texigo.Model.RouteModel;
import com.example.user.texigo.R;

import java.util.List;

/**
 * Created by USER on 09-Apr-17.
 */

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {

    private Context mContext;
    private List<RouteModel> routeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView price, modeVia, time;
        public ImageView mode;

        public MyViewHolder(View view) {
            super(view);
            price = (TextView) view.findViewById(R.id.price);
            modeVia = (TextView) view.findViewById(R.id.mode_via);
            time = (TextView) view.findViewById(R.id.time);
            mode = (ImageView) view.findViewById(R.id.mode);
        }
    }


    public RouteAdapter(Context mContext, List<RouteModel> routeList) {
        this.mContext = mContext;
        this.routeList = routeList;
    }

    @Override
    public RouteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.route_details, parent, false);

        return new RouteAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RouteAdapter.MyViewHolder holder, int position) {
        final RouteModel route = routeList.get(position);
        holder.modeVia.setText(route.getModeViaString());
        holder.time.setText(route.getDurationPretty());
        holder.price.setText("₹"+route.getPrice());
        if (route.getFirstModeTypesCss().contains("flight"))
            Glide.with(mContext).load(R.drawable.ic_flight_black_24dp).into(holder.mode);
        else
            Glide.with(mContext).load(R.drawable.ic_directions_railway_black_24dp).into(holder.mode);

    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }
}



package com.example.user.texigo.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.texigo.Adapter.DestinationAdapter;
import com.example.user.texigo.Model.FlightModel;
import com.example.user.texigo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 08-Apr-17.
 */

public class ReligionFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    private RecyclerView recyclerView;
    private DestinationAdapter adapter;
    private List<FlightModel> destinationList;
    // newInstance constructor for creating fragment with arguments
    public static ReligionFragment newInstance(int page, String title, List<FlightModel> destinationList) {
        ReligionFragment religionFragment = new ReligionFragment();
        Bundle args = new Bundle();
        args.putInt("POSITION", page);
        args.putString("TITLE", title);
        args.putParcelableArrayList("DESTINATION_LIST", (ArrayList<FlightModel>)destinationList);
        religionFragment.setArguments(args);
        return religionFragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("POSITION", 0);
        title = getArguments().getString("TITLE");
        destinationList = new ArrayList<>();
        List<FlightModel> list = getArguments().getParcelableArrayList("DESTINATION_LIST");
        for (FlightModel flightModel : list){
            if (flightModel.getDestinationCategories().contains("Religious"))
                destinationList.add(flightModel);
        }
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        adapter = new DestinationAdapter(getActivity(), destinationList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return view;
    }
}

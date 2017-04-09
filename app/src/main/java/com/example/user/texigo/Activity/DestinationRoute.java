package com.example.user.texigo.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.texigo.Adapter.RouteAdapter;
import com.example.user.texigo.Gac;
import com.example.user.texigo.Model.RouteDataModel;
import com.example.user.texigo.Model.RouteModel;
import com.example.user.texigo.R;
import com.example.user.texigo.Rest.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationRoute extends AppCompatActivity {

    private String destinationImage;
    private String destinationName;
    ProgressDialog pd;
    int originXid;
    int destinationXid;
    private RecyclerView recyclerView;
    private RouteAdapter adapter;
    private List<RouteModel> routeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_route);
        if (null != getIntent()) {
            originXid = getIntent().getIntExtra("ORIGIN_XID",0);
            destinationXid = getIntent().getIntExtra("DESTINATION_XID",0);
            destinationName = getIntent().getStringExtra("DESTINATION_NAME");
            destinationImage = getIntent().getStringExtra("DESTINATION_IMAGE");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(destinationName);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        routeList = new ArrayList<>();
        adapter = new RouteAdapter(this, routeList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        try {
            Glide.with(this).load(destinationImage).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        prepareDestinationDetails();
    }

    private void prepareDestinationDetails() {
        if (Gac.getInstance().isNetworkAvailable()) {
            pd.show();
            ApiService apiService = Gac.getInstance().getRestClient().getApiService();

            Call<RouteDataModel> call = apiService.fetchRoutes(originXid, destinationXid);
            call.enqueue(new Callback<RouteDataModel>() {
                @Override
                public void onResponse(Call<RouteDataModel> call, Response<RouteDataModel> response) {
                    pd.dismiss();
                    routeList.clear();
                    routeList.addAll(response.body().getData().getRoutes());
                    routeList.add(response.body().getData().getCheapestRoute());
                    routeList.add(response.body().getData().getFastestRoute());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<RouteDataModel> call, Throwable t) {
                    // Log error here since request failed
                    Log.e("**********************", t.getMessage());
                    pd.dismiss();
                }
            });
        } else {
            Toast.makeText(this, "No Network connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(destinationName);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}

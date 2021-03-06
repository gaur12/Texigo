package com.example.user.texigo.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.texigo.Adapter.DestinationAdapter;
import com.example.user.texigo.Gac;
import com.example.user.texigo.Model.DiscoverDataModel;
import com.example.user.texigo.Model.FlightModel;
import com.example.user.texigo.R;
import com.example.user.texigo.Rest.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private DestinationAdapter adapter;
    private List<FlightModel> destinationList;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        destinationList = new ArrayList<>();
        adapter = new DestinationAdapter(this, destinationList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        pd = new ProgressDialog(this);
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        prepareDestinations();

    }

    @Override
    public void onPause(){
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    private void prepareDestinations() {
        if (Gac.getInstance().isNetworkAvailable()) {
            pd.show();
            ApiService apiService = Gac.getInstance().getRestClient().getApiService();

            Call<DiscoverDataModel> call = apiService.fetchDiscoverData();
            call.enqueue(new Callback<DiscoverDataModel>() {
                @Override
                public void onResponse(Call<DiscoverDataModel> call, Response<DiscoverDataModel> response) {
                    pd.dismiss();
                    destinationList.clear();
                    destinationList.addAll(response.body().getData().getFlight());
                    destinationList.addAll(response.body().getData().getBudgetFlight());
                    createCover(destinationList, destinationList.size()-1);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<DiscoverDataModel> call, Throwable t) {
                    // Log error here since request failed
                    pd.dismiss();
                }
            });
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No Network connection", Toast.LENGTH_SHORT).show();
        }
    }

    Handler handler;
    Runnable runnable;
    private void createCover(final List<FlightModel> list, int index) {
        try {
            Glide.with(this).load(list.get(index).getImage()).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
        handler = new Handler();
        if (runnable != null)
            handler.removeCallbacks(runnable);
        runnable = new Runnable() {

            @Override
            public void run() {
                //TODO: update image here
                createCover(list, new Random().nextInt(list.size()));
            }
        };
        handler.postDelayed(runnable, 10000);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
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

    public void moveToCategoryScreen(View view) {
        Intent intent = new Intent(MainActivity.this, DestinationCategory.class);
        intent.putParcelableArrayListExtra("DESTINATION_LISTS", (ArrayList<FlightModel>)destinationList);
        startActivity(intent);
    }
}

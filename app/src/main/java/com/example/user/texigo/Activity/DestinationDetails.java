package com.example.user.texigo.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.texigo.Adapter.AutoCompleteAdapter;
import com.example.user.texigo.Gac;
import com.example.user.texigo.Model.AutoCompleteSuggest;
import com.example.user.texigo.Model.DestinationDetail;
import com.example.user.texigo.Model.DestinationDetailsData;
import com.example.user.texigo.Model.FlightModel;
import com.example.user.texigo.R;
import com.example.user.texigo.Rest.ApiService;
import com.example.user.texigo.Util.DelayAutoCompleteTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationDetails extends AppCompatActivity {

    private FlightModel flightModel;
    private ProgressDialog pd;
    DestinationDetail detail;
    private int originXid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_details);
        detail = new DestinationDetail();
        flightModel = new FlightModel();
        if (null != getIntent()) {
            flightModel = getIntent().getParcelableExtra("FLIGHT_MODEL");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(flightModel.getName());
        setSupportActionBar(toolbar);

        initCollapsingToolbar();
        pd = new ProgressDialog(this);
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        prepareDestinationDetails();

    }

    private void prepareDestinationDetails() {
        if (Gac.getInstance().isNetworkAvailable()) {
            pd.show();
            String entityId = flightModel.getCityId();
            ApiService apiService = Gac.getInstance().getRestClient().getApiService();

            Call<DestinationDetailsData> call = apiService.fetchDestinationDetails(entityId);
            call.enqueue(new Callback<DestinationDetailsData>() {
                @Override
                public void onResponse(Call<DestinationDetailsData> call, Response<DestinationDetailsData> response) {
                    pd.dismiss();
                    createCover(response);
                    detail = response.body().getData();
                    fillDetailLayout(detail);
                }

                @Override
                public void onFailure(Call<DestinationDetailsData> call, Throwable t) {
                    // Log error here since request failed
                    pd.dismiss();
                }
            });
        } else {
            Toast.makeText(this, "No Network connection", Toast.LENGTH_SHORT).show();
        }
    }

    boolean isTextViewClickedD = false;
    boolean isTextViewClickedH = false;
    boolean isTextViewClickedW = false;
    TextView descriptionDetail, howToReachDetail, whyToVisitDetail;

    private void fillDetailLayout(DestinationDetail detail) {
        TextView destinationTitle = (TextView) findViewById(R.id.destination_title);
        TextView location = (TextView) findViewById(R.id.location);
        TextView descriptionTitle = (TextView) findViewById(R.id.description_title);
        descriptionDetail = (TextView) findViewById(R.id.destination_description);
        TextView howToReachTitle = (TextView) findViewById(R.id.howToReach_title);
        howToReachDetail = (TextView) findViewById(R.id.howToReach_description);
        TextView whyToVisitTitle = (TextView) findViewById(R.id.whyToVisit_title);
        whyToVisitDetail = (TextView) findViewById(R.id.whyToVisit_description);
        destinationTitle.setText(detail.getName());
        location.setText(detail.getStateName()+", "+detail.getCountryName()+"  ");
        descriptionTitle.setText(R.string.about);
        if (detail.getDescription() != null && detail.getDescription().length() > 0) {
            descriptionDetail.setText(Html.fromHtml(Html.fromHtml(detail.getDescription()).toString()));

            descriptionDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isTextViewClickedD){
                        //This will shrink textview to 2 lines if it is expanded.
                        descriptionDetail.setMaxLines(10);
                        isTextViewClickedD = false;
                    } else {
                        //This will expand the textview if it is of 2 lines
                        descriptionDetail.setMaxLines(Integer.MAX_VALUE);
                        isTextViewClickedD = true;
                    }
                }

            });

        }
        else
            descriptionDetail.setText(R.string.coming_soon);
        howToReachTitle.setText(R.string.how_to_raech);
        if (detail.getHowToReach() != null && detail.getHowToReach().length() > 0) {
            howToReachDetail.setText(detail.getHowToReach());
            boolean isTextViewClicked = false;

            howToReachDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isTextViewClickedH){
                        //This will shrink textview to 2 lines if it is expanded.
                        howToReachDetail.setMaxLines(10);
                        isTextViewClickedH = false;
                    } else {
                        //This will expand the textview if it is of 2 lines
                        howToReachDetail.setMaxLines(Integer.MAX_VALUE);
                        isTextViewClickedH = true;
                    }
                }

            });

        }
        else
            howToReachDetail.setText(R.string.coming_soon);
        whyToVisitTitle.setText(R.string.why_to_visit);
        if (detail.getWhyToVisit() != null && detail.getWhyToVisit().length() > 0) {
            whyToVisitDetail.setText(detail.getWhyToVisit());
            boolean isTextViewClicked = false;

            whyToVisitTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isTextViewClickedW){
                        //This will shrink textview to 2 lines if it is expanded.
                        whyToVisitDetail.setMaxLines(10);
                        isTextViewClickedW = false;
                    } else {
                        //This will expand the textview if it is of 2 lines
                        whyToVisitDetail.setMaxLines(Integer.MAX_VALUE);
                        isTextViewClickedW = true;
                    }
                }

            });

        }
        else
            whyToVisitDetail.setText(R.string.coming_soon);
    }

    private void createCover(Response<DestinationDetailsData> response) {
        try {
            Glide.with(this).load(response.body().getData().getKeyImageUrl()).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(flightModel.getName());
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

    DelayAutoCompleteTextView originTitle;
    public void moveToRouteScreen(View view) {
        LayoutInflater inflater = LayoutInflater.from(DestinationDetails.this);
        View dialogLayout = inflater.inflate(R.layout.custom_dialog, null);

        AlertDialog.Builder dialog = new AlertDialog.Builder(DestinationDetails.this);
        dialog.setView(dialogLayout);
        dialog.setTitle(R.string.select_origin);
        originTitle = (DelayAutoCompleteTextView) dialogLayout.findViewById(R.id.origin);
        originTitle.setAdapter(new AutoCompleteAdapter(this)); // 'this' is Activity instance
        originTitle.setLoadingIndicator(
                (android.widget.ProgressBar) findViewById(R.id.origin_loading_indicator));
        originTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AutoCompleteSuggest title = (AutoCompleteSuggest) adapterView.getItemAtPosition(position);
                originTitle.setText(title.getDestinationName());
                originXid = title.getXid();
            }
        });
        // set the custom dialog components - text, image and button
        // if button is clicked, close the custom dialog
        dialog.setPositiveButton(R.string.go, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(DestinationDetails.this, DestinationRoute.class);
                intent.putExtra("ORIGIN_XID", originXid);
                intent.putExtra("DESTINATION_XID", detail.getXid());
                intent.putExtra("DESTINATION_IMAGE", detail.getKeyImageUrl());
                intent.putExtra("DESTINATION_NAME", detail.getName());
                startActivity(intent);
            }
        });

        dialog.show();
    }

    public void showLocationOnMap(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=" + (String.valueOf(detail.getLatitude())+", " + String.valueOf(detail.getLongitude()))));
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

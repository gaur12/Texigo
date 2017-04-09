package com.example.user.texigo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.texigo.Gac;
import com.example.user.texigo.Model.AutoCompleteSuggest;
import com.example.user.texigo.R;
import com.example.user.texigo.Rest.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by USER on 09-Apr-17.
 */

public class AutoCompleteAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<AutoCompleteSuggest> resultList = new ArrayList<AutoCompleteSuggest>();

    public AutoCompleteAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public AutoCompleteSuggest getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getDestinationName());
        ((TextView) convertView.findViewById(R.id.text2)).setText(getItem(position).getStateName()+", "+getItem(position).getCountryName());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected Filter.FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<AutoCompleteSuggest> destinations = findDestinations(mContext, constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = destinations;
                    filterResults.count = destinations.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    resultList = (List<AutoCompleteSuggest>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }

    /**
     * Returns a search result for the given book title.
     */
    List<AutoCompleteSuggest> fetchedList;
    private List<AutoCompleteSuggest> findDestinations(Context context, String destinationTitle) {
        fetchedList = new ArrayList<>();
        if (Gac.getInstance().isNetworkAvailable()) {
            ApiService apiService = Gac.getInstance().getRestClient().getApiService();
            Call<List<AutoCompleteSuggest>> call = apiService.fetchDestinations(destinationTitle);
            try {
                return call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "No Network connection", Toast.LENGTH_SHORT).show();
        }
        return fetchedList;
    }
}

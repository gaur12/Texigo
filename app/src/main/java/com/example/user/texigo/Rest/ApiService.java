package com.example.user.texigo.Rest;

import com.example.user.texigo.Model.AutoCompleteSuggest;
import com.example.user.texigo.Model.DestinationDetailsData;
import com.example.user.texigo.Model.DiscoverDataModel;
import com.example.user.texigo.Model.RouteDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$")
    Call<DiscoverDataModel> fetchDiscoverData();

    @GET("api/v3/namedentities/id/{entityId}?apiKey=ixicode!2$")
    Call<DestinationDetailsData> fetchDestinationDetails(@Path("entityId") String entityId);

    @GET("action/content/zeus/autocomplete?searchFor=tpAutoComplete&neCategories=City&")
    Call<List<AutoCompleteSuggest>> fetchDestinations(@Query("query") String query);

    @GET("api/v2/a2b/modes?apiKey=ixicode!2$&")
    Call<RouteDataModel> fetchRoutes(@Query("originCityId") int originCityId, @Query("destinationCityId") int destinationCityId);

}
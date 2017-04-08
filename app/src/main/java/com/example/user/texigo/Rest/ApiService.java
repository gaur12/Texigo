package com.example.user.texigo.Rest;

import com.example.user.texigo.Model.DestinationDetailsData;
import com.example.user.texigo.Model.DiscoverDataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$")
    Call<DiscoverDataModel> fetchDiscoverData();

    @GET("api/v3/namedentities/id/{entityId}?apiKey=ixicode!2$")
    Call<DestinationDetailsData> fetchDestinationDetails(@Path("entityId") String entityId);

}
package com.example.user.texigo.Rest;

import com.example.user.texigo.Model.DiscoverDataModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/v2/widgets/brand/inspire?product=1&apiKey=ixicode!2$")
    Call<DiscoverDataModel> fetchDiscoverData();

}
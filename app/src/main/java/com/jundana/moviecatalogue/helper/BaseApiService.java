package com.jundana.moviecatalogue.helper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BaseApiService {
    @GET("3/discover/movie")
    Call<ResponseBody> getMovieRequest(@Query("api_key") String api_key);

    @GET("3/discover/tv")
    Call<ResponseBody> getTvShowRequest(@Query("api_key") String api_key);

}
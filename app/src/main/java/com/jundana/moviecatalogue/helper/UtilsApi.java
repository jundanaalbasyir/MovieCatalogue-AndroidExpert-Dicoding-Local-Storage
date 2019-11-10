package com.jundana.moviecatalogue.helper;

import com.jundana.moviecatalogue.BuildConfig;

public class UtilsApi {
    static final String BASE_URL_API = "https://api.themoviedb.org";
    public static final String api_key = BuildConfig.TMDB_API_KEY;
    public static final String PHOTO_URL = "https://image.tmdb.org/t/p/w185/";

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient().create(BaseApiService.class);
    }
}

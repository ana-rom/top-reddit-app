package com.vrgsoft.TopRedditApp.service;

import com.vrgsoft.TopRedditApp.dto.TopPostDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopPublicationService {
    @GET("/top.json")
    Call<TopPostDto> getTopPublications(@Query("after") String after);
}

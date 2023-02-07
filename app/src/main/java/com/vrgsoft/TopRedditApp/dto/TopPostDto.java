package com.vrgsoft.TopRedditApp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopPostDto {
    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private TopDataDto topDataDto;
}

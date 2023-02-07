package com.vrgsoft.TopRedditApp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildDto {
    @SerializedName("kind")
    private String kind;
    @SerializedName("data")
    private ChildDataDto childDataDto;
}

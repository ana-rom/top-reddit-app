package com.vrgsoft.TopRedditApp.dto;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopDataDto {
    @SerializedName("after")
    private String after;
    @SerializedName("dist")
    private int count;
    @SerializedName("children")
    private List<ChildDto> children;
}

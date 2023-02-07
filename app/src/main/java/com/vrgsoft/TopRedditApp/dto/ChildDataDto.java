package com.vrgsoft.TopRedditApp.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChildDataDto {
    @SerializedName("title")
    private String title;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("num_comments")
    private long commentsNumber;
    @SerializedName("author")
    private String author;
    @SerializedName("url")
    private String imageUrl;
    @SerializedName("created_utc")
    private long timestamp;
}

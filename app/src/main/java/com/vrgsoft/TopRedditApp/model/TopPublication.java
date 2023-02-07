package com.vrgsoft.TopRedditApp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TopPublication {
    private String title;
    private String thumbnail;
    private String commentsNumber;
    private String author;
    private String imageUrl;
    private String dateTime;
}

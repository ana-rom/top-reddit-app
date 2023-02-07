package com.vrgsoft.TopRedditApp.dto.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import com.vrgsoft.TopRedditApp.dto.ChildDataDto;
import com.vrgsoft.TopRedditApp.model.TopPublication;
import org.ocpsoft.prettytime.PrettyTime;

public class TopPublicationMapper {
    public TopPublication toModel(ChildDataDto dto) {
        return TopPublication.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .commentsNumber(dto.getCommentsNumber() + " comments")
                .dateTime(getPrettyTime(dto.getTimestamp()))
                .imageUrl(dto.getImageUrl())
                .thumbnail(dto.getThumbnail())
                .build();
    }

    private String getPrettyTime(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp),
                ZoneId.systemDefault());
        return new PrettyTime().format(dateTime);
    }
}

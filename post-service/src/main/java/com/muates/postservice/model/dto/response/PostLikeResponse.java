package com.muates.postservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikeResponse {

    private Long id;
    private Long userId;
    private Boolean isLike;
    private Date createdDate;
}

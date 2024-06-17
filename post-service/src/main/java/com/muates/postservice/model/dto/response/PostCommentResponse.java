package com.muates.postservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCommentResponse {

    private Long id;
    private Long userId;
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private List<PostCommentLikeResponse> likes;
}

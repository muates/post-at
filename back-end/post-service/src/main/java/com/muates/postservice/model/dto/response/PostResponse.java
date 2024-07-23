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
public class PostResponse {

    private Long id;
    private Long userId;
    private String content;
    private Date createdDate;
    private Date updatedDate;
    private List<PostMediaResponse> media;
    private List<PostLikeResponse> likes;
    private List<PostCommentResponse> comments;
    private String username;
    private String profilePicture;
}

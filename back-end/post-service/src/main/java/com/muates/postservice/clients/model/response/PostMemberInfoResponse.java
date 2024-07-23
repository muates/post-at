package com.muates.postservice.clients.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PostMemberInfoResponse {
    private Long userId;
    private String username;
    private String profilePicture;
    private List<CommentMemberInfoResponse> commentInfos;
}

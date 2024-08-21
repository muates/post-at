package com.muates.postservice.clients.model.response;

import lombok.Data;

@Data
public class CommentMemberInfoResponse {
    private Long userId;
    private String username;
    private String profilePicture;
}

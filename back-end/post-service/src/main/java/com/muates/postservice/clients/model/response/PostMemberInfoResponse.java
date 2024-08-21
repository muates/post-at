package com.muates.postservice.clients.model.response;

import lombok.Data;

@Data
public class PostMemberInfoResponse {
    private Long userId;
    private String username;
    private String profilePicture;
}

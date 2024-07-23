package com.muates.memberservice.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostMemberInfoResponse {
    private Long userId;
    private String username;
    private String profilePicture;
    private List<CommentMemberInfoResponse> commentInfos;
}

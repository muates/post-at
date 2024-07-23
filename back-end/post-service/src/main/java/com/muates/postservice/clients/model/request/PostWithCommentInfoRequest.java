package com.muates.postservice.clients.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostWithCommentInfoRequest {
    private Long postId;
    private List<Long> commentIds;
}

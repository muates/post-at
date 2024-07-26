package com.muates.postservice.service.delegate;

import com.muates.postservice.clients.model.response.CommentMemberInfoResponse;
import com.muates.postservice.model.dto.response.PostCommentResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentDelegateService {
    Page<PostCommentResponse> updateCommentResponse(Page<PostCommentResponse> commentResponse, List<CommentMemberInfoResponse> memberResponse);
}

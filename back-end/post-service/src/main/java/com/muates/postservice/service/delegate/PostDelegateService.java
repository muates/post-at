package com.muates.postservice.service.delegate;

import com.muates.postservice.clients.model.response.PostMemberInfoResponse;
import com.muates.postservice.model.dto.response.PostResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostDelegateService {
    Page<PostResponse> updatePostResponse(Page<PostResponse> postResponse, List<PostMemberInfoResponse> memberResponse);
}

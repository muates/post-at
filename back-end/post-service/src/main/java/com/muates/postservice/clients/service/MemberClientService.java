package com.muates.postservice.clients.service;

import com.muates.postservice.clients.model.request.PostWithCommentInfoRequest;
import com.muates.postservice.clients.model.response.PostMemberInfoResponse;

import java.util.List;

public interface MemberClientService {
    List<PostMemberInfoResponse> getMemberInfoForPosts(List<PostWithCommentInfoRequest> requests);
}

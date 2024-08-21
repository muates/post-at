package com.muates.postservice.clients.service;

import com.muates.postservice.clients.model.request.CommentInfoRequest;
import com.muates.postservice.clients.model.request.PostInfoRequest;
import com.muates.postservice.clients.model.response.CommentMemberInfoResponse;
import com.muates.postservice.clients.model.response.PostMemberInfoResponse;

import java.util.List;

public interface MemberClientService {
    List<PostMemberInfoResponse> getMemberInfoForPosts(PostInfoRequest requests);
    List<CommentMemberInfoResponse> getMemberInfoForComments(CommentInfoRequest request);
}

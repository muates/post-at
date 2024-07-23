package com.muates.postservice.service.delegate.impl;

import com.muates.postservice.clients.model.response.CommentMemberInfoResponse;
import com.muates.postservice.clients.model.response.PostMemberInfoResponse;
import com.muates.postservice.model.dto.response.PostCommentResponse;
import com.muates.postservice.model.dto.response.PostResponse;
import com.muates.postservice.service.delegate.PostDelegateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostDelegateServiceImpl implements PostDelegateService {

    @Override
    public Page<PostResponse> updatePostResponse(Page<PostResponse> postResponse, List<PostMemberInfoResponse> memberResponse) {
        Map<Long, PostMemberInfoResponse> postMemberInfoMap = memberResponse.stream()
                .collect(Collectors.toMap(
                        PostMemberInfoResponse::getUserId,
                        memberInfo -> memberInfo
                ));

        List<PostResponse> updatedResponse = postResponse.getContent().stream()
                .peek(post -> {
                    PostMemberInfoResponse postMemberInfo = postMemberInfoMap.get(post.getUserId());

                    if (postMemberInfo != null) {
                        post.setUsername(postMemberInfo.getUsername());
                        post.setProfilePicture(postMemberInfo.getProfilePicture());

                        List<PostCommentResponse> postCommentResponse = post.getComments().stream()
                                .peek(postComment -> {
                                    Map<Long, CommentMemberInfoResponse> commentInfoMap = postMemberInfo.getCommentInfos().stream()
                                            .collect(Collectors.toMap(
                                                    CommentMemberInfoResponse::getUserId,
                                                    commentInfo -> commentInfo
                                            ));

                                    CommentMemberInfoResponse commentInfo = commentInfoMap.get(postComment.getUserId());

                                    if (commentInfo != null) {
                                        postComment.setUsername(commentInfo.getUsername());
                                        postComment.setProfilePicture(commentInfo.getProfilePicture());
                                    }
                                })
                                .collect(Collectors.toList());

                        post.setComments(postCommentResponse);
                    }
                })
                .collect(Collectors.toList());

        return new PageImpl<>(updatedResponse, postResponse.getPageable(), postResponse.getTotalElements());
    }
}

package com.muates.postservice.service.delegate.impl;

import com.muates.postservice.clients.model.response.PostMemberInfoResponse;
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
        List<PostResponse> updatedResponse = postResponse.stream().peek(post -> {
            Map<Long, PostMemberInfoResponse> postInfoMap = memberResponse.stream()
                    .collect(Collectors.toMap(
                            PostMemberInfoResponse::getUserId,
                            postInfo -> postInfo
                    ));

            PostMemberInfoResponse postMemberInfo = postInfoMap.get(post.getUserId());

            if (postMemberInfo != null) {
                post.setUsername(postMemberInfo.getUsername());
                post.setProfilePicture(postMemberInfo.getProfilePicture());
            }
        }).collect(Collectors.toList());

        return new PageImpl<>(updatedResponse, postResponse.getPageable(), postResponse.getTotalElements());
    }
}

package com.muates.postservice.service.delegate.impl;

import com.muates.postservice.clients.model.response.CommentMemberInfoResponse;
import com.muates.postservice.model.dto.response.PostCommentResponse;
import com.muates.postservice.service.delegate.CommentDelegateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentDelegateServiceImpl implements CommentDelegateService {


    @Override
    public Page<PostCommentResponse> updateCommentResponse(Page<PostCommentResponse> commentResponse, List<CommentMemberInfoResponse> memberResponse) {
         List<PostCommentResponse> updatedResponse = commentResponse.stream().peek(comment -> {
            Map<Long, CommentMemberInfoResponse> commentInfoMap = memberResponse.stream()
                    .collect(Collectors.toMap(
                            CommentMemberInfoResponse::getUserId,
                            commentInfo -> commentInfo
                    ));

            CommentMemberInfoResponse commentMemberInfo = commentInfoMap.get(comment.getUserId());

            if (commentMemberInfo != null) {
                comment.setUsername(commentMemberInfo.getUsername());
                comment.setProfilePicture(commentMemberInfo.getProfilePicture());
            }
        }).collect(Collectors.toList());

        return new PageImpl<>(updatedResponse, commentResponse.getPageable(), commentResponse.getTotalElements());
    }
}

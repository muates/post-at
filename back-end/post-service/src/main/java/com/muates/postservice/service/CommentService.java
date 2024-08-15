package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;
import com.muates.postservice.model.dto.response.PostCommentResponse;
import com.muates.postservice.model.entity.PostComment;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CommentService {
    void commentToPost(CommentRequest request);
    PostComment getCommentByCommentId(Long commentId);
    void updateComment(Long commentId, CommentUpdateRequest request);
    Page<PostCommentResponse> getComments(Long postId, int page, int size);
    Integer getCountByPostId(Long postId);
    Map<Long, Integer> getCountByPostIds(List<Long> postIds);
}

package com.muates.postservice.service.impl;

import com.muates.postservice.exception.CommentNotFoundException;
import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.model.entity.PostComment;
import com.muates.postservice.repository.PostCommentRepository;
import com.muates.postservice.service.CommentService;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostService postService;

    @Override
    public void commentToPost(CommentRequest request) {
        Post existPost = postService.getPostByPostId(request.getPostId());
        PostComment postComment = convertToPostComment(request, existPost);
        postCommentRepository.save(postComment);
    }

    @Override
    public void updateComment(Long commentId, CommentUpdateRequest request) {
        PostComment existComment = postCommentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment does not found!"));

        existComment.setContent(request.getContent());
        existComment.setUpdatedDate(new Date());
        postCommentRepository.save(existComment);
    }

    private PostComment convertToPostComment(CommentRequest request, Post existPost) {
        return PostComment.builder()
                .post(existPost)
                .userId(request.getUserId())
                .content(request.getContent())
                .build();
    }
}

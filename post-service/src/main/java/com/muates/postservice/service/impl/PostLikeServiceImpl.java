package com.muates.postservice.service.impl;

import com.muates.postservice.exception.PostAlreadyLikedException;
import com.muates.postservice.model.dto.request.PostReactionRequest;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.model.entity.PostLike;
import com.muates.postservice.repository.PostLikeRepository;
import com.muates.postservice.service.PostLikeService;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostService postService;

    @Override
    public void reactToPost(PostReactionRequest request) {
        postLikeRepository.findByUserIdAndPostId(request.getUserId(), request.getPostId())
                .ifPresentOrElse(
                        existLike -> updateExistReaction(existLike, request.getIsLike()),
                        () -> saveNewReaction(request)
                );
    }

    private void updateExistReaction(PostLike existLike, Boolean isLike) {
        if (existLike.getIsLike().equals(isLike)) {
            throw new PostAlreadyLikedException("This post has already been liked or disliked by this user!");
        }

        existLike.setIsLike(isLike);
        postLikeRepository.save(existLike);
    }

    private void saveNewReaction(PostReactionRequest request) {
        Post existPost = postService.getPostByPostId(request.getPostId());
        PostLike postLike = convertToPostLike(request, existPost);
        postLikeRepository.save(postLike);
    }

    private PostLike convertToPostLike(PostReactionRequest request, Post existPost) {
        return PostLike.builder()
                .userId(request.getUserId())
                .post(existPost)
                .isLike(request.getIsLike())
                .build();
    }
}

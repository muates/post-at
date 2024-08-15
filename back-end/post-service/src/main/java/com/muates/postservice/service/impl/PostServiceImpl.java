package com.muates.postservice.service.impl;

import com.muates.postservice.clients.service.MemberClientService;
import com.muates.postservice.converter.PostConverter;
import com.muates.postservice.converter.PostMediaConverter;
import com.muates.postservice.exception.PostNotFoundException;
import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.dto.request.PostUpdateRequest;
import com.muates.postservice.clients.model.request.PostInfoRequest;
import com.muates.postservice.clients.model.response.PostMemberInfoResponse;
import com.muates.postservice.model.dto.response.PostResponse;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.model.entity.PostMedia;
import com.muates.postservice.repository.PostMediaRepository;
import com.muates.postservice.repository.PostRepository;
import com.muates.postservice.service.CommentService;
import com.muates.postservice.service.PostService;
import com.muates.postservice.service.delegate.PostDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;
    private final MemberClientService memberClientService;
    private final PostDelegateService postDelegateService;
    private final CommentService commentService;

    @Override
    public Post createPost(PostCreateRequest request) {
        Post post = postRepository.save(PostConverter.convertPostCreateRequestToPost(request));

        if (request.getMedia() != null) {
            List<PostMedia> mediaList = request.getMedia().stream()
                    .map(PostMediaConverter::convertPostMediaCreateRequestToMedia)
                    .peek(media -> media.setPost(post))
                    .collect(Collectors.toList());

            postMediaRepository.saveAll(mediaList);
            post.setMedia(mediaList);
        }

        return post;
    }

    @Override
    public Page<Post> getAllPostByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<PostResponse> getPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Long> postIds = getPostIds(posts);
        List<Long> userIds = getUserIds(posts);

        Map<Long, Integer> commentCountMap = commentService.getCountByPostIds(postIds);

        Page<PostResponse> response = convertToPostResponse(posts, commentCountMap);

        PostInfoRequest postInfoRequest = new PostInfoRequest(userIds);
        List<PostMemberInfoResponse> memberResponse = memberClientService.getMemberInfoForPosts(postInfoRequest);

        return postDelegateService.updatePostResponse(response, memberResponse);
    }

    @Override
    public Post getPostByPostId(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post does not found!"));
    }

    @Override
    public Post updatePost(Long postId, PostUpdateRequest request) {
        Post existPost = getPostByPostId(postId);
        existPost.setContent(request.getContent());
        existPost.setUpdatedDate(new Date());
        return postRepository.save(existPost);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    private List<Long> getPostIds(Page<Post> posts) {
        return posts.stream()
                .map(Post::getId)
                .collect(Collectors.toList());
    }

    private List<Long> getUserIds(Page<Post> posts) {
        return posts.stream()
                .map(Post::getUserId)
                .collect(Collectors.toList());
    }

    private Page<PostResponse> convertToPostResponse(Page<Post> posts, Map<Long, Integer> commentCountMap) {
        return posts.map(post -> {
            Integer count = commentCountMap.getOrDefault(post.getId(), 0);
            return PostConverter.convertPostToResponse(post, count);
        });
    }
}

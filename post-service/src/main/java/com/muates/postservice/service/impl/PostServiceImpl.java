package com.muates.postservice.service.impl;

import com.muates.postservice.converter.PostConverter;
import com.muates.postservice.converter.PostMediaConverter;
import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.model.entity.PostMedia;
import com.muates.postservice.repository.PostMediaRepository;
import com.muates.postservice.repository.PostRepository;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;

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
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

}

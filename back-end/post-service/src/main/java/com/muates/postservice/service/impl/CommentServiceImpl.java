package com.muates.postservice.service.impl;

import com.muates.kafkaconfig.config.KafkaConfigData;
import com.muates.kafkamodel.avro.SocialInteractionNotificationAvro;
import com.muates.kafkaproducer.service.KafkaProducer;
import com.muates.postservice.clients.model.request.CommentInfoRequest;
import com.muates.postservice.clients.model.response.CommentMemberInfoResponse;
import com.muates.postservice.clients.service.MemberClientService;
import com.muates.postservice.converter.PostCommentConverter;
import com.muates.postservice.converter.SocialInteractionNotificationConverter;
import com.muates.postservice.exception.CommentNotFoundException;
import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;
import com.muates.postservice.model.dto.response.PostCommentResponse;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.model.entity.PostComment;
import com.muates.postservice.repository.PostCommentRepository;
import com.muates.postservice.service.CommentService;
import com.muates.postservice.service.PostService;
import com.muates.postservice.service.delegate.CommentDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostCommentRepository postCommentRepository;
    private final PostService postService;
    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducer<Long, SocialInteractionNotificationAvro> kafkaProducer;
    private final MemberClientService memberClientService;
    private final CommentDelegateService commentDelegateService;

    @Override
    public void commentToPost(CommentRequest request) {
        Post existPost = postService.getPostByPostId(request.getPostId());
        PostComment postComment = convertToPostComment(request, existPost);
        postCommentRepository.save(postComment);

        kafkaProducer.send(
                kafkaConfigData.getTopicNamesToCreate().get(0),
                request.getPostOwnerId(),
                SocialInteractionNotificationConverter
                        .convertToAvro(request.getUserId(), request.getPostId(), "Your post has been commented on", "POST_COMMENT")
        );
    }

    @Override
    public PostComment getCommentByCommentId(Long commentId) {
        return postCommentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment does not found!"));
    }

    @Override
    public void updateComment(Long commentId, CommentUpdateRequest request) {
        PostComment existComment = getCommentByCommentId(commentId);
        existComment.setContent(request.getContent());
        existComment.setUpdatedDate(new Date());
        postCommentRepository.save(existComment);
    }

    @Override
    public Page<PostCommentResponse> getComments(Long postId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostComment> comments = postCommentRepository.findByPostId(postId, pageable);

        Page<PostCommentResponse> response = comments.map(PostCommentConverter::convertPostCommentToResponse);

        List<Long> userIds = comments.stream()
                .map(PostComment::getUserId)
                .collect(Collectors.toList());

        CommentInfoRequest commentInfoRequest = new CommentInfoRequest(userIds);

        List<CommentMemberInfoResponse> commentMemberInfoResponse = memberClientService.getMemberInfoForComments(commentInfoRequest);

        return commentDelegateService.updateCommentResponse(response, commentMemberInfoResponse);
    }

    @Override
    public int getCountByPostId(Long postId) {
        return postCommentRepository.countByPostId(postId);
    }

    private PostComment convertToPostComment(CommentRequest request, Post existPost) {
        return PostComment.builder()
                .post(existPost)
                .userId(request.getUserId())
                .content(request.getContent())
                .build();
    }
}

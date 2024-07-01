package com.muates.postservice.service.impl;

import com.muates.kafkaconfig.config.KafkaConfigData;
import com.muates.kafkamodel.avro.SocialInteractionNotificationAvro;
import com.muates.kafkaproducer.service.KafkaProducer;
import com.muates.postservice.converter.SocialInteractionNotificationConverter;
import com.muates.postservice.exception.CommentAlreadyLikedException;
import com.muates.postservice.model.dto.request.CommentReactionRequest;
import com.muates.postservice.model.entity.PostComment;
import com.muates.postservice.model.entity.PostCommentLike;
import com.muates.postservice.repository.PostCommentLikeRepository;
import com.muates.postservice.service.CommentLikeService;
import com.muates.postservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService {

    private final PostCommentLikeRepository postCommentLikeRepository;
    private final CommentService commentService;
    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducer<Long, SocialInteractionNotificationAvro> kafkaProducer;

    @Override
    public void reactToComment(CommentReactionRequest request) {
        postCommentLikeRepository.findByUserIdAndCommentId(request.getUserId(), request.getCommentId())
                .ifPresentOrElse(
                        existLike -> updateExistReaction(existLike, request.getIsLike()),
                        () -> saveNewReaction(request)
                );

        kafkaProducer.send(
                kafkaConfigData.getTopicNamesToCreate().get(0),
                request.getCommentOwnerId(),
                SocialInteractionNotificationConverter
                        .convertToAvro(request.getUserId(), request.getPostId(), "Your comment has been liked", "COMMENT_LIKE")
        );
    }

    private void updateExistReaction(PostCommentLike existLike, Boolean isLike) {
        if (existLike.getIsLike().equals(isLike))  {
            throw new CommentAlreadyLikedException("This comment has already been liked or disliked by this user!");
        }

        existLike.setIsLike(isLike);
        postCommentLikeRepository.save(existLike);
    }

    private void saveNewReaction(CommentReactionRequest request) {
        PostComment existComment = commentService.getCommentByCommentId(request.getCommentId());
        PostCommentLike commentLike = convertToPostCommentLike(request, existComment);
        postCommentLikeRepository.save(commentLike);
    }

    private PostCommentLike convertToPostCommentLike(CommentReactionRequest request, PostComment existComment) {
        return PostCommentLike.builder()
                .userId(request.getUserId())
                .comment(existComment)
                .isLike(request.getIsLike())
                .build();
    }
}

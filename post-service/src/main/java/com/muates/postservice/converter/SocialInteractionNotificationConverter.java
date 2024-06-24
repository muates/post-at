package com.muates.postservice.converter;

import com.muates.kafkamodel.avro.SocialInteractionNotificationAvro;
import com.muates.kafkamodel.avro.SocialInteractionType;

public class SocialInteractionNotificationConverter {

    public static SocialInteractionNotificationAvro convertToAvro(Long userId, Long postId, String message, String type) {
        return SocialInteractionNotificationAvro.newBuilder()
                .setUserId(userId)
                .setPostId(postId)
                .setMessage(message)
                .setType(convertSocialInteractionType(type))
                .setTimestamp(System.currentTimeMillis())
                .build();
    }

    private static SocialInteractionType convertSocialInteractionType(String type) {
        switch (type) {
            case "POST_LIKE":
                return SocialInteractionType.POST_LIKE;
            case "POST_COMMENT":
                return SocialInteractionType.POST_COMMENT;
            case "COMMENT_LIKE":
                return SocialInteractionType.COMMENT_LIKE;
            default:
                throw new IllegalArgumentException("Unsupported notification type: " + type);
        }
    }
}

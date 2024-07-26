package com.muates.memberservice.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CommentInfoRequest {
    List<Long> userIds;
}

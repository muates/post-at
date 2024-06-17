package com.muates.postservice.model.dto.request;

import com.muates.postservice.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostCreateRequest {
    private Long userId;
    private String content;
    private List<PostMediaCreateRequest> media;
}

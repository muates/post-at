package com.muates.postservice.model.dto.request;

import com.muates.postservice.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMediaCreateRequest {
    private String mediaUrl;
    private MediaType mediaType;
}

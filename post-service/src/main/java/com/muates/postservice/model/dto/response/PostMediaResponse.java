package com.muates.postservice.model.dto.response;

import com.muates.postservice.model.enums.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMediaResponse {

    private Long id;
    private String mediaUrl;
    private MediaType mediaType;
    private Date createdDate;
}

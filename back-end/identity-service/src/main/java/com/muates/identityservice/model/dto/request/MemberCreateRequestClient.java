package com.muates.identityservice.model.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCreateRequestClient {
    private Long id;
    private String username;
    private String email;
}

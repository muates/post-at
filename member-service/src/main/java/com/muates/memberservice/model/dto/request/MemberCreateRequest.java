package com.muates.memberservice.model.dto.request;

import lombok.Data;

@Data
public class MemberCreateRequest {
    private Long id;
    private String username;
    private String email;
}

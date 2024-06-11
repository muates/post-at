package com.muates.identityservice.model.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class MemberResponseClient {
    private Long id;
    private String username;
    private String email;
    private Date createdDate;
    private Date updatedDate;
}

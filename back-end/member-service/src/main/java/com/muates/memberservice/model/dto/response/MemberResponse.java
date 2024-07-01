package com.muates.memberservice.model.dto.response;

import com.muates.memberservice.model.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MemberResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;
    private String country;
    private String profileImageUrl;
    private String biography;
    private String website;
    private String links;
    private String socialMediaLinks;
    private Date createdDate;
    private Date updatedDate;
}

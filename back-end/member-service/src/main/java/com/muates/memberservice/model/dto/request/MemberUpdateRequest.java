package com.muates.memberservice.model.dto.request;

import com.muates.memberservice.model.enums.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MemberUpdateRequest {
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
}

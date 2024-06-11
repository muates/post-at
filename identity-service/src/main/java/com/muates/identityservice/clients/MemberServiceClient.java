package com.muates.identityservice.clients;

import com.muates.identityservice.model.dto.request.MemberCreateRequestClient;
import com.muates.identityservice.model.dto.response.MemberResponseClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "member-service")
public interface MemberServiceClient {

    @PostMapping("/member-service/api/member/create")
    ResponseEntity<MemberResponseClient> createMember(MemberCreateRequestClient request);
}

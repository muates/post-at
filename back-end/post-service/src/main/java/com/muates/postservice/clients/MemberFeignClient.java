package com.muates.postservice.clients;

import com.muates.postservice.clients.model.request.PostWithCommentInfoRequest;
import com.muates.postservice.clients.model.response.PostMemberInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "member-service")
public interface MemberFeignClient {

    @PostMapping("/api/member/member-info-for-post")
    ResponseEntity<List<PostMemberInfoResponse>> getMemberInfoForPost(@RequestBody List<PostWithCommentInfoRequest> requests);
}

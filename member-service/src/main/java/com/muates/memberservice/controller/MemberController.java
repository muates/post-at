package com.muates.memberservice.controller;

import com.muates.memberservice.converter.MemberConverter;
import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.dto.response.MemberResponse;
import com.muates.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberCreateRequest request) {
        MemberResponse response = MemberConverter.convertMemberToResponse(memberService.createMember(request));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{memberId}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long memberId,
                                                       @RequestBody MemberUpdateRequest request) {
        MemberResponse response = MemberConverter.convertMemberToResponse(memberService.updateMember(memberId, request));
        return ResponseEntity.ok(response);
    }
}

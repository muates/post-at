package com.muates.memberservice.controller;

import com.muates.memberservice.converter.MemberConverter;
import com.muates.memberservice.model.dto.request.CommentInfoRequest;
import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.dto.request.PostInfoRequest;
import com.muates.memberservice.model.dto.response.CommentMemberInfoResponse;
import com.muates.memberservice.model.dto.response.PostMemberInfoResponse;
import com.muates.memberservice.model.dto.response.MemberResponse;
import com.muates.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/member-info-for-post")
    public ResponseEntity<List<PostMemberInfoResponse>> getMemberInfoForPost(@RequestBody PostInfoRequest request) {
        List<PostMemberInfoResponse> response = MemberConverter.convertMemberToPostInfoResponse(memberService.findMembers(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/member-info-for-comment")
    public ResponseEntity<List<CommentMemberInfoResponse>> getMemberInfoForComment(@RequestBody CommentInfoRequest request) {
        List<CommentMemberInfoResponse> response = MemberConverter.convertMemberToCommentInfoResponse(memberService.findMembers(request));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId) {
        MemberResponse response = MemberConverter.convertMemberToResponse(memberService.findMemberByMemberId(memberId));
        return ResponseEntity.ok(response);
    }
}

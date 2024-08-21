package com.muates.memberservice.service;

import com.muates.memberservice.model.dto.request.CommentInfoRequest;
import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.dto.request.PostInfoRequest;
import com.muates.memberservice.model.entity.Member;

import java.util.List;

public interface MemberService {
    Member createMember(MemberCreateRequest request);
    Member updateMember(Long memberId, MemberUpdateRequest request);
    Member findMemberByMemberId(Long memberId);
    List<Member> findMembers(PostInfoRequest postInfoRequests);
    List<Member> findMembers(CommentInfoRequest request);
}

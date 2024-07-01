package com.muates.memberservice.service;

import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.entity.Member;

public interface MemberService {
    Member createMember(MemberCreateRequest request);
    Member updateMember(Long memberId, MemberUpdateRequest request);
    Member findMemberByMemberId(Long memberId);
}

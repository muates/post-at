package com.muates.memberservice.service;

import com.muates.memberservice.model.dto.request.CommentInfoRequest;
import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.dto.request.PostWithCommentInfoRequest;
import com.muates.memberservice.model.entity.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    Member createMember(MemberCreateRequest request);
    Member updateMember(Long memberId, MemberUpdateRequest request);
    Member findMemberByMemberId(Long memberId);
    Map<Member, List<Member>> findMembers(List<PostWithCommentInfoRequest> postWithCommentInfoRequests);
    List<Member> findMembers(CommentInfoRequest request);
}

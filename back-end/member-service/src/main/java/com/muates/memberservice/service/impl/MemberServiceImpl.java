package com.muates.memberservice.service.impl;

import com.muates.memberservice.converter.MemberConverter;
import com.muates.memberservice.exception.MemberNotFoundException;
import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.entity.Member;
import com.muates.memberservice.repository.MemberRepository;
import com.muates.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberCreateRequest request) {
        Member member = MemberConverter.convertMemberCreateRequestToMember(request);
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Long memberId, MemberUpdateRequest request) {
        Member existMember = findMemberByMemberId(memberId);
        MemberConverter.convertMemberUpdateRequestToMember(existMember, request);
        return memberRepository.save(existMember);
    }

    @Override
    public Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member does not found!"));
    }
}

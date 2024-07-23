package com.muates.memberservice.converter;

import com.muates.memberservice.model.dto.request.MemberCreateRequest;
import com.muates.memberservice.model.dto.request.MemberUpdateRequest;
import com.muates.memberservice.model.dto.response.CommentMemberInfoResponse;
import com.muates.memberservice.model.dto.response.PostMemberInfoResponse;
import com.muates.memberservice.model.dto.response.MemberResponse;
import com.muates.memberservice.model.entity.Member;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MemberConverter {

    public static Member convertMemberCreateRequestToMember(MemberCreateRequest request) {
        if (request == null) {
            return null;
        }

        return Member.builder()
                .id(request.getId())
                .username(request.getUsername())
                .email(request.getEmail())
                .build();
    }

    public static Member convertMemberUpdateRequestToMember(Member member, MemberUpdateRequest request) {
        if (request == null) {
            return null;
        }

        updateField(request.getFirstName(), member::setFirstName);
        updateField(request.getLastName(), member::setLastName);
        updateField(request.getGender(), member::setGender);
        updateField(request.getBirthDate(), member::setBirthDate);
        updateField(request.getCountry(), member::setCountry);
        updateField(request.getProfileImageUrl(), member::setProfileImageUrl);
        updateField(request.getBiography(), member::setBiography);
        updateField(request.getWebsite(), member::setWebsite);
        updateField(request.getLinks(), member::setLinks);
        updateField(request.getSocialMediaLinks(), member::setSocialMediaLinks);
        member.setUpdatedDate(new Date());

        return member;
    }

    public static MemberResponse convertMemberToResponse(Member member) {
        if (member == null) {
            return null;
        }

        return MemberResponse.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .gender(member.getGender())
                .birthDate(member.getBirthDate())
                .country(member.getCountry())
                .profileImageUrl(member.getProfileImageUrl())
                .biography(member.getBiography())
                .website(member.getWebsite())
                .links(member.getLinks())
                .socialMediaLinks(member.getSocialMediaLinks())
                .createdDate(member.getCreatedDate())
                .updatedDate(member.getUpdatedDate())
                .build();
    }

    public static List<PostMemberInfoResponse> convertMemberToPostInfoResponse(Map<Member, List<Member>> memberMap) {
        if (memberMap == null) {
            return null;
        }

        return memberMap.entrySet().stream().map(entry -> {
            Member postMember = entry.getKey();
            List<Member> commentMembers = entry.getValue();

            List<CommentMemberInfoResponse> commentMemberInfoResponse = commentMembers.stream().map(commentMember ->
                    CommentMemberInfoResponse.builder()
                            .userId(commentMember.getId())
                            .username(commentMember.getUsername())
                            .profilePicture(commentMember.getProfileImageUrl())
                            .build()
            ).collect(Collectors.toList());

            return PostMemberInfoResponse.builder()
                    .userId(postMember.getId())
                    .username(postMember.getUsername())
                    .profilePicture(postMember.getProfileImageUrl())
                    .commentInfos(commentMemberInfoResponse)
                    .build();
        }).collect(Collectors.toList());
    }

    private static <T> void updateField(T target, Consumer<T> updater) {
        Optional.ofNullable(target).ifPresent(updater);
    }
}

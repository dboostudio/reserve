package com.youngilee.web.module.fortest;

import com.youngilee.web.module.member.MemberService;
import com.youngilee.web.module.member.MemberUser;
import com.youngilee.web.module.member.mapper.MemberMapper;
import com.youngilee.web.module.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class WithMemberSecurityContextFactory implements WithSecurityContextFactory<WithMember> {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @Override
    public SecurityContext createSecurityContext(WithMember withMember) {

        Member member = Member.builder()
                .mbPhone("010-9134-6572")
                .mbPwd("eoghks12").build();

        try {
            memberService.login(member);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return SecurityContextHolder.getContext();
    }
}

package com.server.cms.service;

import com.server.cms.domain.oracle.CpUser;
import com.server.cms.data.response.SUser;
import com.server.cms.repository.oracle.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(isBlank(username)) {
            throw new NullPointerException("사용자 아이디가 존재하지 않습니다.");
        }

        return null;
    }

    public SUser.Read findByLoginUser(String userId, String pw) {
        if(isBlank(userId)) {
            throw new NullPointerException("사용자 아이디가 존재하지 않습니다.");
        }

        Optional<CpUser> optionalUser = userRepository.findByUserId(userId);
        CpUser cpUser = optionalUser.orElseThrow(() -> new NullPointerException("아이디 또는 비밀번호가 일치하지 않습니다."));

        cpUser.checkLoginPassword(pw, encoder);

        return SUser.Read.from(cpUser);
    }

}

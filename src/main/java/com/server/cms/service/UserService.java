package com.server.cms.service;

import com.server.cms.config.jwt.JWT;
import com.server.cms.domain.CpUser;
import com.server.cms.data.response.ResUser;
import com.server.cms.domain.Token;
import com.server.cms.framework.error.UserNotFoundException;
import com.server.cms.repository.TokenRepository;
import com.server.cms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JWT jwt;
    private final PasswordEncoder encoder;

    public ResUser.Read findByLoginUser(String userId, String pw) {
        if(isBlank(userId)) {
            throw new NullPointerException("사용자 아이디가 존재하지 않습니다.");
        }

        Optional<CpUser> optionalUser = userRepository.findByUserId(userId);
        CpUser cpUser = optionalUser.orElseThrow(() -> new NullPointerException("아이디 또는 비밀번호가 일치하지 않습니다."));
        cpUser.checkLoginPassword(pw, encoder);

        return ResUser.Read.from(cpUser);
    }

    public void saveRefreshToken(String accessToken) {
        if(isEmpty(accessToken)) {
            throw new UserNotFoundException();
        }

        JWT.Claims claims = jwt.verify(accessToken);
        String refreshToken = jwt.refreshToken(accessToken);
        Token token = Token.create(claims.getUserId(), refreshToken);
        tokenRepository.save(token);
    }

    public void deleteRefreshToken(String cpId) {
        Optional<Token> tokenOptional = tokenRepository.findByUserId(cpId);
        Token token = tokenOptional.orElseThrow(() -> new UserNotFoundException());
        token.tokenRemove();
    }

}

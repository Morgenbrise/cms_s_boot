package com.server.cms.service;

import com.server.cms.domain.CpUser;
import com.server.cms.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("연결 테스트")
    @Transactional
    void save_test() {
        CpUser cpUser = CpUser.create();
        CpUser save = userRepository.save(cpUser);
        System.out.println(save.getId());
    }

}
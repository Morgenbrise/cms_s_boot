package com.server.cms.repository;

import com.server.cms.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t where t.userId = :userId and t.useYn = 'Y'")
    Optional<Token> findByUserId(String userId);

}

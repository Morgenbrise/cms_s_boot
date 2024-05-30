package com.server.cms.repository;

import com.server.cms.domain.CpUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<CpUser, Long> {

    @Query("select u from CpUser u where u.id = :userId")
    Optional<CpUser> findByUserId(String userId);

    @Query("select u from CpUser u where u.companyCode = :companyCode")
    Optional<CpUser> findByUserCompanyCode(String companyCode);
}

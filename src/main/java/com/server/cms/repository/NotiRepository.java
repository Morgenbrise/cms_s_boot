package com.server.cms.repository;

import com.server.cms.domain.support.Notification;
import com.server.cms.query.support.QueryNotiRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotiRepository extends JpaRepository<Notification, Long>, QueryNotiRepository {
}

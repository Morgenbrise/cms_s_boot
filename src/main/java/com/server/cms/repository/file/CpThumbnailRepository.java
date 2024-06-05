package com.server.cms.repository.file;

import com.server.cms.domain.thumbnail.CpThumbnail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpThumbnailRepository extends JpaRepository<CpThumbnail, Long> {
}

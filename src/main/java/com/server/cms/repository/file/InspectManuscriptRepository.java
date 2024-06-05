package com.server.cms.repository.file;

import com.server.cms.domain.manuscript.InspectManuscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectManuscriptRepository extends JpaRepository<InspectManuscript, Long> {
}

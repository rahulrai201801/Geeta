package com.gitamentor.repository;

import com.gitamentor.model.UnansweredQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnansweredQueryRepository extends JpaRepository<UnansweredQuery, Long> {
}

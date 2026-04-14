package com.gitamentor.repository;

import com.gitamentor.model.Shloka;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShlokaRepository extends JpaRepository<Shloka, Long> {
    List<Shloka> findByCategory(String category);
}

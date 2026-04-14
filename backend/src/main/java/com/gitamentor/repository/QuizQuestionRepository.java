package com.gitamentor.repository;

import com.gitamentor.model.QuizQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> {
    List<QuizQuestion> findByCategory(String category);

    @Query(value = "SELECT * FROM quiz_questions WHERE category IN (:categories) ORDER BY RAND() LIMIT :limit",
           nativeQuery = true)
    List<QuizQuestion> findRandomByCategories(@Param("categories") List<String> categories,
                                               @Param("limit") int limit);

    @Query(value = "SELECT * FROM quiz_questions ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<QuizQuestion> findRandom(@Param("limit") int limit);
}

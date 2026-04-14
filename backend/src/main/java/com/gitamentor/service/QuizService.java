package com.gitamentor.service;

import com.gitamentor.model.QuizQuestion;
import com.gitamentor.repository.QuizQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final MentorService mentorService;

    public QuizService(QuizQuestionRepository quizQuestionRepository,
                       MentorService mentorService) {
        this.quizQuestionRepository = quizQuestionRepository;
        this.mentorService = mentorService;
    }

    public List<QuizQuestion> getQuizForUser(Long userId) {
        List<String> userCategories = mentorService.getUserCategories(userId);

        if (!userCategories.isEmpty()) {
            // Fetch 5 random questions from user's searched categories
            List<QuizQuestion> questions =
                quizQuestionRepository.findRandomByCategories(userCategories, 5);
            if (!questions.isEmpty()) {
                return questions;
            }
        }

        // Fallback: random 5 questions from any category
        return quizQuestionRepository.findRandom(5);
    }
}

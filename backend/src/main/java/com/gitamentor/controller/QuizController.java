package com.gitamentor.controller;

import com.gitamentor.model.QuizQuestion;
import com.gitamentor.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // GET /quiz?userId=1
    @GetMapping("/quiz")
    public ResponseEntity<List<QuizQuestion>> getQuiz(@RequestParam Long userId) {
        List<QuizQuestion> questions = quizService.getQuizForUser(userId);
        return ResponseEntity.ok(questions);
    }
}

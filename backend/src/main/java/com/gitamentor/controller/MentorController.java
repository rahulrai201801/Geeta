package com.gitamentor.controller;

import com.gitamentor.model.UnansweredQuery;
import com.gitamentor.repository.UnansweredQueryRepository;
import com.gitamentor.service.MentorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class MentorController {

    private final MentorService mentorService;
    private final UnansweredQueryRepository unansweredQueryRepository;

    public MentorController(MentorService mentorService,
                            UnansweredQueryRepository unansweredQueryRepository) {
        this.mentorService = mentorService;
        this.unansweredQueryRepository = unansweredQueryRepository;
    }

    // POST /mentor
    @PostMapping("/mentor")
    public ResponseEntity<Map<String, Object>> getMentorResponse(@RequestBody Map<String, Object> body) {
        Long userId = Long.parseLong(body.get("userId").toString());
        String query = body.get("query").toString();
        Map<String, Object> response = mentorService.getMentorResponse(userId, query);
        return ResponseEntity.ok(response);
    }

    // POST /save-unanswered  (manual save from frontend)
    @PostMapping("/save-unanswered")
    public ResponseEntity<Map<String, Object>> saveUnanswered(@RequestBody Map<String, Object> body) {
        Long userId = Long.parseLong(body.get("userId").toString());
        String query = body.get("query").toString();

        UnansweredQuery uq = new UnansweredQuery();
        uq.setUserId(userId);
        uq.setQuery(query);
        unansweredQueryRepository.save(uq);

        return ResponseEntity.ok(Map.of("success", true, "message", "Query saved for admin review."));
    }
}

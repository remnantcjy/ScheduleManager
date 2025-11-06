package org.example.schedulemanager.controller;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.CreateCommentRequest;
import org.example.schedulemanager.dto.CreateCommentResponse;
import org.example.schedulemanager.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    // CommentService 주입
    private final CommentService commentService;

    // Lv 5. 댓글 생성
    @PostMapping("/schedules/{id}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long id,
            @RequestBody CreateCommentRequest request
    ) {

        CreateCommentResponse result = commentService.save(id, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}

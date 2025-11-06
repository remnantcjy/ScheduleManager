package org.example.schedulemanager.service;

import lombok.RequiredArgsConstructor;
import org.example.schedulemanager.dto.CreateCommentRequest;
import org.example.schedulemanager.dto.CreateCommentResponse;
import org.example.schedulemanager.entity.Comment;
import org.example.schedulemanager.entity.Schedule;
import org.example.schedulemanager.repository.CommentRepository;
import org.example.schedulemanager.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    // CommentRepository 주입
    private final CommentRepository commentRepository;

    // ScheduleRepository 주입
    private final ScheduleRepository scheduleRepository;


    @Transactional
    public CreateCommentResponse save(Long id, CreateCommentRequest request) {

        // 해당 id의 일정이 있는지 확인 / 예외 처리
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        // 해당 id의 "일정"이 있을 때
        // request에서 값 꺼내와 Comment 객체로 변환
        Comment comment = new Comment(
                request.getContents(),
                request.getName(),
                request.getPassword()
        );

        // 댓글에 일정을 저장
        comment.setSchedule(schedule);

        // 레포지토리에 댓글 저장 -> 저장된 댓글 반환
        Comment savedComment = commentRepository.save(comment);

        // 댓글 리스트에 저장된 댓글을 추가
        List<Comment> commentList = schedule.getCommentList();
        commentList.add(savedComment);


        // 레포지토리에 데이터 처리된 댓글을 사용자에게 응답
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContents(),
                savedComment.getName(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }
}

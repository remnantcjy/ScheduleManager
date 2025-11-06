package org.example.schedulemanager.repository;

import org.example.schedulemanager.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}

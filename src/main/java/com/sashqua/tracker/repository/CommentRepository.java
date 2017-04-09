package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer>{
}

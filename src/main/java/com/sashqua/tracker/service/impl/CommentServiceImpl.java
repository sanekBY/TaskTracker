package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.repository.CommentRepository;
import com.sashqua.tracker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service("commentService")
@Validated
public class CommentServiceImpl implements CommentService {
    @Autowired
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getComment(Integer id) {
        return commentRepository.findOne(id);
    }
}

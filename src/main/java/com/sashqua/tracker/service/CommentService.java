package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.User;

public interface CommentService {
    Comment addComment(Comment comment);
    Comment getComment(Integer id);
}

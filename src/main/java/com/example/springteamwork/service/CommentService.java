package com.example.springteamwork.service;

import com.example.jpa.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    void saveComment(Comment post);
    Comment getCommentId(Long id);
    void deleteCommentById(Long id);
}

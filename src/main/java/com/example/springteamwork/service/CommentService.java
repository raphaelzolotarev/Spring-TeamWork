package com.example.springteamwork.service;
import com.example.springteamwork.model.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByPostId(Long id);
    Comment getCommentById(Long id);
    void saveComment(Comment comment);
    void updateComment(Long id, String comment);
    void deleteComment(Long id);
}

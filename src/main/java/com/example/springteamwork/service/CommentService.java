package com.example.springteamwork.service;

import com.example.springteamwork.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments();
    void saveComment(Comment comment);
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    List<Comment> getAllCommentsByPostID(Long postId);
    List<Comment> getAllCommentsByUserID(Long userId);
    Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable);
    Optional<Comment> getCommentByIdAndPostId(Long id, Long postId);
    Optional<Comment> getCommentByIdAndUserId(Long id, Long userId);
}

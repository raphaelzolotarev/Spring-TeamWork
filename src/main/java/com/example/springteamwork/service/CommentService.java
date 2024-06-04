package com.example.springteamwork.service;
import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByPostId(Long id);
    Comment getCommentById(Long id);
    void saveComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long id);
}

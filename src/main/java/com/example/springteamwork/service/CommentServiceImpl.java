package com.example.springteamwork.service;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Page<Comment> getAllCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public List<Comment> getAllCommentsByPostID(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> getAllCommentsByUserID(Long userId) {
        return commentRepository.findByUserId(userId);
    }

    @Override
    public Optional<Comment> getCommentByIdAndPostId(Long id, Long postId) {
        return commentRepository.findByIdAndPostId(id, postId);
    }

    @Override
    public Optional<Comment> getCommentByIdAndUserId(Long id, Long userId) {
        return commentRepository.findByIdAndUserId(id, userId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()) {
            throw new IllegalStateException("Comment doesn't exist");
        }
        return commentOptional.get();
    }

    @Override
    public void deleteCommentById(Long id) {
        boolean exists = commentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Comment id " + id + " does not exist");
        }
        commentRepository.deleteById(id);
    }
}

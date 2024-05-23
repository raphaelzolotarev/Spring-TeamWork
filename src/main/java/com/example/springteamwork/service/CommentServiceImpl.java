package com.example.springteamwork.service;


import com.example.springteamwork.model.Comment;
import com.example.springteamwork.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllCommentsByPostID(Long id) {
        return commentRepository.findAll().stream().filter(c->c.getPost().getId()==id).toList();
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public Comment getCommentId(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(!commentOptional.isPresent()){
            throw new IllegalStateException("comment doesn't exist");
        }
        return commentOptional.get();
    }

    @Override
    public void deleteCommentById(Long id) {
        boolean exists = commentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("comment id "+id+" does not exists");
        }
        commentRepository.deleteById(id);
    }

}

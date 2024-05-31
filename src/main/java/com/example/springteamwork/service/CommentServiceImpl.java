package com.example.springteamwork.service;
import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.repository.CommentRepository;
import com.example.springteamwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllCommentsByPostId(Long id) {
        return commentRepository.findAll().stream().filter(c->c.getPost().getId()==id).collect(Collectors.toList());
    }

    @Override
    public Post getCommentById(Long id) {
        return null;
    }

    @Override
    public void saveComment(Comment comment) {
        if (comment.getText()!=null &&  !comment.getText().isEmpty())
            commentRepository.save(comment);
    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}

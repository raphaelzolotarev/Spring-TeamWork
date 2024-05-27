package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.service.CommentService;
import com.example.springteamwork.service.CommentServiceImpl;
import com.example.springteamwork.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/comments")
    public String viewCommentsPage(Model model) {
        model.addAttribute("listComments", commentService.getAllComments());
        return "comments";
    }

    @GetMapping("/comments/post/{postId}")
    public String viewCommentsByPostId(@PathVariable Long postId, Model model) {
        List<Comment> comments = commentService.getAllCommentsByPostID(postId);
        Post post = postService.getPostId(postId);
        Comment newComment = new Comment();
        newComment.setPost(post);

        model.addAttribute("comments", comments);
        model.addAttribute("post", post);
        model.addAttribute("newComment", newComment);
        return "post_comments";
    }

    @GetMapping("/comments/new")
    public String showNewCommentForm(Model model) {
        Comment comment = new Comment();
        model.addAttribute("comment", comment);
        return "new_comment";
    }

    @PostMapping("/comments")
    public String saveComment(@ModelAttribute("comment") Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/update/{id}")
    public String showUpdateCommentForm(@PathVariable Long id, Model model) {
        Comment comment = commentService.getCommentId(id);
        model.addAttribute("comment", comment);
        return "update_comment";
    }



    @PostMapping("/comments/update/{id}")
    public String updateComment(@PathVariable Long id, @ModelAttribute("comment") Comment comment) {
        Comment existingComment = commentService.getCommentId(id);
        existingComment.setText(comment.getText());
        commentService.saveComment(existingComment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/comments";
    }
}
package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.model.User;
import com.example.springteamwork.service.CommentService;
import com.example.springteamwork.service.PostService;
import com.example.springteamwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {
/*
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/comments")
    public String viewCommentsPage(Model model) {
        model.addAttribute("listComments", commentService.getAllComments());
        return "comments";
    }

    @GetMapping("/comments/post/{postId}")
    public String viewCommentsByPostId(@PathVariable Long postId, Model model, Pageable pageable) {
        Page<Comment> commentsPage = commentService.getAllCommentsByPostId(postId, pageable);
        List<Comment> comments = commentsPage.getContent();
        Post post = postService.getPostId(postId);
        Comment newComment = new Comment();
        newComment.setPost(post);

        model.addAttribute("comments", comments);
        model.addAttribute("post", post);
        model.addAttribute("newComment", newComment);
        return "post_comments";
    }

    @GetMapping("/comments/user/{userId}")
    public String viewCommentsByUserId(@PathVariable Long userId, Model model) {
        List<Comment> comments = commentService.getAllCommentsByUserID(userId);
        User user = userService.getUserById(userId);

        model.addAttribute("comments", comments);
        model.addAttribute("user", user);
        return "user_comments";
    }

    @GetMapping("/comments/{id}/post/{postId}")
    public ResponseEntity<Comment> getCommentByIdAndPostId(@PathVariable Long id, @PathVariable Long postId) {
        Optional<Comment> comment = commentService.getCommentByIdAndPostId(id, postId);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/comments/{id}/user/{userId}")
    public ResponseEntity<Comment> getCommentByIdAndUserId(@PathVariable Long id, @PathVariable Long userId) {
        Optional<Comment> comment = commentService.getCommentByIdAndUserId(id, userId);
        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
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
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "update_comment";
    }



    @PostMapping("/comments/update/{id}")
    public String updateComment(@PathVariable Long id, @ModelAttribute("comment") Comment comment) {
        Comment existingComment = commentService.getCommentById(id);
        existingComment.setText(comment.getText());
        commentService.saveComment(existingComment);
        return "redirect:/comments";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return "redirect:/comments";
    }

 */
}

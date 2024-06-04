package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.model.User;
import com.example.springteamwork.service.CommentService;
import com.example.springteamwork.service.CommentServiceImpl;
import com.example.springteamwork.service.PostServiceImpl;
import com.example.springteamwork.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PostServiceImpl postService;

    /*ADD NEW COMMENT*/
    @PostMapping("/newComment")
    public String createComment(@RequestParam(value = "authorId") Long authorId,
                                @RequestParam(value = "postId") Long postId,
                                @RequestParam(value = "text") String text) {
        Comment newComment = new Comment(text, postService.getPostById(postId), userService.getUserById(authorId));
        commentService.saveComment(newComment);
        return "redirect:/showPost/"+postId;
    }





    /*DELETE COMMENT*/
    @GetMapping("/deleteComment/{postId}/{commentId}")
    public String deleteComment(@PathVariable(value = "postId") Long postId,
                                @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/showPost/"+postId;
    }

}

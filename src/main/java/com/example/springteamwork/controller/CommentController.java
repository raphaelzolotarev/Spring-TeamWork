package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.service.CommentServiceImpl;
import com.example.springteamwork.service.PostServiceImpl;
import com.example.springteamwork.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class    CommentController {

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

    /*EDIT COMMENT*/
    @PostMapping("/editComment")
    public String editComment(@RequestParam(value = "postId") Long postId,
                            @RequestParam(value = "commentId") Long commentId,
                            @RequestParam(value = "comment") String comment) {
        commentService.updateComment(commentId, comment);
        return "redirect:/showPost/" + postId;
    }

    /*DELETE COMMENT*/
    @GetMapping("/deleteComment/{postId}/{commentId}")
    public String deleteComment(@PathVariable(value = "postId") Long postId,
                                @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/showPost/"+postId;
    }

}

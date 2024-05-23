package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.service.CommentServiceImpl;
import com.example.springteamwork.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;
    @GetMapping("/")
    public String viewHomepage(Model model){
        model.addAttribute("ListPost", postService.getAllPosts());
        return "index";
    }
    @GetMapping("/showPost/{id}")
    public String viewPostById(Model model, @PathVariable Long id){
        Post post = postService.getPostId(id);
        Comment comment = new Comment();
        comment.setPost(post);
        List<Comment> comments = commentService.getAllCommentsByPostID(id);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("newComment", comment);
        return "post_page";
    }
    @GetMapping("/addNewPost")
    public String showNewPostForm(Model model){
        Post post = new Post();
        model.addAttribute("post", post);
        return "new_post";
    }
    @PostMapping("/newPost")
    public String newPost(@ModelAttribute("post") Post post){
        postService.savePost(post);
        return "redirect:/";
    }
    @GetMapping("/updatePost/{id}")
    public String showUpdatePostForm(@PathVariable(value="id") Long id, Model model){
        Post post = postService.getPostId(id);
        model.addAttribute("post", post);
        return "update_show";
    }
    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value="id") Long id){
        postService.deletePostById((id));
        return "redirect:/";
    }



}

package com.example.springteamwork.controller;

import com.example.springteamwork.model.Post;
import com.example.springteamwork.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/")
    public String viewHomepage(Model model) {
        model.addAttribute("ListPost", postService.getAllPosts());
        return "index";
    }

    @GetMapping("/showPost/{id}")
    public String viewPostById(Model model, @PathVariable Long id) {
        Post post = postService.getPostId(id);
        model.addAttribute("post", post);
        return "post_page";
    }

    @GetMapping("/addNewPost")
    public String showNewPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "new_post";
    }

    @PostMapping("/newPost")
    public String newPost(@ModelAttribute("post") Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping("/updatePost/{id}")
    public String showUpdatePostForm(@PathVariable(value = "id") Long id, Model model) {
        Post post = postService.getPostId(id);
        model.addAttribute("post", post);
        return "update_post";
    }

    @PostMapping("/updatePost/{id}")
    public String updatePost(@PathVariable(value = "id") Long id, @ModelAttribute("post") Post postDetails) {
        Post post = postService.getPostId(id);
        post.setTitle(postDetails.getTitle());
        post.setDescription(postDetails.getDescription());
        postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") Long id) {
        postService.deletePostById(id);
        return "redirect:/";
    }
}

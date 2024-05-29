package com.example.springteamwork.controller;

import com.example.springteamwork.model.Comment;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.model.User;
import com.example.springteamwork.service.CommentServiceImpl;
import com.example.springteamwork.service.PostServiceImpl;
import com.example.springteamwork.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOError;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @Autowired
    private PostServiceImpl postService;
    @Autowired
    private CommentServiceImpl commentService;



    /*SHOW ALL POSTS*/
    @GetMapping("/")
    public String showAllPost(@RequestParam(value = "filter", required = false, defaultValue = "new") String filter, Model model) {
        List<Post> posts =  postService.getAllPosts().stream().sorted(Comparator.comparing(Post::getId).reversed()).collect(Collectors.toList());

        if (filter.equals("old")){
            posts = postService.getAllPosts().stream().sorted(Comparator.comparing(Post::getId)).collect(Collectors.toList());
        }
        model.addAttribute("posts", posts);
        model.addAttribute("filter", filter);
        return "index";
    }


    /*SHOW ALL POSTS*/
    @GetMapping("/filterAuthorProfile")
    public String showAllPostInAuthorProfile(@RequestParam(value = "filter", required = false, defaultValue = "new") String filter, Model model) {
        List<Post> posts =  postService.getAllPosts().stream().sorted(Comparator.comparing(Post::getId).reversed()).collect(Collectors.toList());

        if (filter.equals("old")){
            posts = postService.getAllPosts().stream().sorted(Comparator.comparing(Post::getId)).collect(Collectors.toList());
        }
        model.addAttribute("posts", posts);
        model.addAttribute("filter", filter);
        return "userprofile";
    }



    /*SHOW ONE POST*/
    @GetMapping("/showPost/{id}")
    public String showOnePost(Model model, @PathVariable(value="id") Long id) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);

        List<Comment> comments = commentService.getAllCommentsByPostId(id);
        model.addAttribute("comments", comments);
        return "blogpage";
    }


    /*SEARCH ALL POSTS*/
    @PostMapping("/")
    public String searchPost(@RequestParam(value = "search", required = false, defaultValue = "") String search, Model model) {
        List<Post> posts = postService.getAllPosts().stream()
                .filter(post -> post.getTitle().toLowerCase().contains(search.toLowerCase()) || post.getTag().toLowerCase().contains(search.toLowerCase()) || post.getAuthor().getFirstName().toLowerCase().contains(search.toLowerCase()) || post.getAuthor().getLastName().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());
        model.addAttribute("posts", posts);
        return "index";
    }




    /*CREATE POST*/
    @GetMapping("/newPost")
    public String showPostForm(Model model) {
        Post newpost = new Post();
        model.addAttribute("newpost", newpost);
        return "createpost";
    }
    @PostMapping("/newPost")
    public String createPost(@ModelAttribute Post newpost, Model model) {
        try {
            postService.savePost(newpost);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("newpost", new Post());
            return "createpost";
        }
    }



    /*EDIT POST*/
    @GetMapping("/editPost/{id}")
    public String showPostEditForm(@PathVariable Long id, Model model) {
        Post updatePost = postService.getPostById(id);
        model.addAttribute("updatePost", updatePost);
        model.addAttribute("postid", id);
        return "editpost";
    }
    @PostMapping("/editPost")
    public String updatePost(
            @RequestParam("postid") Long postID,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file,
            Model model) {
        try {
            Post updatedPost = postService.getPostById(postID);
            postService.updatePost(updatedPost, title, description, tag, file);
            return "redirect:/showPost/"+postID;
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return showPostEditForm(postID, model);
        }
    }




    /*DELETE POST*/
    @GetMapping("/deletePost/{id}")
    public String deleteUser(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }

}

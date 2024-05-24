package com.example.springteamwork.controller;

import com.example.springteamwork.model.Like;
import com.example.springteamwork.service.LikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LikeController {

    @Autowired
    private LikeServiceImpl likeService;

    @GetMapping("/likes")
    public String viewLikesPage(Model model) {
        List<Like> likes = likeService.getAllLikes();
        model.addAttribute("likes", likes);
        return "likes";
    }

    @GetMapping("/likes/{id}")
    public String viewLikeById(@PathVariable Long id, Model model) {
        Like like = likeService.getLikeById(id);
        model.addAttribute("like", like);
        return "like_detail";
    }

    @GetMapping("/likes/new")
    public String showNewLikeForm(Model model) {
        Like like = new Like();
        model.addAttribute("like", like);
        return "new_like";
    }

    @PostMapping("/likes")
    public String saveLike(@ModelAttribute("like") Like like) {
        likeService.saveLike(like);
        return "redirect:/likes";
    }

    @GetMapping("/likes/edit/{id}")
    public String showEditLikeForm(@PathVariable Long id, Model model) {
        Like like = likeService.getLikeById(id);
        model.addAttribute("like", like);
        return "edit_like";
    }

    @PostMapping("/likes/{id}")
    public String updateLike(@PathVariable Long id, @ModelAttribute("like") Like likeDetails) {
        Like like = likeService.getLikeById(id);
        like.setUser(likeDetails.getUser());
        like.setPost(likeDetails.getPost());
        likeService.saveLike(like);
        return "redirect:/likes";
    }

    @GetMapping("/likes/delete/{id}")
    public String deleteLike(@PathVariable Long id) {
        likeService.deleteLikeById(id);
        return "redirect:/likes";
    }
}

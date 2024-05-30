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


    @GetMapping("/likes/new")
    public String showNewLikeForm(Model model) {
        Like like = new Like();
        model.addAttribute("like", like);
        return "new_like";
    }








}

package com.example.springteamwork.controller;

import com.example.springteamwork.model.Like;
import com.example.springteamwork.service.LikeService;
import com.example.springteamwork.service.LikeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeServiceImpl likeService;


    @PostMapping("/add")
    public String likePost(@RequestParam Long userId, @RequestParam Long postId, HttpServletRequest request) {
        likeService.likePost(userId, postId);
        return "redirect:" + request.getHeader("referer");

    }

}

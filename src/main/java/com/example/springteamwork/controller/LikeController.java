package com.example.springteamwork.controller;

import com.example.springteamwork.service.LikeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeServiceImpl likeService;

    @PostMapping("/add")
    public String likePost(@RequestParam(defaultValue = "0") Long userId, @RequestParam(defaultValue = "0") Long postId, HttpServletRequest request, Model model) {

        if (userId>0 || postId>0) {
            try{
                likeService.likePost(userId, postId);
            } catch (Exception e){
                String error = "You need to have an account to leave likes";
                model.addAttribute("error", error);
                return "login";
            }
        }

        return "redirect:" + request.getHeader("referer");
    }

}
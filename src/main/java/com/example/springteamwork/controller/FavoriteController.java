package com.example.springteamwork.controller;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.service.FavoriteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @GetMapping("/add")
    public String addFavorites(@RequestParam Long userId, @RequestParam Long postId, HttpServletRequest request) {
        favoriteService.favoritePost(userId, postId);
        return "redirect:" + request.getHeader("referer");
    }

}

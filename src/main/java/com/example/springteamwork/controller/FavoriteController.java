package com.example.springteamwork.controller;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.service.FavoriteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
<<<<<<< HEAD
import jakarta.servlet.http.HttpServletResponse;
=======
>>>>>>> 8bb92d02996e206e6111eee2c831f5342b775bb2
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

<<<<<<< HEAD
    @PostMapping("/add")
    public String addFavorites(
                                @RequestParam Long authorId,
                                @RequestParam Long userId,
                                HttpServletRequest request) {
        favoriteService.addAuthorToFavorite(authorId, userId);
        return "redirect:" + request.getHeader("referer");
    }


=======
    @GetMapping("/add")
    public String addFavorites(@RequestParam Long userId, @RequestParam Long postId, HttpServletRequest request) {
        favoriteService.favoritePost(userId, postId);
        return "redirect:" + request.getHeader("referer");
    }

>>>>>>> 8bb92d02996e206e6111eee2c831f5342b775bb2
}

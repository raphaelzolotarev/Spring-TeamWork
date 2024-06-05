package com.example.springteamwork.controller;

import com.example.springteamwork.service.FavoriteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @PostMapping("/add")
    public String addFavorites(
                                @RequestParam Long authorId,
                                @RequestParam Long userId,
                                HttpServletRequest request) {
        favoriteService.addAuthorToFavorite(authorId, userId);
        return "redirect:" + request.getHeader("referer");
    }

}

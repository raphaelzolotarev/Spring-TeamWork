package com.example.springteamwork.controller;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.service.FavoriteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @GetMapping("/favorites")
    public String viewFavoritesPage(Model model) {
        List<Favorite> favorites = favoriteService.getAllFavorites();
        model.addAttribute("favorites", favorites);
        return "favorites";
    }



    @GetMapping("/favorites/new")
    public String showNewFavoriteForm(Model model) {
        Favorite favorite = new Favorite();
        model.addAttribute("favorite", favorite);
        return "new_favorite";
    }

    @PostMapping("/favorites")
    public String saveFavorite(@ModelAttribute("favorite") Favorite favorite) {
        favoriteService.saveFavorite(favorite);
        return "redirect:/favorites";
    }



}

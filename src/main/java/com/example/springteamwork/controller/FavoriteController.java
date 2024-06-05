package com.example.springteamwork.controller;

import com.example.springteamwork.service.FavoriteServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteServiceImpl favoriteService;

    @PostMapping("/add")
    public String addFavorites(
                                @RequestParam(defaultValue = "0") Long authorId,
                                @RequestParam(defaultValue = "0") Long userId,
                                HttpServletRequest request,
                                Model model) {

        if (authorId>0 || userId>0) {
            try{
                favoriteService.addAuthorToFavorite(authorId, userId);
            } catch (Exception e){
                String error = "You need to have an account to add an author into your favorites";
                model.addAttribute("error", error);
                return "login";
            }
        }

        return "redirect:" + request.getHeader("referer");
    }

}

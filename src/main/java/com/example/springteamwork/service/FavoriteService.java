package com.example.springteamwork.service;

import com.example.springteamwork.model.Favorite;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getAllFavorites();
    void addAuthorToFavorite (Long authorId, Long userId);
    void removeAuthorFromFavorite (Favorite favorite);
}


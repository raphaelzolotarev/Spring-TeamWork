package com.example.springteamwork.service;

import com.example.springteamwork.model.Favorite;

import java.util.List;

public interface FavoriteService {
    List<Favorite> getAllFavorites();
<<<<<<< HEAD
    void addAuthorToFavorite (Long authorId, Long userId);
    void removeAuthorFromFavorite (Favorite favorite);
=======
    void favoritePost (Long userId, Long postId);
>>>>>>> 8bb92d02996e206e6111eee2c831f5342b775bb2
}


package com.example.springteamwork.service;

import com.example.springteamwork.model.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAllLikes();
    void saveLike(Like like);
    Like getLikeById(Long id);
    void deleteLikeById(Long id);
}


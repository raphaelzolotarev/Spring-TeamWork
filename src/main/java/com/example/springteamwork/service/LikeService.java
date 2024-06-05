package com.example.springteamwork.service;

import com.example.springteamwork.model.Like;
import java.util.List;

public interface LikeService {
    List<Like> getAllLikes();
    void likePost(Long userId, Long postId);
    void unlikePost(Like like);
}



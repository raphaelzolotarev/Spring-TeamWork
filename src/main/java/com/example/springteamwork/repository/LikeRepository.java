package com.example.springteamwork.repository;

import com.example.springteamwork.model.Like;
import com.example.springteamwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    void deleteByUserIdAndPostId(Long userId, Long postId);
    Like getLikeByUserId(Long userId);
}


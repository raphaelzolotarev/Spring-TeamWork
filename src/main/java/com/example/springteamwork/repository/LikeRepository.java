package com.example.springteamwork.repository;

import com.example.springteamwork.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like getLikeByUserIdAndPostId(Long userId, Long postId);
}


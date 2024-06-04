package com.example.springteamwork.repository;

import com.example.springteamwork.model.Like;
import com.example.springteamwork.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Like getLikeByUserIdAndPostId(Long userId, Long postId);
}


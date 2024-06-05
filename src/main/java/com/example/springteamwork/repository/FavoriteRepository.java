package com.example.springteamwork.repository;

import com.example.springteamwork.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
<<<<<<< HEAD
    Favorite getFavoriteByAuthorIdAndUserId(Long authorId, Long userId);
=======

    Favorite findByUserIdAndPostId(Long userId, Long postId);
>>>>>>> 8bb92d02996e206e6111eee2c831f5342b775bb2
}


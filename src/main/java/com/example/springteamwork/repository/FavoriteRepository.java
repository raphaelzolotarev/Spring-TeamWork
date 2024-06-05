package com.example.springteamwork.repository;

import com.example.springteamwork.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite getFavoriteByAuthorIdAndUserId(Long authorId, Long userId);
}


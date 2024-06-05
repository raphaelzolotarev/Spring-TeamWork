package com.example.springteamwork.repository;

import com.example.springteamwork.model.Favorite;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Favorite getFavoriteByAuthorIdAndUserId(Long authorId, Long userId);
    List<Favorite> getAllByAuthorId(Long authorId);
    @Transactional
    void deleteAllByAuthorId(Long authorId);

}


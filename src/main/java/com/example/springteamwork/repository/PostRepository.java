package com.example.springteamwork.repository;
import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.model.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getAllByAuthorId(Long authorId);
    @Transactional
    void deleteAllByAuthorId(Long authorId);
}

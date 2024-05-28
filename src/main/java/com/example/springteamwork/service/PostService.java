package com.example.springteamwork.service;
import com.example.springteamwork.model.Post;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    void savePost(Post post);
    void updatePost(Post post, String title, String description);
    void deletePost(Long id);
}

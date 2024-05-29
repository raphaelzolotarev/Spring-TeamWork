package com.example.springteamwork.service;
import com.example.springteamwork.model.Post;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    void savePost(Post post) throws IOException;
    void updatePost(Post post, String title, String description, String tag, MultipartFile file);
    void deletePost(Long id);
}
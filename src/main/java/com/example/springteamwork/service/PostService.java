package com.example.springteamwork.service;

import com.example.springteamwork.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    void savePost(Post post);
    Post getPostId(Long id);
    void deletePostById(Long id);
}

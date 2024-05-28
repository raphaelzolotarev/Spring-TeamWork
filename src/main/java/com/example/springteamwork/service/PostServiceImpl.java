package com.example.springteamwork.service;

import com.example.springteamwork.model.Post;
import com.example.springteamwork.model.User;
import com.example.springteamwork.repository.PostRepository;
import com.example.springteamwork.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    /*GET ALL POSTS*/
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /*GET POST BY ID*/
    @Override
    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (!optionalPost.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        return optionalPost.get();
    }

    /*SAVE POST*/
    @Override
    public void savePost(Post post) {
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        else if (post.getDescription() == null || post.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        postRepository.save(post);
    }

    /*UPDATE POST*/
    @Override
    public void updatePost(Post updatedpost, String title, String description) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        else if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        updatedpost.setTitle(title);
        updatedpost.setDescription(description);
        postRepository.save(updatedpost);
    }

    /*DELETE POST*/
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


}
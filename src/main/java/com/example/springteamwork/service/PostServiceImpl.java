package com.example.springteamwork.service;

import com.example.springteamwork.model.Post;
import com.example.springteamwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostId(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Post with id " + id + " doesn't exist"));
    }

    @Override
    public void deletePostById(Long id) {
        boolean exists = postRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Post with id " + id + " does not exist");
        }
        postRepository.deleteById(id);
    }
}
//

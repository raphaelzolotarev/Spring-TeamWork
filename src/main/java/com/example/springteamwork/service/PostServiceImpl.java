package com.example.springteamwork.service;

import com.example.springteamwork.model.Post;
import com.example.springteamwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
        } else if (post.getDescription() == null || post.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        } else if (post.getTag() == null || post.getTag().isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be empty");
        } else if (post.getFile() == null || post.getFile().isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        try {
            if (post.getFile() != null && !post.getFile().isEmpty()) {
                post.setImage(post.getFile().getBytes());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Image cannot be uploaded");
        }

        postRepository.save(post);
    }

    /*UPDATE POST*/
    @Override
    public void updatePost(Post updatedpost, String title, String description, String tag, MultipartFile file) {
        updatedpost.setFile(file);
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        } else if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        } else if (tag == null || tag.isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be empty");
        }
        try {
            if (updatedpost.getFile() != null && !updatedpost.getFile().isEmpty()) {
                updatedpost.setImage(updatedpost.getFile().getBytes());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Image cannot be uploaded");
        }
        updatedpost.setTitle(title);
        updatedpost.setDescription(description);
        updatedpost.setTag(tag);
        postRepository.save(updatedpost);
    }

    /*DELETE POST*/
    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

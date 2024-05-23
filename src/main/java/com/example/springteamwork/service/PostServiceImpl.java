package com.example.springteamwork.service;

import com.example.springteamwork.model.Post;
import com.example.springteamwork.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{
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
        /*Optional<Post> postOptional = postRepository.findById(post.getId());
        if(postOptional.isPresent()){
            postOptional.get().setTitle(post.getTitle());
            postOptional.get().setDescription(post.getDescription());
            postOptional.get().setContent(post.getContent());
            postRepository.save(postOptional.get());
        } else {
            postRepository.save(post);
        }*/
        postRepository.save(post);
    }

    @Override
    public Post getPostId(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if(!postOptional.isPresent()){
            throw new IllegalStateException("post doesn't exist");
        }
        return postOptional.get();
    }

    @Override
    public void deletePostById(Long id) {
        boolean exists = postRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("post id "+id+" does not exists");
        }
        postRepository.deleteById(id);
    }
    
    

}

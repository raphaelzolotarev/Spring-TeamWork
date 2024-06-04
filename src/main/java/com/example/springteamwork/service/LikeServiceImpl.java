package com.example.springteamwork.service;

import com.example.springteamwork.model.Like;
import com.example.springteamwork.model.Post;
import com.example.springteamwork.model.User;
import com.example.springteamwork.repository.LikeRepository;
import com.example.springteamwork.repository.PostRepository;
import com.example.springteamwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public void likePost(Long userId, Long postId) {
        Like getLikeByUserIdAndPostId = likeRepository.getLikeByUserIdAndPostId(userId, postId);
        if (getLikeByUserIdAndPostId==null){
            Like like = new Like(userRepository.getReferenceById(userId), postRepository.getReferenceById(postId));
            likeRepository.save(like);
        } else{
            unlikePost(getLikeByUserIdAndPostId);
        }
    }


    @Override
    public void unlikePost(Like like) {
        likeRepository.delete(like);
    }
}

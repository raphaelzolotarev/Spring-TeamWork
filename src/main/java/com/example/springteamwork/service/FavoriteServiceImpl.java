package com.example.springteamwork.service;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.repository.FavoriteRepository;
import com.example.springteamwork.repository.PostRepository;
import com.example.springteamwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public void favoritePost(Long userId, Long postId) {
        Favorite getFavoriteByUserIdPostId = favoriteRepository.findByUserIdAndPostId(userId, postId);
        if (getFavoriteByUserIdPostId==null){
            Favorite favorite = new Favorite(userRepository.getReferenceById(userId).getId(), postRepository.getReferenceById(postId).getId());
            favoriteRepository.save(favorite);
        }
    }
}


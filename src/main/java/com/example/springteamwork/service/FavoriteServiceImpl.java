package com.example.springteamwork.service;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.model.Like;
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

<<<<<<< HEAD
=======
    @Autowired
    private PostRepository postRepository;

>>>>>>> 8bb92d02996e206e6111eee2c831f5342b775bb2
    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
<<<<<<< HEAD
    public void addAuthorToFavorite(Long authorId, Long userId) {
        Favorite favoriteByAuthorIdAndUserId = favoriteRepository.getFavoriteByAuthorIdAndUserId(authorId, userId);
        if (favoriteByAuthorIdAndUserId==null){
            Favorite favorite = new Favorite(userRepository.getReferenceById(authorId), userRepository.getReferenceById(userId));
            favoriteRepository.save(favorite);
        } else{
            removeAuthorFromFavorite(favoriteByAuthorIdAndUserId);
        }
    }


    @Override
    public void removeAuthorFromFavorite(Favorite favorite) {
        favoriteRepository.delete(favorite);
=======
    public void favoritePost(Long userId, Long postId) {
        Favorite getFavoriteByUserIdPostId = favoriteRepository.findByUserIdAndPostId(userId, postId);
        if (getFavoriteByUserIdPostId==null){
            Favorite favorite = new Favorite(userRepository.getReferenceById(userId).getId(), postRepository.getReferenceById(postId).getId());
            favoriteRepository.save(favorite);
        }
>>>>>>> 8bb92d02996e206e6111eee2c831f5342b775bb2
    }


}




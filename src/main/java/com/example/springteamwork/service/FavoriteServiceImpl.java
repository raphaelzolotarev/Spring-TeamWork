package com.example.springteamwork.service;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.repository.FavoriteRepository;
import com.example.springteamwork.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
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
    }

}




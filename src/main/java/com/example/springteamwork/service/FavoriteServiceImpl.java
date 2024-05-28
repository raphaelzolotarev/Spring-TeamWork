package com.example.springteamwork.service;

import com.example.springteamwork.model.Favorite;
import com.example.springteamwork.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public void saveFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    @Override
    public Favorite getFavoriteById(Long id) {
        Optional<Favorite> optionalFavorite = favoriteRepository.findById(id);
        if (!optionalFavorite.isPresent()) {
            throw new IllegalStateException("Favorite with id " + id + " doesn't exist");
        }
        return optionalFavorite.get();
    }

    @Override
    public void deleteFavoriteById(Long id) {
        boolean exists = favoriteRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Favorite with id " + id + " does not exist");
        }
        favoriteRepository.deleteById(id);
    }
}


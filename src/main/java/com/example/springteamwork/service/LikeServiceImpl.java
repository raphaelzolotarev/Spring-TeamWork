package com.example.springteamwork.service;

import com.example.springteamwork.model.Like;
import com.example.springteamwork.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public void saveLike(Like like) {
        likeRepository.save(like);
    }

    @Override
    public Like getLikeById(Long id) {
        Optional<Like> optionalLike = likeRepository.findById(id);
        if (!optionalLike.isPresent()) {
            throw new IllegalStateException("Like with id " + id + " doesn't exist");
        }
        return optionalLike.get();
    }

    @Override
    public void deleteLikeById(Long id) {
        boolean exists = likeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Like with id " + id + " does not exist");
        }
        likeRepository.deleteById(id);
    }
}


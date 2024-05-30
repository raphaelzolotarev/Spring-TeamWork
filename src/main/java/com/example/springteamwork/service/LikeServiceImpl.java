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
    public void likePost(Long id) {
        Like like = likeRepository.findById(id).orElseThrow(() -> new RuntimeException("Like not found"));

    }

    @Override
    public void unlikePost(Long id) {

    }









}


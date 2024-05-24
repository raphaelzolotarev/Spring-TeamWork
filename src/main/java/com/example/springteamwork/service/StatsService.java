package com.example.springteamwork.service;

import com.example.springteamwork.model.Stats;

import java.util.List;

public interface StatsService {
    List<Stats> getAllStats();
    void saveStats(Stats stats);
    Stats getStatsById(Long id);
    void deleteStatsById(Long id);
}


package com.example.springteamwork.service;

import com.example.springteamwork.model.Stats;
import com.example.springteamwork.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<Stats> getAllStats() {
        return statsRepository.findAll();
    }

    @Override
    public void saveStats(Stats stats) {
        statsRepository.save(stats);
    }

    @Override
    public Stats getStatsById(Long id) {
        Optional<Stats> optionalStats = statsRepository.findById(id);
        if (!optionalStats.isPresent()) {
            throw new IllegalStateException("Stats with id " + id + " doesn't exist");
        }
        return optionalStats.get();
    }

    @Override
    public void deleteStatsById(Long id) {
        boolean exists = statsRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Stats with id " + id + " does not exist");
        }
        statsRepository.deleteById(id);
    }
}


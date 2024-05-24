package com.example.springteamwork.controller;

import com.example.springteamwork.model.Stats;
import com.example.springteamwork.service.StatsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StatsController {

    @Autowired
    private StatsServiceImpl statsService;

    @GetMapping("/stats")
    public String viewStatsPage(Model model) {
        List<Stats> stats = statsService.getAllStats();
        model.addAttribute("stats", stats);
        return "stats";
    }

    @GetMapping("/stats/{id}")
    public String viewStatsById(@PathVariable Long id, Model model) {
        Stats stats = statsService.getStatsById(id);
        model.addAttribute("stats", stats);
        return "stats_detail";
    }

    @GetMapping("/stats/new")
    public String showNewStatsForm(Model model) {
        Stats stats = new Stats();
        model.addAttribute("stats", stats);
        return "new_stats";
    }

    @PostMapping("/stats")
    public String saveStats(@ModelAttribute("stats") Stats stats) {
        statsService.saveStats(stats);
        return "redirect:/stats";
    }

    @GetMapping("/stats/edit/{id}")
    public String showEditStatsForm(@PathVariable Long id, Model model) {
        Stats stats = statsService.getStatsById(id);
        model.addAttribute("stats", stats);
        return "edit_stats";
    }

    @PostMapping("/stats/{id}")
    public String updateStats(@PathVariable Long id, @ModelAttribute("stats") Stats statsDetails) {
        Stats stats = statsService.getStatsById(id);
        stats.setNumberOfVisitors(statsDetails.getNumberOfVisitors());
        statsService.saveStats(stats);
        return "redirect:/stats";
    }

    @GetMapping("/stats/delete/{id}")
    public String deleteStats(@PathVariable Long id) {
        statsService.deleteStatsById(id);
        return "redirect:/stats";
    }
}

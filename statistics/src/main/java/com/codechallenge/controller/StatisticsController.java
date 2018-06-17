package com.codechallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.service.StatisticService;
import com.codechallenge.dto.Statistic;

/*
 * Controller for StatisticService.
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticService statisticService;
    
    @GetMapping
    public Statistic getStatistics(){
        return statisticService.getStatistics();
    }
}

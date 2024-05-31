package com.example.springteamwork.service;
import com.example.springteamwork.model.NumberOfVisits;
import com.example.springteamwork.repository.NumberOfVisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberOfVisitsService {

    private final NumberOfVisitsRepository numberOfVisitsRepository;

    @Autowired
    public NumberOfVisitsService(NumberOfVisitsRepository numberOfVisitsRepository) {
        this.numberOfVisitsRepository = numberOfVisitsRepository;
    }

    public NumberOfVisits getNumberOfVisits(){
        return numberOfVisitsRepository.findById((byte)1).get();
    }
}

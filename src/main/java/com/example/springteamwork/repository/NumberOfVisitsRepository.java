package com.example.springteamwork.repository;

import com.example.springteamwork.model.NumberOfVisits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberOfVisitsRepository extends JpaRepository<NumberOfVisits, Byte> {
}

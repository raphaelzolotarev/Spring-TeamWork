package com.example.springteamwork.repository;

import com.example.springteamwork.model.MailJet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailJetRepository extends JpaRepository<MailJet, Byte> {
}

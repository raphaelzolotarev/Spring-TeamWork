package com.example.springteamwork.repository;

import com.example.springteamwork.model.MailGun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailGunRepository extends JpaRepository<MailGun, Byte> {
}

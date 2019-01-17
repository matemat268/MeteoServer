package com.example.meteo.repository;

import com.example.meteo.entity.Right;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RightRepository extends JpaRepository<Right, Long> {
    Right findFirstByNazwa(String nazwa);
}

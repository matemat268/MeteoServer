package com.example.meteo.repository;

import com.example.meteo.entity.CollectedData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectedDataRepository extends JpaRepository<CollectedData, Long> {
}

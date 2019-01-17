package com.example.meteo.repository;

import com.example.meteo.entity.Measurements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurements, Long> {

    List<Measurements> getAllByTyp(String typ);
}

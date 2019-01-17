package com.example.meteo.repository;

import com.example.meteo.entity.Log;
import com.example.meteo.entity.LogIn;
import com.example.meteo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LogInRepository extends JpaRepository<LogIn, Long> {

    @Query (value = "select * from login l where day(l.czas) = :dzien and month(l.czas) = :miesiac and year (l.czas) = :rok", nativeQuery = true)
    List<LogIn> findLoginToday(@Param("dzien") Long dzien, @Param("miesiac") Long miesiac, @Param("rok") Long rok);

    @Query (value = "select * from login l where month(l.czas) = :miesiac and year (l.czas) = :rok", nativeQuery = true)
    List<LogIn> findLoginMonth(@Param("miesiac") Long miesiac, @Param("rok") Long rok);

    @Query(value = "select l.czas from login l where l.id_uzyt = :user order by l.czas desc limit 1", nativeQuery = true)
    Date findMaxDate(@Param("user") Long user);
}

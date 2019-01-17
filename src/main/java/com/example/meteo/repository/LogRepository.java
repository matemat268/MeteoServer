package com.example.meteo.repository;

import com.example.meteo.entity.Log;
import com.example.meteo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {

    @Query (value = "select * from log l where l.typ = 'REG' and day(l.czas) = :dzien and month(l.czas) = :miesiac and year (l.czas) = :rok", nativeQuery = true)
    List<Log> findNewUsersToday(@Param("dzien") Long dzien, @Param("miesiac") Long miesiac, @Param("rok") Long rok);

    @Query (value = "select * from log l where l.typ = 'REG' and month(l.czas) = :miesiac and year (l.czas) = :rok", nativeQuery = true)
    List<Log> findNewUsersMonth(@Param("miesiac") Long miesiac, @Param("rok") Long rok);

    Log findLogByUzytAndAndTyp(User u, String typ);
}

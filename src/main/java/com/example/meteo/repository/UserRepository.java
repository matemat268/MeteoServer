package com.example.meteo.repository;

import com.example.meteo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByEmail(String email);

    @Query(value = "select * from users u order by u.id_uzyt desc limit 1", nativeQuery = true)
    User getNewest();
}

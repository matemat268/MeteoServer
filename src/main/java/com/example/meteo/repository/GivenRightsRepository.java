package com.example.meteo.repository;

import com.example.meteo.entity.GivenRights;
import com.example.meteo.entity.Right;
import com.example.meteo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GivenRightsRepository extends JpaRepository<GivenRights, Long> {

    GivenRights findAllByUzytDocelowyAndPrawo(User u, Right r);
}

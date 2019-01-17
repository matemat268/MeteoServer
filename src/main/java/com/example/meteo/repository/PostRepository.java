package com.example.meteo.repository;

import com.example.meteo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "select max(p.nr_postu) from post p where p.id_watku = :id_watku", nativeQuery = true)
    Integer getMaxNrPostu(@Param("id_watku") Long id_watku);

}

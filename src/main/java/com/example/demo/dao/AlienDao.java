package com.example.demo.dao;

import com.example.demo.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlienDao extends JpaRepository<Alien, Integer> {

    //CrudRepository is implemented internally

    List<Alien> findByTech(String tech);
    List<Alien> findByAidGreaterThan(Integer aid);

    @Query("from Alien where tech=?1 order by aname")
    List<Alien> findByTechSorted(String tech);
}

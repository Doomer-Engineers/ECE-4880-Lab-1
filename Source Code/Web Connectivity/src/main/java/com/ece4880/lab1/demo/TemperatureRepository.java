package com.ece4880.lab1.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemperatureRepository extends JpaRepository<com.ece4880.lab1.demo.Temperature,Long> {
    @Query("SELECT t FROM Temperature t WHERE t.id = ?1")
    com.ece4880.lab1.demo.Temperature findByID(Long i);

    @Query("SELECT t FROM Temperature t WHERE t.id=(SELECT max(t.id) FROM Temperature)")
    com.ece4880.lab1.demo.Temperature getLastTemp();
}

package com.ece4880.lab1.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ButtonRepository extends JpaRepository<com.ece4880.lab1.demo.Button,Long> {
    @Query("SELECT b FROM Button b WHERE b.id = ?1")
    com.ece4880.lab1.demo.Button findByID();
}

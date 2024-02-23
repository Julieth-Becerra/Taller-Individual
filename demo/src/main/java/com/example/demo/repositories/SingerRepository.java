package com.example.demo.repositories;

import com.example.demo.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository <Singer, Integer> {
}

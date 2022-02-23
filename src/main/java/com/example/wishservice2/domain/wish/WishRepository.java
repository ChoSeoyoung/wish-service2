package com.example.wishservice2.domain.wish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish, Long> {
    @Query("SELECT w FROM Wish w ORDER BY w.id DESC")
    List<Wish> findAllDesc();
}

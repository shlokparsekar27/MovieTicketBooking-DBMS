package com.movieticketbooking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketbooking.demo.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

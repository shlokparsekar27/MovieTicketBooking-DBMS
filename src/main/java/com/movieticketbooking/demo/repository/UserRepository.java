package com.movieticketbooking.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movieticketbooking.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

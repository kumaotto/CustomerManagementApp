package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}

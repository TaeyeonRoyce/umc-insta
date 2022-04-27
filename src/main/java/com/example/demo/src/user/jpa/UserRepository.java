package com.example.demo.src.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.src.user.jpa.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

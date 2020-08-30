package com.example.databaseproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.databaseproject.entity.Customer;

public interface UserDao extends JpaRepository<Customer, Long> {

}

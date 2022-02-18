package com.vobi.bank.repository;

import com.vobi.bank.domain.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {

}

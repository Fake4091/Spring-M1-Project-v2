package com.example.Spring.Project.v2.Password;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordRepository extends JpaRepository<Password, Long> {

  List<Password> findAllByNameContains(String name);
}

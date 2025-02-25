package com.example.Spring.Project.v2.Password;

import jakarta.persistence.*;

@Entity
@Table
public class Password {
  @Id
  @SequenceGenerator(
      name = "student_sequence",
      sequenceName = "student_sequence",
      allocationSize = 1
  )
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "student_sequence"
  )
  private Long id;
  private String name;
  private String username;
  private String password;

  public Password() {
  }

  public Password(Long id, String name, String username, String password) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public Password(String name, String username, String password) {
    this.name = name;
    this.username = username;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

package com.example.Spring.Project.v2.Password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/passwords")
public class PasswordController {
  private final PasswordService passwordService;

  @Autowired
  public PasswordController(PasswordService passwordService) {this.passwordService = passwordService;}

  @GetMapping
  public List<Password> getPasswords() {
    return passwordService.getPasswords();
  }

  @PostMapping
  public Password createPassword(@RequestBody Password password) {
    return passwordService.createPassword(password);
  }

  @GetMapping(path = "{passwdId}")
  public Password getPassword(@PathVariable("passwdId") Long id) {
    return passwordService.getPassword(id);
  }

  @GetMapping(path = "by_name")
  public List<Password> getPasswordsByName(@RequestBody String name) {
    return passwordService.getPasswordByName(name);
  }

  @PutMapping(path = "{passwdId}")
  public Password updatePassword(@PathVariable("passwdId") Long id, @RequestParam(required = false) String newName, @RequestParam(required = false) String newUsername, @RequestParam(required = false) String newPassword) {
    return passwordService.updatePassword(id, newName, newUsername, newPassword);
  }

  @DeleteMapping(path = "{passwdId}")
  public void deletePassword(@PathVariable("passwdId") Long id) {
    passwordService.deletePassword(id);
  }

}

package com.example.Spring.Project.v2.Password;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordService {
  private final PasswordRepository passwordRepository;

  @Autowired
  public PasswordService(PasswordRepository passwordRepository) {this.passwordRepository = passwordRepository;}

  public List<Password> getPasswords() {
    return passwordRepository.findAll();
  }

  public Password createPassword(Password password) {
    if (password.getPassword().length() < 8) {
      throw new IllegalStateException("Please use a longer password");
    }
    if (password.getPassword().indexOf('!') < 0 &&
        password.getPassword().indexOf('_') < 0 &&
        password.getPassword().indexOf('$') < 0 &&
        password.getPassword().indexOf('*') < 0 &&
        password.getPassword().indexOf('+') < 0 &&
        password.getPassword().indexOf('-') < 0
    ) {
      throw new IllegalStateException("Please include a special character (!_$*+-)");
    }
    return passwordRepository.save(password);
  }

  public Password getPassword(Long id) {
    Optional<Password> optionalPassword = passwordRepository.findById(id);
    if (optionalPassword.isEmpty()) {
      throw new IllegalStateException("Password not found");
    }
    return optionalPassword.get();
  }


  public Password updatePassword(Long id, String newName, String newUsername, String newPassword) {
    if (newName.isEmpty() && newUsername.isEmpty() && newPassword.isEmpty()) {
      throw new IllegalStateException("No fields edited");
    }
    Optional<Password> optionalPassword = passwordRepository.findById(id);
    if (optionalPassword.isEmpty()) {
      throw new IllegalStateException("Password with id " + id + " not found");
    }
    Password password = optionalPassword.get();
    if (!newName.isEmpty()) {
      password.setName(newName);
    }
    if (!newUsername.isEmpty()) {
      password.setUsername(newUsername);
    }
    if (!newPassword.isEmpty()) {
      if (newPassword.length() < 8) {
        throw new IllegalStateException("Please use a longer password");
      }
      if (newPassword.indexOf('!') < 0 &&
          newPassword.indexOf('_') < 0 &&
          newPassword.indexOf('$') < 0 &&
          newPassword.indexOf('*') < 0 &&
          newPassword.indexOf('+') < 0 &&
          newPassword.indexOf('-') < 0
      ) {
        throw new IllegalStateException("Please include a special character (!_$*+-)");
      }
      password.setPassword(newPassword);
    }
    return passwordRepository.save(password);
  }

  public void deletePassword(Long id) {
    Optional<Password> optionalPassword = passwordRepository.findById(id);
    if (optionalPassword.isEmpty()) {
      throw new IllegalStateException("Password not found");
    }
    passwordRepository.delete(optionalPassword.get());
  }

  public List<Password> getPasswordByName(String name) {
    List<Password> passwords = passwordRepository.findAll();
    List<Password> filteredPasswords = new ArrayList<>();
    for (Password password : passwords) {
      if (password.getName().toLowerCase().contains(name.split("\"")[1].toLowerCase())) {
        filteredPasswords.add(password);
      }
    }
    System.out.println(filteredPasswords);

    if (filteredPasswords.isEmpty()) {
      throw new IllegalStateException("Password not found");
    }
    return filteredPasswords;
  }
}

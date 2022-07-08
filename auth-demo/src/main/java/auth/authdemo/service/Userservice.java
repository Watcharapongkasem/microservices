package auth.authdemo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import auth.authdemo.vm.Userviewmodel;

@Service
public interface Userservice {

    Boolean registerUser(Userviewmodel model);

    Optional<String> generateSalt (final int length);

    Optional<String> hashPassword (String password, String salt);

    Boolean verifyPassword (Userviewmodel model);

    Userviewmodel getUserId(Userviewmodel model ,String token);

}

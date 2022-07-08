package auth.authdemo.serviceImp;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import auth.authdemo.entity.User;
import auth.authdemo.repository.UserJpa;
import auth.authdemo.service.Userservice;
import auth.authdemo.vm.Userviewmodel;

@Service
public class UserserviceImp implements Userservice {

    @Autowired
    private UserJpa userJpa;

    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    @Override
    public Boolean registerUser(Userviewmodel model) {

        Boolean checkUser = userJpa.existsByUserName(model.getUserName());

        if (checkUser) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {

            String salt = generateSalt(50).get();
            String key = hashPassword(model.getPassword(), salt).get();
            model.setUserId(UUID.randomUUID().toString());
            var user = model.toEntity();
            user.setPasswordSalt(salt);
            user.setPasswordHash(key);
            userJpa.save(user);

        }
        return true;
    }

    @Override
    public Optional<String> generateSalt(final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
    }

    @Override
    public Optional<String> hashPassword(String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {

            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {

            spec.clearPassword();

        }
    }

    @Override
    public Boolean verifyPassword(Userviewmodel model) {

        Boolean checkUser = userJpa.existsByUserName(model.getUserName());
        User user;

        if (!checkUser) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else {
            user = userJpa.findByUserName(model.getUserName());
        }
        Optional<String> optEncrypted = hashPassword(model.getPassword(), user.getPasswordSalt());
        if (!optEncrypted.isPresent())
            return false;
        return optEncrypted.get().equals(user.getPasswordHash());
    }

    @Override
    public Userviewmodel getUserId(Userviewmodel model, String token){
        User userEn = userJpa.findByUserName(model.getUserName());
        return new Userviewmodel(userEn,token);
    }
}

package auth.authdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import auth.authdemo.entity.User;

public interface UserJpa extends JpaRepository<User, String> {

    boolean existsByUserName(String userName);

    User findByUserName(String userName);
}

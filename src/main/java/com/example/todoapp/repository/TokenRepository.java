package com.example.todoapp.repository;

import com.example.todoapp.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TokenRepository extends JpaRepository<UserToken,Long> {
    Optional<UserToken> findByToken(String token);
    void deleteByToken(String token);
    List<UserToken> findUserTokensByTokenExpiredTimeAfter(LocalDateTime time);
    void deleteUserTokensByTokenExpiredTimeBefore(LocalDateTime time);
}

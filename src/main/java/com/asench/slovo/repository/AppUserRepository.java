package com.asench.slovo.repository;

import com.asench.slovo.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String userName);
    boolean existsByUsername(String username);
}

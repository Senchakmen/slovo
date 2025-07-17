package com.asench.slovo.repository;

import com.asench.slovo.domain.UserLanguagePair;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserLanguagePairRepository extends CrudRepository<UserLanguagePair, Long> {
    Optional<List<UserLanguagePair>> findByAppUser_Username(String username);
    Optional<UserLanguagePair> findByIsDefaultTrueAndAppUser_Username(String username);
}

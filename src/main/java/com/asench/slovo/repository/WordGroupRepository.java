package com.asench.slovo.repository;

import com.asench.slovo.domain.WordGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface WordGroupRepository extends CrudRepository<WordGroup, Long> {
    Optional<WordGroup> findByNameAndAppUser_Username(String name, String username);
    boolean existsByNameAndAppUser_Username(String name, String username);
    List<WordGroup> findByAppUser_Username(String username);
}

package com.asench.slovo.repository;

import com.asench.slovo.domain.WordGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WordGroupRepository extends CrudRepository<WordGroup, Long> {
    Optional<WordGroup> findByName(String name);
}

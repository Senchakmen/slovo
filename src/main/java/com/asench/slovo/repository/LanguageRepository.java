package com.asench.slovo.repository;

import com.asench.slovo.domain.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    /** Find by ISO code, case-insensitive. */
    Optional<Language> findByCodeIgnoreCase(String code);

    /** True if a language with the given code already exists. */
    boolean existsByCodeIgnoreCase(String code);
}

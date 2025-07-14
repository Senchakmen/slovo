package com.asench.slovo.repository;

import com.asench.slovo.domain.AppUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.fail;

public class AppUserRepositoryTest extends RepositoryTestBase {

    @Autowired
    AppUserRepository appUserRepository;

    static final String BASE_USERNAME = "test";
    static final String BASE_USER_EMAIL = "test@test.com";


    @Test
    void crudAppUser() {
        String newEmail = "updated@test.com";
        AppUser user = new AppUser(BASE_USERNAME, BASE_USER_EMAIL);
        appUserRepository.save(user);

        Assertions.assertTrue(appUserRepository.existsByUsername(BASE_USERNAME));
        AppUser createdUser = appUserRepository.findByUsername(BASE_USERNAME)
                .orElseThrow(() -> new AssertionError("User is not created"));

        createdUser.setEmail(newEmail);
        appUserRepository.save(createdUser);

        appUserRepository.findByUsername(createdUser.getUsername())
                .ifPresent(updatedUser -> {
                    if (!newEmail.equals(updatedUser.getEmail())) {
                        fail("User is not updated");
                    }
                });

        appUserRepository.delete(createdUser);
    }
}

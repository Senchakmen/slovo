package com.asench.slovo.repository;


import com.asench.slovo.domain.Language;
import com.asench.slovo.domain.AppUser;
import com.asench.slovo.domain.UserLanguagePair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLanguagePairRepositoryTest extends RepositoryTestBase {
    @Autowired
    AppUserRepository userRepo;
    @Autowired
    LanguageRepository langRepo;
    @Autowired
    UserLanguagePairRepository pairRepo;

    private static final String BASE_SOURCE_LANGUAGE_CODE = "en";
    private static final String BASE_TARGET_LANGUAGE_CODE = "uk";
    //private static final String BASE_USERNAME = "test";
    //private static final String BASE_USER_EMAIL = "test@test.com";

    private AppUser baseAppUser;
    private Language languageSource;
    private Language languageTarget;

    @BeforeEach
    void setup() {
        baseAppUser = new AppUser(AppUserRepositoryTest.BASE_USERNAME, AppUserRepositoryTest.BASE_USER_EMAIL);
        userRepo.save(baseAppUser);
        languageSource = langRepo.findByCodeIgnoreCase(BASE_SOURCE_LANGUAGE_CODE).orElseThrow();
        languageTarget = langRepo.findByCodeIgnoreCase(BASE_TARGET_LANGUAGE_CODE).orElseThrow();
    }

    @AfterEach
    void tearDown() {
        userRepo.findByUsername(AppUserRepositoryTest.BASE_USERNAME)
                .ifPresent(appUser -> userRepo.delete(appUser));
    }

    @Test
    void saveFetchAndDeleteDefaultPair() {
        var userLanguagePair = new UserLanguagePair(baseAppUser, languageSource, languageTarget, true);
        pairRepo.save(userLanguagePair);

        UserLanguagePair result = pairRepo.findByIsDefaultTrueAndUser_Username(AppUserRepositoryTest.BASE_USERNAME).orElseThrow();
        var resultID = result.getId();

        Assertions.assertEquals(result.getUser(), baseAppUser);
        Assertions.assertTrue(result.isDefault());

        pairRepo.delete(result);

        Assertions.assertTrue(userRepo.existsByUsername(AppUserRepositoryTest.BASE_USERNAME), "ifDeletePairUserShouldNotBeDeleted");
        Assertions.assertFalse(pairRepo.existsById(resultID));
    }

}

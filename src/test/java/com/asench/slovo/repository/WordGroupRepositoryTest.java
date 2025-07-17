package com.asench.slovo.repository;


import com.asench.slovo.domain.AppUser;
import com.asench.slovo.domain.WordGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public class WordGroupRepositoryTest extends RepositoryTestBase {
    @Autowired
    AppUserRepository userRepo;
    @Autowired
    WordGroupRepository wordGroupRepository;

    private AppUser baseAppUser;

    @BeforeEach
    void setup() {
        if (!userRepo.existsByUsername(AppUserRepositoryTest.BASE_USERNAME)) {
            baseAppUser = new AppUser(AppUserRepositoryTest.BASE_USERNAME, AppUserRepositoryTest.BASE_USER_EMAIL);
            userRepo.save(baseAppUser);
        }
    }

    @Test
    void saveFetchAndDelete_BaseWordGroup_works() {

        // Save
        WordGroup wordGroup = new WordGroup("TestGroup", baseAppUser);
        wordGroupRepository.save(wordGroup);
        Assertions.assertTrue(wordGroupRepository.existsByNameAndAppUser_Username("TestGroup", AppUserRepositoryTest.BASE_USERNAME), "WordGroup is not saved");

        // Update
        WordGroup createdWordGroup = wordGroupRepository.findByNameAndAppUser_Username("TestGroup", AppUserRepositoryTest.BASE_USERNAME).orElseThrow();
        createdWordGroup.setName("Updated");
        wordGroupRepository.save(createdWordGroup);
        List<WordGroup> wordGroupByAppUserUsernameList = wordGroupRepository.findByAppUser_Username(AppUserRepositoryTest.BASE_USERNAME);
        Assertions.assertFalse(wordGroupByAppUserUsernameList.isEmpty());
        WordGroup updatedWordGroup = wordGroupByAppUserUsernameList.getFirst();
        Assertions.assertEquals("Updated", updatedWordGroup.getName());

        // Delete
        Long resultID = updatedWordGroup.getId();
        wordGroupRepository.delete(updatedWordGroup);
        Assertions.assertFalse(wordGroupRepository.existsById(resultID));
    }

    @Test
    void whenDuplicateNameForOneAppUser_ThrowsError() {
        WordGroup wordGroup = new WordGroup("TestGroup", baseAppUser);
        wordGroupRepository.save(wordGroup);

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> wordGroupRepository.save(new WordGroup("TestGroup", baseAppUser)),
                "Duplicate names for one user is not allowed");
    }

}

package com.sergioruy.character.repository;

import com.sergioruy.character.CharacterBaseTest;
import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.GetCharacterRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GetCharacterRepositoryTest extends CharacterBaseTest {

    @Inject
    GetCharacterRepository getCharacterRepository;

    @Test
    @TestTransaction
    @DisplayName("The method find by name should work")
    void findByNameShouldWork() {
        createTestMassOnDatabase();

        Optional<CharacterEntity> result = getCharacterRepository.findByName(characterName);

        assertNotNull(result);
        assertEquals(characterName, result.get().getName(), "The name of this character is Invincible");
    }

    @Test
    @TestTransaction
    @DisplayName("The method exists should return true")
    void existsByNameShouldWork() {
        createTestMassOnDatabase();

        boolean result = getCharacterRepository.existsByName(characterName);

        assertTrue(result);
    }
}

package com.sergioruy.character.repository;

import com.sergioruy.character.CharacterBaseTest;
import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.CreateCharacterRepository;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class CreateCharacterRepositoryTest extends CharacterBaseTest {

    @Inject
    CreateCharacterRepository createCharacterRepository;

    @Test
    @TestTransaction
    @DisplayName("The method persistCharacter should persist a character on database")
    void persistCharacterShouldWork() {
        String characterId = UUID.randomUUID().toString();
        String characterName = "Wolverine";
        String alignment = "Chaotic Good";
        String superGroup = "X-men";

        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setId(characterId);
        characterEntity.setName(characterName);
        characterEntity.setAlignment(alignment);
        characterEntity.setSuperGroup(superGroup);

        createCharacterRepository.persistCharacter(characterEntity);

        CharacterEntity result = createCharacterRepository.findById(characterId);
        assertNotNull(result);
        assertEquals(characterId, result.getId());
        assertEquals(characterName, result.getName());
        assertEquals(alignment, result.getAlignment());
        assertEquals(superGroup, result.getSuperGroup());
    }
}

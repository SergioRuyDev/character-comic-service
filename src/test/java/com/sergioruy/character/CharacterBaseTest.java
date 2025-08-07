package com.sergioruy.character;

import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.CreateCharacterRepository;
import com.sergioruy.character.usecase.adapter.GetCharacterRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.UUID;

public class CharacterBaseTest {

    @Inject
    GetCharacterRepository getCharacterRepository;

    @Inject
    CreateCharacterRepository createCharacterRepository;

    protected CharacterEntity character;

    protected String characterName = "Invincible";
    protected String alignment = "Lawful Good";
    protected String superGroup = "Teen Team";

    @Transactional
    public void createTestMassOnDatabase() {
        populateCharacter();
    }

    private void populateCharacter() {
        character = new CharacterEntity();
        character.setId(UUID.randomUUID().toString());
        character.setName(characterName);
        character.setAlignment(alignment);
        character.setSuperGroup(superGroup);

        createCharacterRepository.persistCharacter(character);
    }
}

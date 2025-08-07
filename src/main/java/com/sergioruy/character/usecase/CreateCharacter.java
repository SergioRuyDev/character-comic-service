package com.sergioruy.character.usecase;

import com.sergioruy.character.dto.CharacterDTO;
import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.CreateCharacterRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

import java.util.Objects;
import java.util.UUID;

@ApplicationScoped
public class CreateCharacter {

    CreateCharacterRepository createCharacterRepository;
    GetCharacter getCharacter;

    CreateCharacter(
            CreateCharacterRepository createCharacterRepository,
            GetCharacter getCharacter
    ) {
        this.createCharacterRepository = createCharacterRepository;
        this.getCharacter = getCharacter;
    }

    @Transactional
    public CharacterDTO createCharacter(CharacterDTO characterDTO) {
        validateCharacter(characterDTO);
        return dtoToPersistence(characterDTO);
    }

    private CharacterDTO dtoToPersistence(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setId(UUID.randomUUID().toString());
        characterEntity.setName(characterDTO.name());
        characterEntity.setAlignment(characterDTO.alignment());
        characterEntity.setSuperGroup(characterDTO.superGroup());
        createCharacterRepository.persistCharacter(characterEntity);
        Log.infof("Character created with id: %s", characterEntity.getId());
        return getCharacter.getCharacter(characterEntity.getId());
    }

    private void validateCharacter(CharacterDTO characterDTO) {
        Objects.requireNonNull(characterDTO);
        if (characterDTO.name().isBlank() || characterDTO.alignment().isBlank()) {
            throw new IllegalArgumentException("Name and alignment cannot be empty");
        }
        if (getCharacter.exists(characterDTO.name())) {
            throw new BadRequestException("Character with name " + characterDTO.name() + " already exists");
        }
    }
}

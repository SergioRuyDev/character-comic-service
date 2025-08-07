package com.sergioruy.character.usecase;

import com.sergioruy.character.dto.CharacterDTO;
import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.GetCharacterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GetCharacter {

    GetCharacterRepository getCharacterRepository;

    public GetCharacter(GetCharacterRepository getCharacterRepository) {
        this.getCharacterRepository = getCharacterRepository;
    }

    public CharacterDTO getCharacter(String id) {
        validateStringRequest(id);
        return persistenceToDto(getCharacterRepository.findById(id));
    }

    public CharacterDTO getCharacterByName(String name) {
        validateStringRequest(name);
        CharacterEntity entity = getCharacterRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Character with name " + name + " not found"));

        return persistenceToDto(entity);
    }

    public boolean exists(String name) {
        validateStringRequest(name);
        try {
            return getCharacterRepository.existsByName(name);
        } catch (NotFoundException e) {
            return false;
        }
    }

    private void validateStringRequest(String request) {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
    }

    private CharacterDTO persistenceToDto(CharacterEntity entity) {
        return new CharacterDTO(
                entity.getName(), entity.getAlignment(), entity.getSuperGroup()
        );
    }
}

package com.sergioruy.character.repository;

import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.CreateCharacterRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateCharacterRepositoryImpl implements CreateCharacterRepository {

    public void persistCharacter(CharacterEntity characterEntity) {
        persistAndFlush(characterEntity);
    }
}

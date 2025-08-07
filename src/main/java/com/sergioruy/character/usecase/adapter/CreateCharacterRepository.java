package com.sergioruy.character.usecase.adapter;

import com.sergioruy.character.entity.CharacterEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

public interface CreateCharacterRepository extends PanacheRepositoryBase<CharacterEntity, String> {
    void persistCharacter(CharacterEntity characterEntity);
}

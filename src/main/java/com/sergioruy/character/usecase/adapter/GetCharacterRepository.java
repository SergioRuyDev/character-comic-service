package com.sergioruy.character.usecase.adapter;

import com.sergioruy.character.entity.CharacterEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.Optional;

public interface GetCharacterRepository extends PanacheRepositoryBase<CharacterEntity, String> {

    Optional<CharacterEntity> findByName(String name);
    boolean existsByName(String name);
}

package com.sergioruy.character.repository;

import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.GetCharacterRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class GetCharacterRepositoryImpl implements GetCharacterRepository {

    @Override
    public Optional<CharacterEntity> findByName(String name) {
        return find("name = :name", Parameters.with("name", name))
                .firstResultOptional();
    }

    @Override
    public boolean existsByName(String name) {
        return count("name = :name", Parameters.with("name", name)) > 0;
    }
}

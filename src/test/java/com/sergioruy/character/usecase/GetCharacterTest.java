package com.sergioruy.character.usecase;

import com.sergioruy.character.CharacterBaseTest;
import com.sergioruy.character.dto.CharacterDTO;
import com.sergioruy.character.entity.CharacterEntity;
import com.sergioruy.character.usecase.adapter.GetCharacterRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@QuarkusTest
class GetCharacterTest extends CharacterBaseTest {

    @Inject
    GetCharacter getCharacter;

    @InjectMock
    GetCharacterRepository getCharacterRepository;

    private CharacterEntity character;

    @BeforeEach
    void setup() {
        character = new CharacterEntity();
        character.setId(UUID.randomUUID().toString());
        character.setName("Wolverine");
        character.setAlignment("Chaotic Good");
        character.setSuperGroup("X-men");
    }

    @Test
    @TestTransaction
    void getCharacterShouldWork() {
        when(getCharacterRepository.findById(anyString())).thenReturn(character);

        CharacterDTO characterDTO = getCharacter.getCharacter(character.getId());

        assertNotNull(characterDTO);
        assertEquals(character.getName(), characterDTO.name());
        assertEquals(character.getAlignment(), characterDTO.alignment());
        assertEquals(character.getSuperGroup(), characterDTO.superGroup());
    }

}

package com.sergioruy.character.framework;

import com.sergioruy.character.dto.CharacterDTO;
import com.sergioruy.character.usecase.CreateCharacter;
import com.sergioruy.character.usecase.GetCharacter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("characters")
public class CharacterResource {

    CreateCharacter createCharacter;
    GetCharacter getCharacter;

    CharacterResource(
            CreateCharacter createCharacter,
            GetCharacter getCharacter
    ) {
        this.createCharacter = createCharacter;
        this.getCharacter = getCharacter;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new character which will be dealt with synchronously.")
    public Response createCharacter(CharacterDTO characterDTO) {
        CharacterDTO dto = createCharacter.createCharacter(characterDTO);
        return Response.status(Response.Status.CREATED).entity(dto).build();
    }
}

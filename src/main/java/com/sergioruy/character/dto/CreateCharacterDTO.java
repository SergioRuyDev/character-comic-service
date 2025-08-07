package com.sergioruy.character.dto;

public class CreateCharacterDTO {

    private String name;
    private String alignment;
    private String superGroup;

    public CreateCharacterDTO() {
    }

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperGroup() {
        return superGroup;
    }

    public void setSuperGroup(String superGroup) {
        this.superGroup = superGroup;
    }
}

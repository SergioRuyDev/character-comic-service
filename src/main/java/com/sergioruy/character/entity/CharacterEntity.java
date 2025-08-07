package com.sergioruy.character.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "comic_character")
public class CharacterEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "alignment", nullable = false)
    private String alignment;

    @Column(name = "super_group")
    private String superGroup;

    public String getAlignment() {
        return alignment;
    }

    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

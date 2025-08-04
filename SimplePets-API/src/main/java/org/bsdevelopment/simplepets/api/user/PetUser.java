package org.bsdevelopment.simplepets.api.user;

import org.bsdevelopment.pluginutils.nbt.types.CompoundData;
import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public interface PetUser {
    UUID getUserUUID();

    String getUsername();

    Player getPlayer();

    // --- Pet Handling --- //
    boolean hasPet(PetEntity entity);

    void removePet(PetEntity entity);

    List<PetEntity> getPetEntities();

    PetEntity getPetEntity(PetEntity entity);

    void setPet(PetEntity entity);

    boolean hasPets();

    boolean removePets();

    // --- Pet Hats --- //
    boolean isPetHat(PetEntity entity);

    void setPetHat(PetEntity entity, boolean hat);

    List<PetEntity> getHatPets();


    // --- Pet Saves --- //
    boolean hasPetSave(CompoundData compound);

    void removePetSave(CompoundData compound);

    void addPetSave(PetEntity entity);

    CompoundData[] getSavedPets();
}

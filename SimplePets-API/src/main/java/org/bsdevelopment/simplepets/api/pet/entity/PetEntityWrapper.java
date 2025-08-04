package org.bsdevelopment.simplepets.api.pet.entity;

import org.bsdevelopment.pluginutils.nbt.types.CompoundData;
import org.bsdevelopment.simplepets.api.user.PetUser;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public interface PetEntityWrapper {
    Entity getRawEntity();

    EntityType getPetEntityType();

    PetUser getPetUser();

    CompoundData saveData ();
    void readData (CompoundData data);
}

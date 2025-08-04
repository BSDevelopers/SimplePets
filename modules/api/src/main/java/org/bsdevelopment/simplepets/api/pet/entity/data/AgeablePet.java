package org.bsdevelopment.simplepets.api.pet.entity.data;

import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;

public interface AgeablePet extends PetEntity {
    boolean isBabySafe();

    void setBabySafe(boolean flag);
}
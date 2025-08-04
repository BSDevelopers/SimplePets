package org.bsdevelopment.simplepets.api.pet.entity;

import org.bsdevelopment.simplepets.api.pet.PetType;
import org.bsdevelopment.simplepets.api.user.PetUser;
import org.bukkit.Location;

public record PetDataHandler(PetType type, PetUser user, Location spawnLocation) {
}

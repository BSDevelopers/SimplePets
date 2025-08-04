package org.bsdevelopment.simplepets.api.pet;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public interface PetType {
    String name();

    Class<? extends PetEntity> entityClass();

    String getPermission();

    String getPermission(String addition);

    ItemBuilder builder();

    EntityType getEntityType();

    boolean isSupported();


    // Internal registry for pet types.
    Map<String, PetType> REGISTRY = new HashMap<>();

    static PetType register(String name,
                            Class<? extends PetEntity> entityClass,
                            ItemBuilder builder) {
        BasicPetType petType = new BasicPetType(name, entityClass, builder);
        REGISTRY.put(name.toLowerCase(), petType);
        return petType;
    }

    static PetType register(String name,
                            Class<? extends PetEntity> entityClass,
                            String textureID) {
        return register(name, entityClass, ItemBuilder.playerSkull("http://textures.minecraft.net/texture/" + textureID));
    }
}

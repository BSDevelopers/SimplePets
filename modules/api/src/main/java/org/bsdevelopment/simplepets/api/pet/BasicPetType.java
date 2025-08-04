package org.bsdevelopment.simplepets.api.pet;

import org.bsdevelopment.pluginutils.inventory.ItemBuilder;
import org.bsdevelopment.pluginutils.text.Colorize;
import org.bsdevelopment.pluginutils.text.WordUtils;
import org.bsdevelopment.pluginutils.version.VersionCompatibility;
import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;
import org.bukkit.entity.EntityType;

public record BasicPetType(String name, Class<? extends PetEntity> entityClass,
                           ItemBuilder builder) implements PetType {

    public BasicPetType(String name,
                        Class<? extends PetEntity> entityClass,
                        ItemBuilder builder) {
        this.name = name.toLowerCase();
        this.entityClass = entityClass;
        // Set the pet’s display name on the builder.
        this.builder = builder.withName(Colorize.translateBungeeHex("&#c8f792" +
                WordUtils.capitalize(name.toLowerCase().replace("_", " "))));
    }

    @Override
    public String getPermission() {
        return "pet.type." + name.replace("_", "");
    }

    @Override
    public String getPermission(String addition) {
        return getPermission() + "." + addition;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.valueOf(name.toUpperCase());
    }

    @Override
    public boolean isSupported() {
        return VersionCompatibility.isCompatible(entityClass);
    }
}

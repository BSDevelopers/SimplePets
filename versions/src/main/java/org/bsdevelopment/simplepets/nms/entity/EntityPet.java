package org.bsdevelopment.simplepets.nms.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.bsdevelopment.pluginutils.nbt.types.CompoundData;
import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;
import org.bsdevelopment.simplepets.api.pet.entity.PetDataHandler;
import org.bsdevelopment.simplepets.api.user.PetUser;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.entity.Entity;

public class EntityPet extends Mob implements PetEntity {
    private PetDataHandler petDataHandler;

    protected EntityPet(EntityType<? extends Mob> entityType, PetDataHandler petDataHandler) {
        super(entityType, ((CraftWorld)petDataHandler.spawnLocation().getWorld()).getHandle());
        this.petDataHandler = petDataHandler;
    }

    @Override
    public Entity getRawEntity() {
        return getBukkitEntity();
    }

    @Override
    public org.bukkit.entity.EntityType getPetEntityType() {
        return petDataHandler.type().getEntityType();
    }

    @Override
    public PetUser getPetUser() {
        return petDataHandler.user();
    }

    @Override
    public CompoundData saveData() {
        return null;
    }

    @Override
    public void readData(CompoundData data) {

    }

    @Override
    public boolean isBurning() {
        return false;
    }

    @Override
    public void setBurning(boolean var) {

    }
}
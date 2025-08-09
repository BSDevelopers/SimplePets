package org.bsdevelopment.simplepets.nms;

import net.minecraft.world.entity.Entity;
import org.bsdevelopment.pluginutils.nbt.types.CompoundData;
import org.bsdevelopment.pluginutils.version.ServerVersion;
import org.bsdevelopment.pluginutils.version.VersionCompatibility;
import org.bsdevelopment.simplepets.api.pet.PetType;
import org.bsdevelopment.simplepets.api.pet.entity.PetDataHandler;
import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;
import org.bsdevelopment.simplepets.api.spawn.FailureReason;
import org.bsdevelopment.simplepets.api.spawn.PetSpawnResult;
import org.bsdevelopment.simplepets.api.spawn.PetSpawnService;
import org.bsdevelopment.simplepets.api.user.PetUser;
import org.bsdevelopment.simplepets.api.utils.HelperUtilities;
import org.bsdevelopment.simplepets.nms.entity.EntityPet;
import org.bukkit.Location;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SpawnService extends PetSpawnService {
    private final Map<PetType, Class<? extends EntityPet>> petMap = new HashMap<>();

    public SpawnService (ClassLoader classLoader) {
        for (PetType type : PetType.REGISTRY.values()) {
            String name = "Entity"+type.entityClass().getSimpleName().replaceFirst("I", "");
            try {
                Class<?> clazz = Class.forName(HelperUtilities.NMS_PATH+"."+ ServerVersion.getVersion().getVersionName() +".entity.type."+name, false, classLoader);
                if (!VersionCompatibility.isCompatible(clazz)) {
                    // Pet not compatible
                    continue;
                }

                @SuppressWarnings("unchecked")
                Class<? extends EntityPet> petClass = (Class<? extends EntityPet>) clazz;
                petMap.put(type, petClass);
            }catch (Exception e) {
                // Pet not added for this version
            }
        }
    }

    @Override
    public PetEntity spawnPet(List<PetSpawnResult.ReasonDetail> reasonDetails, PetType type, PetUser user, Location location, CompoundData compound) {
        if (type == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.UNKNOWN,
                    "PetType was null."));
            return null;
        }
        if (user == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.UNKNOWN,
                    "PetUser was null."));
            return null;
        }
        if (location == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.LOCATION_DENIED,
                    "Spawn location was null."));
            return null;
        }
        if (!location.getChunk().isLoaded()) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.LOCATION_DENIED,
                    "Chunk at the desired location is not loaded."));
            return null;
        }

        Class<?> nmsClass = petMap.get(type);
        if (nmsClass == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.UNAVAILABLE,
                    "Pet type '" + type.getName() + "' is not available on "
                            + ServerVersion.getVersion().getVersionName() + '.'));
            return null;
        }

        PetDataHandler dataHandler = new PetDataHandler(type, user, location);
        EntityPet entityPet;

        try {
            entityPet = (EntityPet) nmsClass.getDeclaredConstructor(PetDataHandler.class).newInstance(dataHandler);
        } catch (InvocationTargetException ite) {
            Throwable cause = Optional.ofNullable(ite.getCause()).orElse(ite);
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.UNKNOWN,
                    "Exception while creating pet: " + cause));
            return null;

        } catch (ReflectiveOperationException e) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                    FailureReason.UNKNOWN,
                    "Unable to reflectively create NMS pet entity: " + e.getMessage()));
            return null;
        }


        if (compound != null && !compound.copyMap().isEmpty()) {
            try {
                entityPet.readData(compound);
            } catch (Exception ex) {
                reasonDetails.add(new PetSpawnResult.ReasonDetail(
                        FailureReason.UNKNOWN,
                        "Failed to apply NBT data: " + ex.getMessage()));
                entityPet.remove(Entity.RemovalReason.DISCARDED);
                return null;
            }
        }

        return entityPet;
    }
}

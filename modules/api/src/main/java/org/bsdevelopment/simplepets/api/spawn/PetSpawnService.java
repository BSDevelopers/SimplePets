package org.bsdevelopment.simplepets.api.spawn;

import org.bsdevelopment.pluginutils.nbt.types.CompoundData;
import org.bsdevelopment.simplepets.api.pet.PetType;
import org.bsdevelopment.simplepets.api.pet.entity.PetEntity;
import org.bsdevelopment.simplepets.api.user.PetUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class PetSpawnService {
    public abstract PetEntity spawnPet(List<PetSpawnResult.ReasonDetail> reasonDetails, PetType type, PetUser user, Location location, CompoundData compound);

    /**
     * Attempts to spawn a pet at the specified location for the given player.
     *
     * @param user    The player who wants to own the pet.
     * @param type  The type of entity (e.g., {@link PetType}).
     * @param location The location where the pet should be spawned.
     * @return A {@link PetSpawnResult} indicating success or failure. If failure, includes
     * multiple {@link PetSpawnResult.ReasonDetail} entries.
     */
    public PetSpawnResult spawnPet(PetUser user, PetType type, Location location) {
        // We'll accumulate any reasons as we go
        List<PetSpawnResult.ReasonDetail> reasonDetails = new ArrayList<>();

        // 1. Basic null checks
        if (user == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.UNKNOWN,
                "user was null."
            ));
        }
        if (type == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.UNKNOWN,
                "Pet type was null."
            ));
        }
        if (location == null) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.LOCATION_DENIED,
                "Location is null or invalid."
            ));
        }

        // If we already have reasons, fail early
        if (!reasonDetails.isEmpty()) {
            return PetSpawnResult.failure(reasonDetails);
        }

        // 2. Check if the chunk is loaded
        if (!location.getChunk().isLoaded()) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.LOCATION_DENIED,
                "Chunk at the specified location is not loaded."
            ));
        }

        // 3. Permission check example
        // TODO: Change how the permission is checked
        if (!user.getPlayer().hasPermission("pets.spawn")) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.NO_PERMISSION,
                "Player does not have 'pets.spawn' permission."
            ));
        }

        // If there's any failure reason so far, fail
        if (!reasonDetails.isEmpty()) {
            return PetSpawnResult.failure(reasonDetails);
        }

        // 5. Attempt actual spawn
        PetEntity entity = null;
        try {
            entity = spawnPet(reasonDetails, type, user, location, new CompoundData());
        } catch (Exception e) {
            // Log the error for debugging
            Bukkit.getLogger().severe("Error while spawning pet: " + e.getMessage());

            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.UNKNOWN,
                "An exception occurred during spawn: " + e.getMessage()
            ));
            return PetSpawnResult.failure(reasonDetails);
        }

        // 6. Check if spawn was canceled by another plugin/event
        if (entity.getRawEntity() == null || !entity.getRawEntity().isValid()) {
            reasonDetails.add(new PetSpawnResult.ReasonDetail(
                FailureReason.EVENT_CANCELED,
                "Spawn event was canceled by another plugin or the server."
            ));
            return PetSpawnResult.failure(reasonDetails);
        }

        // 7. If all checks passed, return success
        return PetSpawnResult.success();
    }
}

package org.bsdevelopment.simplepets.api;

import org.bsdevelopment.simplepets.api.spawn.PetSpawnService;

import java.util.logging.Logger;

public interface PetPlugin {
    Logger LOG = Logger.getLogger("SimplePets");


    PetSpawnService getSpawnService();

    NMSUtilities getNmsUtilities();
}

package org.bsdevelopment.simplepets;

import org.bsdevelopment.simplepets.api.NMSUtilities;
import org.bsdevelopment.simplepets.api.PetPlugin;
import org.bsdevelopment.simplepets.api.spawn.PetSpawnService;
import org.bsdevelopment.simplepets.api.utils.HelperUtilities;
import org.bukkit.plugin.java.JavaPlugin;


public class PetCore extends JavaPlugin implements PetPlugin {
    private PetSpawnService spawnService;
    private NMSUtilities nmsUtilities;

    @Override
    public void onEnable() {
        spawnService = HelperUtilities.getVersionedClass("SpawnService", new Class[]{ClassLoader.class}, getLoader());
        nmsUtilities = HelperUtilities.getVersionedClass("NMSUtilitiesHandler");
    }

    public ClassLoader getLoader () {
        return getClassLoader();
    }

    @Override
    public PetSpawnService getSpawnService() {
        return spawnService;
    }

    @Override
    public NMSUtilities getNmsUtilities() {
        return nmsUtilities;
    }
}

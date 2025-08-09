package org.bsdevelopment.simplepets;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandAPIConfig;
import org.bsdevelopment.simplepets.api.NMSUtilities;
import org.bsdevelopment.simplepets.api.PetPlugin;
import org.bsdevelopment.simplepets.api.pet.PetTypeHandler;
import org.bsdevelopment.simplepets.api.spawn.PetSpawnService;
import org.bsdevelopment.simplepets.api.utils.HelperUtilities;
import org.bsdevelopment.simplepets.commands.Arguments;
import org.bukkit.plugin.java.JavaPlugin;


public class PetCore extends JavaPlugin implements PetPlugin {
    private PetSpawnService spawnService;
    private NMSUtilities nmsUtilities;

    @Override
    public void onLoad() {
        CommandAPIConfig config = new CommandAPIBukkitConfig(this);
        config.verboseOutput(false);

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this)
                .silentLogs(true)
                .verboseOutput(false)
        );
    }

    @Override
    public void onEnable() {
        spawnService = HelperUtilities.getVersionedClass("SpawnService", new Class[]{ClassLoader.class}, getLoader());
        nmsUtilities = HelperUtilities.getVersionedClass("NMSUtilitiesHandler");

        CommandAPI.onEnable();

        new CommandAPICommand("testcommand2")
                .withArguments(Arguments.setupPetTypeArgument("type"))
                .executes((sender, args) -> {
                    sender.sendMessage("Executed command with pet: "+args.get("type"));
                })
                .register();

        PetTypeHandler.INITIATE();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    public ClassLoader getLoader() {
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

package de.phyx.transientenchantments;

import de.phyx.transientenchantments.logic.EventLogic;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class TransientEnchantments extends JavaPlugin {

    public static World cbWorld = Bukkit.getWorld("cb");
    public static String cbWorldName = "cb";

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new EventLogic(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

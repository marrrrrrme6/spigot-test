package io.github.marrrrrrme6.plugin.test.test;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;

public final class Test extends JavaPlugin {

    private static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("startup plugin");
        getCommand("test").setExecutor(new CommandClass());
        getCommand("gui").setExecutor(new CommandClass());
        getCommand("janken").setExecutor(new CommandClass());
        plugin = this;
        Bukkit.getServer().getPluginManager().registerEvents(new EventListenerClass(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("shutdown plugin");
    }

    public static Plugin getPlugin() {return plugin;}
}

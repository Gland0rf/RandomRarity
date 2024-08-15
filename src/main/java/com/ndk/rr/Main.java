package com.ndk.rr;

import com.ndk.rr.Commands.Options;
import com.ndk.rr.Listeners.ItemRarityListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable(){
        instance = this;

        Bukkit.getLogger().info("RandomRarity plugin is active.");
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new ItemRarityListener(), this);

        this.getCommand("randomrarity").setExecutor(new Options());
    }

    private void checkConfigIntegrity(){
        FileConfiguration config = this.getConfig();
    }

    public static Main getInstance() {
        return instance;
    }

}
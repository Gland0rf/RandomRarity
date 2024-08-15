package com.ndk.rr.Util;

import com.ndk.rr.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;
import java.util.Set;

public class Util {

    public static String addNewRarity(String rarityName, double chance, ChatColor color) {
        FileConfiguration config = Main.getInstance().getConfig();

        // Check if the rarity already exists to avoid duplicates
        if (config.contains("rarity." + rarityName.toLowerCase())) {
            return rarityName + " rarity already exists in the configuration.";
        }

        // Add the new rarity to the configuration
        config.set("rarity." + rarityName.toLowerCase(), chance);
        config.set("colors." + rarityName.toLowerCase(), color.name());

        // Save the configuration
        Main.getInstance().saveConfig();

        return "Added new rarity: " + rarityName + " with a chance of " + chance + "%.";
    }

    public static String deleteRarity(String rarityName) {
        FileConfiguration config = Main.getInstance().getConfig();

        // Check if the rarity exists
        if (!config.contains("rarity." + rarityName.toLowerCase())) {
            return rarityName + " rarity does not exist in the configuration.";
        }

        // Remove the rarity and its associated color
        config.set("rarity." + rarityName.toLowerCase(), null);
        config.set("colors." + rarityName.toLowerCase(), null);

        // Save the configuration
        Main.getInstance().saveConfig();

        return "Deleted rarity: " + rarityName;
    }

    public static String changeRarityChance(String rarityName, double chance) {
        FileConfiguration config = Main.getInstance().getConfig();

        // Check if the rarity already exists to avoid duplicates
        if (!config.contains("rarity." + rarityName.toLowerCase())) {
            return rarityName + " rarity doesn't exist in the configuration.";
        }

        // Add the new rarity to the configuration
        config.set("rarity." + rarityName.toLowerCase(), chance);

        // Save the configuration
        Main.getInstance().saveConfig();

        return "Changed chance for rarity: " + rarityName + ". Now has a chance of: " + chance + "%.";
    }

    public static String changeRarityColor(String rarityName, ChatColor color) {
        FileConfiguration config = Main.getInstance().getConfig();

        // Check if the rarity already exists to avoid duplicates
        if (!config.contains("rarity." + rarityName.toLowerCase())) {
            return rarityName + " rarity doesn't exist in the configuration.";
        }

        // Add the new rarity to the configuration
        config.set("colors." + rarityName.toLowerCase(), color.name());

        // Save the configuration
        Main.getInstance().saveConfig();

        return "Changed color for rarity: " + rarityName + ". Now has the color: " + color.name() + ".";
    }

    public static ChatColor getChatColor(String colorName) {
        try {
            // Convert the color name to uppercase to match the enum constants
            return ChatColor.valueOf(colorName.toUpperCase());
        } catch (IllegalArgumentException e) {
            // If the color name is invalid, return a default color (e.g., GRAY)
            return null;
        }
    }

}

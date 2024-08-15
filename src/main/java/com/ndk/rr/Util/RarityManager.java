package com.ndk.rr.Util;

import com.ndk.rr.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RarityManager {

    private static final Random random = new Random();

    // Method to change the rarity of an item
    public static void changeItemRarity(ItemStack item) {
        if (item == null) return;

        FileConfiguration config = Main.getInstance().getConfig();

        // Get all configured rarities in order of configuration
        Set<String> raritySet = config.getConfigurationSection("rarity").getKeys(false);
        List<String> rarities = new ArrayList<>(raritySet);

        // Set the first rarity as the default
        String defaultRarity = rarities.get(0);
        ChatColor defaultColor = Util.getChatColor(config.getString("colors." + defaultRarity, "GRAY"));

        String chosenRarity = defaultRarity;
        ChatColor rarityColor = defaultColor;

        // Roll for each rarity independently
        for (String rarity : rarities) {
            double chance = config.getDouble("rarity." + rarity, 0);
            double roll = random.nextDouble() * 100; // Roll a number between 0 and 100

            if (roll < chance) {
                chosenRarity = rarity;
                rarityColor = Util.getChatColor(config.getString("colors." + rarity, "GRAY"));
                break; // Stop after the first successful roll to apply only one rarity
            }
        }

        // Update the item's display name to reflect the new rarity
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            String originalName = meta.getDisplayName();
            if (originalName == null || originalName.isEmpty()) {
                originalName = item.getType().name(); // Fallback to item type name if display name is empty
            }
            // Remove any existing rarity prefix (if necessary)
            String strippedName = originalName.replaceAll("§[0-9a-fk-or]", ""); // Remove any existing color codes
            meta.setDisplayName(rarityColor + chosenRarity + " " + ChatColor.RESET + strippedName);
            item.setItemMeta(meta);
        }
    }

}

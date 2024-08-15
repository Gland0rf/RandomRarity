package com.ndk.rr.Listeners;

import com.ndk.rr.Util.RarityManager;
import com.ndk.rr.Util.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemRarityListener implements Listener {

    // Trigger when a player crafts an item
    @EventHandler
    public void onItemCraft(CraftItemEvent event) {
        ItemStack craftedItem = event.getRecipe().getResult().clone(); // Get the crafted item
        RarityManager.changeItemRarity(craftedItem); // Change the rarity as needed
        event.getInventory().setResult(craftedItem); // Set the result item with updated rarity
    }

    // Trigger when a player picks up an item
    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        ItemStack pickedUpItem = event.getItem().getItemStack(); // Get the picked-up item
        RarityManager.changeItemRarity(pickedUpItem); // Change the rarity as needed
        event.getItem().setItemStack(pickedUpItem); // Update the item with the new rarity
    }

    // Trigger when a player clicks an item in their inventory
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem(); // Get the clicked item
        if (clickedItem != null) {
            RarityManager.changeItemRarity(clickedItem); // Change the rarity as needed
        }
    }

}

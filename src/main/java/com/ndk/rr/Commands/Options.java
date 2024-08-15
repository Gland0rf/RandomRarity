package com.ndk.rr.Commands;

import com.ndk.rr.Util.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class Options implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command string, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            sender.sendMessage("Invalid Usage! You can use: options");
            return false;
        }else if(args.length == 1){
            sender.sendMessage("Invalid Usage! You can use: create_rarity, delete_rarity, set_chance");
            return false;
        }

        if(args[1].equals("create_rarity")){
            if(args.length < 5){
                sender.sendMessage("Invalid Usage! You can use: create_rarity <name> <chance> <color>");
                return false;
            }

            String name = args[2];
            String chanceStr = args[3];
            String colorStr = args[4];

            if(name.isBlank()){
                sender.sendMessage("The name cannot be blank.");
                return false;
            }

            if(!isInteger(chanceStr)){
                sender.sendMessage("The chance has to be a valid integer.");
                return false;
            }

            int chance = Integer.parseInt(chanceStr);

            if(chance > 100){
                sender.sendMessage("The drop chance cannot be higher than 100%.");
                return false;
            }

            ChatColor color = Util.getChatColor(colorStr);
            if(color == null){
                sender.sendMessage("\"" + colorStr + "\" is not a valid color.");
                return false;
            }

            String status = Util.addNewRarity(name, chance, color);
            sender.sendMessage(status);
        }else if(args[1].equals("delete_rarity")){
            if(args.length < 3){
                sender.sendMessage("Invalid Usage! You can use: delete_rarity <name>");
                return false;
            }

            String name = args[2];
            String status = Util.deleteRarity(name);
            sender.sendMessage(status);
        }else if(args[1].equals("change_chance")) {
            if(args.length < 4){
                sender.sendMessage("Invalid Usage! You can use: change_chance <name> <chance>");
                return false;
            }

            String name = args[2];
            String chanceStr = args[3];

            if(name.isBlank()){
                sender.sendMessage("The name cannot be blank.");
                return false;
            }

            if(!isInteger(chanceStr)){
                sender.sendMessage("The chance has to be a valid integer.");
                return false;
            }

            int chance = Integer.parseInt(chanceStr);

            if(chance > 100){
                sender.sendMessage("The drop chance cannot be higher than 100%.");
                return false;
            }

            String status = Util.changeRarityChance(name, chance);
            sender.sendMessage(status);
        }else if(args[1].equals("change_color")) {
            if(args.length < 4){
                sender.sendMessage("Invalid Usage! You can use: change_color <name> <color>");
                return false;
            }

            String name = args[2];
            String colorStr = args[3];

            if(name.isBlank()){
                sender.sendMessage("The name cannot be blank.");
                return false;
            }

            ChatColor color = Util.getChatColor(colorStr);
            if(color == null){
                sender.sendMessage("\"" + colorStr + "\" is not a valid color.");
                return false;
            }

            String status = Util.changeRarityColor(name, color);
            sender.sendMessage(status);
        }

        return false;
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

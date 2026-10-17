package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

    private boolean psychedelicActive = false;

    @Override
    public void onEnable() {
        getLogger().info("PsychedelicPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PsychedelicPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("psychedelic")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                psychedelicActive = !psychedelicActive;
                if (psychedelicActive) {
                    startPsychedelicEffects(player);
                    player.sendMessage("Psychedelic effects activated!");
                } else {
                    stopPsychedelicEffects(player);
                    player.sendMessage("Psychedelic effects deactivated!");
                }
                return true;
            } else {
                sender.sendMessage("This command can only be run by a player.");
                return false;
            }
        }
        return false;
    }

    private void startPsychedelicEffects(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!psychedelicActive) {
                    cancel();
                    return;
                }
                player.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 2, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100, 1, false, false));
            }
        }.runTaskTimer(this, 0, 20);
    }

    private void stopPsychedelicEffects(Player player) {
        player.removePotionEffect(PotionEffectType.NAUSEA);
        player.removePotionEffect(PotionEffectType.SPEED);
        player.removePotionEffect(PotionEffectType.LEVITATION);
        player.removePotionEffect(PotionEffectType.INVISIBILITY);
    }
}
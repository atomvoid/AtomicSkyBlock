package com.atom.skyblock.achievements;

import com.atom.skyblock.SBMain;
import com.atom.skyblock.powerups.impl.BoosterItem;
import dev.atom.atomachievements.achievement.i.Achievement;
import dev.atom.atomachievements.api.AchievementAPI;
import org.bukkit.Material;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ZombieKiller extends Achievement {
    @Override
    public String name() {
        return "Matador de Zumbi";
    }

    @Override
    public String description() {
        return "Mate 100 Zumbis.";
    }

    @Override
    public String reward() {
        return "1x Cabeça de Zumbi e 1x Booster de Cobble (2x)";
    }

    @Override
    public String id() {
        return "asb_a_mastery_1";
    }

    @Override
    public AchievementRarity rarity() {
        return AchievementRarity.RARE;
    }

    @Override
    public JavaPlugin source() {
        return SBMain.INSTANCE;
    }

    @Override
    public boolean showTitle() {
        return true;
    }

    @Override
    public Material itemDisplay() {
        return Material.ROTTEN_FLESH;
    }

    @Override
    public Type type() {
        return Type.ACTION;
    }

    @Override
    public void onComplete(Player player) {
        SBMain.globalCobblestoneLocation.getWorld().dropItemNaturally(SBMain.globalCobblestoneLocation, BoosterItem.returnItemStack(2.F));
        SBMain.globalCobblestoneLocation.getWorld().dropItemNaturally(SBMain.globalCobblestoneLocation, new ItemStack(Material.ZOMBIE_HEAD));
        this.conclude(player);
    }

    @EventHandler
    public void onKillAnimal(final EntityDeathEvent ev) {
        if (ev.getEntity().getKiller() != null && ev.getEntity() instanceof Zombie) {
            final Player player = ev.getEntity().getKiller();
            if (!AchievementAPI.hasCompleted(player, this)) {
                final double progress;
                progress = AchievementAPI.getAchievementInfo(this, player).progress += (double) 1 / 100;
                if (progress >= 1) {
                    this.onComplete(player);
                }
            }
        }
    }
}

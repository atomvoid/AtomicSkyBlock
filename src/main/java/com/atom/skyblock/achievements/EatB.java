package com.atom.skyblock.achievements;

import com.atom.skyblock.SBMain;
import dev.atom.atomachievements.achievement.i.Achievement;
import dev.atom.atomachievements.api.AchievementAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class EatB extends Achievement {
    @Override
    public String name() {
        return "Rango";
    }

    @Override
    public String description() {
        return "Coma 1.000 comidas.";
    }

    @Override
    public String reward() {
        return "64x Bife";
    }

    @Override
    public String id() {
        return "asb_a_3_b";
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
    public Type type() {
        return Type.ACTION;
    }

    @Override
    public void onComplete(Player player) {
        SBMain.globalCobblestoneLocation.getWorld().dropItemNaturally(SBMain.globalCobblestoneLocation, new ItemStack(Material.COOKED_BEEF, 64));
        this.conclude(player);
    }

    @Override
    public Material itemDisplay() {
        return Material.MUTTON;
    }

    @EventHandler
    public void onConsoom(final PlayerItemConsumeEvent ev) {
        if (!AchievementAPI.hasCompleted(ev.getPlayer(), this)) {
            final double progress;
            progress = AchievementAPI.getAchievementInfo(this, ev.getPlayer()).progress += (double) 1 / 1000;
            if (progress >= 1) {
                this.onComplete(ev.getPlayer());
            }
        }
    }
}

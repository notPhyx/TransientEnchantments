package de.phyx.transientenchantments.interfaces;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Enchantment {

    void onActivate(Player player, ItemStack itemStack);

    boolean canUse(Material material);

    int getCooldown();

    String getName();

    String getDescription();

    int getMaximumLevel();

    int getStartLevel();

    NamespacedKey getKey();
}

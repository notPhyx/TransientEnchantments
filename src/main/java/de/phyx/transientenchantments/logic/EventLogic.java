package de.phyx.transientenchantments.logic;

import de.phyx.transientenchantments.interfaces.Enchantment;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class EventLogic implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || !item.hasItemMeta()) {
            return; // No item or no item meta
        }

        PersistentDataContainer dataContainer = item.getItemMeta().getPersistentDataContainer();

        // Liste der registrierten Verzauberungen abrufen (einschließlich der von externen Plugins)
        List<Enchantment> customEnchantments = EnchantmentAPI.getRegisteredEnchantments();

        // Über alle registrierten Verzauberungen iterieren und prüfen
        for (Enchantment enchantment : customEnchantments) {
            NamespacedKey enchantmentKey = enchantment.getKey();

            if (dataContainer.has(enchantmentKey, PersistentDataType.STRING)) {
                handleEnchantmentUse(enchantment, player, item);
                break;
            }
        }
    }

    private void handleEnchantmentUse(Enchantment enchantment, Player player, ItemStack item) {
        player.sendMessage("You have activated the enchantment: " + enchantment.getName());
        enchantment.onActivate(player, item);
    }
}

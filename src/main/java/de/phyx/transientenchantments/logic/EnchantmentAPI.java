package de.phyx.transientenchantments.logic;

import de.phyx.transientenchantments.TransientEnchantments;
import de.phyx.transientenchantments.interfaces.Enchantment;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentAPI {

    // Liste aller registrierten Verzauberungen (inkl. externer Plugins)
    private static final List<Enchantment> registeredEnchantments = new ArrayList<>();

    // Methode zum Registrieren einer Verzauberung
    public static void registerEnchantment(Enchantment enchantment) {
        if (!registeredEnchantments.contains(enchantment)) {
            registeredEnchantments.add(enchantment);
            System.out.println("Enchantment registered: " + enchantment.getName());
        } else {
            System.out.println("Enchantment already registered: " + enchantment.getName());
        }
    }

    public static Enchantment getEnchantment(ItemStack itemStack){
        NamespacedKey key = new NamespacedKey(TransientEnchantments.getPlugin(TransientEnchantments.class), "enchantment");

        PersistentDataContainer dataContainer = itemStack.getItemMeta().getPersistentDataContainer();

        Enchantment e;

        if (dataContainer.get(key, PersistentDataType.STRING) != null){
            e = getEnchantmentByName(dataContainer.get(key, PersistentDataType.STRING));
        } else {
            e = null;
        }
        return e;
    }

    // Methode, um alle registrierten Verzauberungen zu bekommen
    public static List<Enchantment> getRegisteredEnchantments() {
        return registeredEnchantments;
    }

    public static Enchantment getEnchantmentByName(String name){
        for (Enchantment enchantment : getRegisteredEnchantments()){
            if (enchantment.getName().equals(name)){
                return enchantment;
            }
        }
        return null;
    }
}

package com.marme.plugin.test.test;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;




public class EventListenerClass implements Listener {
    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent e) {
        Bukkit.broadcastMessage("fired furnace smelt!");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();

        if (item == null
                || item.hasItemMeta()) return;

        if (item.getType().equals(Material.CHEST)) {
            if (item.getItemMeta().getDisplayName().equals("バックパック1")) {
                Player player = (Player) event.getWhoClicked();
                Inventory inv = Bukkit.createInventory(null, 27, "gui");

                ItemStack itemStack = new ItemStack(Material.BREAD);
                ItemMeta itemMeta = itemStack.getItemMeta();
                //アイテム名をセット
                itemMeta.setDisplayName("焼きたてのパン");
                List<String> lore = Arrays.asList("とても良い香りがする", "120円");
                //説明文をセット
                itemMeta.setLore(lore);
                //ItemMetaをセットしないと反映されない。
                itemStack.setItemMeta(itemMeta);
                inv.addItem(itemStack);
                player.openInventory(inv);
                //player.closeInventory();

            }else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("ゴミ箱")) {
                Player player = (Player) event.getWhoClicked();
                Inventory inv = Bukkit.createInventory(null, 9, "ゴミ箱");
                player.openInventory(inv);
            }
        }
    }
}

package io.github.marrrrrrme6.plugin.test.test;

//command import
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommandClass implements CommandExecutor {
    public void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9, "gui");
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = item.getItemMeta();
        //アイテム名をセット
        itemMeta.setDisplayName("ゴミ箱");
        //ItemMetaをセットしないと反映されない。
        item.setItemMeta(itemMeta);

        inv.setItem(0, item);

        ItemStack item2 = new ItemStack(Material.CHEST);
        ItemMeta itemMeta2 = item2.getItemMeta();
        //アイテム名をセット
        itemMeta2.setDisplayName("バックパック1");
        List<String> lore = Arrays.asList("普通のチェストと同じ量が入りそうだ","(27個)");
        //説明文をセット
        itemMeta2.setLore(lore);
        //ItemMetaをセットしないと反映されない。
        item2.setItemMeta(itemMeta2);

        inv.setItem(1, item2);

        player.openInventory(inv);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("test")) { //親コマンドの判定
            if (!sender.hasPermission("testplugin.commands.test")) {
                sender.sendMessage("§4パーミッションを持っていません!");
                return false;
            }
            sender.sendMessage("§a/test が実行されました！");
            if (args.length == 0) { //サブコマンドの個数が0、つまりサブコマンド無し
                sender.sendMessage("§eサブコマンドなし!");
            } else { //サブコマンドの個数が0以外
                sender.sendMessage("§eサブコマンドあり!");
                if (args[0].equalsIgnoreCase("hello")) { //サブコマンドが「hello」かどうか
                    sender.sendMessage("§bHello World!");
                } else { //サブコマンドが「hello」以外
                    sender.sendMessage("§eその他のサブコマンドです");
                }
            }
            return true;
        }

        if(command.getName().equalsIgnoreCase("janken")) {
            if(args.length == 0)return false;
            String[] hand = {"goo","tyo","paa"};

            //乱数生成用のクラスをインスタンス化
            Random r = new Random();
            String enemy = hand[r.nextInt(3)];

            if(args[0].equalsIgnoreCase("enemy")) {
                sender.sendMessage("Enemy choice is " + enemy + ".");
                return true;

            }else if(args[0].equalsIgnoreCase("paa") && enemy.equalsIgnoreCase("tyo") ||
                    args[0].equalsIgnoreCase("tyo") && enemy.equalsIgnoreCase("goo") ||
                    args[0].equalsIgnoreCase("goo") && enemy.equalsIgnoreCase("paa")){
                //敗北のパターン
                sender.sendMessage("僕の勝ち");

            }else if(args[0].equalsIgnoreCase("tyo") && enemy.equalsIgnoreCase("paa") ||
                    args[0].equalsIgnoreCase("goo") && enemy.equalsIgnoreCase("tyo") ||
                    args[0].equalsIgnoreCase("paa") && enemy.equalsIgnoreCase("goo")) {
                //勝利のパターン
                sender.sendMessage("君の勝ち");

            }else if(args[0].equalsIgnoreCase(enemy)) {
                //あいこのパターン
                sender.sendMessage("あいこ");

            }else {
                // goo tyo paa 以外が入力されたパターン
                sender.sendMessage("gooかtyoかpaaを入力してね");
                return false;
            }
        }

        if(command.getName().equalsIgnoreCase("gui")) {
            Player player = (Player) sender;
            openGUI(player);
        }
        return true;
    }
}

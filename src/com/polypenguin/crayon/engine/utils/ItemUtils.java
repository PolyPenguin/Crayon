package com.polypenguin.crayon.engine.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.polypenguin.crayon.Crayon;

import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.action.CrayonAction;
import com.polypenguin.crayon.engine.action.PassiveChangeAction;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Matthias Kovacic
 *
 * Utilities that handle item related stuff.
 */
public class ItemUtils {

    private static Field profileField;

    public static ItemStack getItem(Material material, int amount, String name, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getCustomItem(Material material, int id, short damage, int amount, String name, String... lore) {
        ItemStack item = new ItemStack(material, amount, damage, Byte.valueOf((byte)id));
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        meta.setUnbreakable(true);
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE });

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getSkullItem(int amount, String owner, String name, String... lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, amount, (short)0);
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        meta.setOwningPlayer(Bukkit.getOfflinePlayer(owner));
        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getSkullItemFromBase64(int amount, String name, String base, String... lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, amount, (short)0);
        SkullMeta meta = (SkullMeta)item.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { base })
                .getBytes());

        profile.getProperties().put("textures", new Property("textures", new String(data)));

        try {
            if (profileField == null) {
                profileField = meta.getClass().getDeclaredField("profile");
            }
            profileField.setAccessible(true);
            profileField.set(meta, profile);

            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));

            item.setItemMeta(meta);

            return item;
        } catch (IllegalAccessException|NoSuchFieldException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ItemStack changeMeta(ItemStack item, int newAmount, String name, String... lore) {
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);
        item.setAmount(newAmount);

        return item;
    }

    public static ItemStack getActionItem(CrayonAction action) {
        ItemStack stack = null;

        if (action instanceof BlockChangeAction) {
            stack = getSkullItem(1, "flashlight", ChatColor.AQUA + "Block Change Action",
                    ChatColor.WHITE + "ID: " + action.getID(),
                    ChatColor.WHITE + "Amount: " + ((BlockChangeAction) action).getStates().size(),
                    ChatColor.WHITE + "Undoable: " + action.canUndo());
        } else if (action instanceof PassiveChangeAction) {
            stack = getItem(Material.PAPER, 1, ChatColor.AQUA + "Passive Action",
                    ChatColor.WHITE + "ID: " + action.getID(),
                    ChatColor.WHITE + "Operation Type: " + ((PassiveChangeAction) action).getOperation().toString(),
                    ChatColor.WHITE + "Undoable: " + action.canUndo());
        }

        return stack;
    }

    public static ItemStack getNoActionsItem() {
        return getItem(Material.PAPER, 1, ChatColor.AQUA + "You have no actions listed");
    }

    public static ItemStack getExitItem() {
        return getItem(Material.BARRIER, 1, ChatColor.RED + "Exit", new String[0]);
    }

    public static ItemStack getBackItem() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Back", new String[0]);
    }

    public static ItemStack getFillItem() {
        return getItem(Material.GRAY_STAINED_GLASS_PANE, 1, "", new String[0]);
    }

    public static ItemStack getNextPageItem() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Next Page", new String[0]);
    }

    public static ItemStack getPreviousPageItem() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Previous Page", new String[0]);
    }

    public static ItemStack getUndoItem() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Undo", new String[0]);
    }

    public static ItemStack getRedoItem() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Redo", new String[0]);
    }

    public static ItemStack getLatestUndo() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Undo latest action", new String[0]);
    }

    public static ItemStack getLatestRedo() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Redo latest action", new String[0]);
    }

    public static ItemStack getMenuItem() {
        return getItem(Material.COMPASS, 1, Crayon.getPrefix() + ChatColor.AQUA + "Menu", new String[0]);
    }

    public static ItemStack getWandItem() {
        return getItem(Material.DIAMOND_AXE, 1, Crayon.getPrefix() + ChatColor.AQUA + "Crayon Wand", new String[0]);
    }

    public static ItemStack getConfirmItem() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Confirm", new String[0]);
    }

    public static ItemStack getYesItem() {
        return getItem(Material.GREEN_STAINED_GLASS_PANE, 1, ChatColor.GREEN + "Yes", new String[0]);
    }

    public static ItemStack getNoItem() {
        return getItem(Material.RED_STAINED_GLASS_PANE, 1, ChatColor.RED + "No", new String[0]);
    }

    public static boolean isCrayonItem(ItemStack item) {
        if (item.hasItemMeta()) {
            if (item.getItemMeta().getDisplayName().contains("Crayon")) {
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean hasActionAssigned(ItemStack stack) {
        return (stack != getFillItem() && stack != getExitItem());
    }

}

package com.polypenguin.pencil.engine.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.polypenguin.pencil.Pencil;

import com.polypenguin.pencil.engine.Clipboard;
import com.polypenguin.pencil.engine.action.BlockChangeAction;
import com.polypenguin.pencil.engine.action.PencilAction;
import com.polypenguin.pencil.engine.action.PassiveChangeAction;
import com.polypenguin.pencil.engine.geometry.selection.CuboidSelection;
import com.polypenguin.pencil.engine.geometry.selection.ShapeSelection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
 * Utilities that handle item-creation related stuff.
 */
public class ItemUtils {

    private static Field profileField;

    public static ItemStack getItem(Material material, int amount, String name, String... lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getCustomItem(Material material, int id, short damage, int amount, String name, String... lore) {
        ItemStack item = new ItemStack(material, amount, damage, Byte.valueOf((byte)id));
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(Arrays.asList(lore));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);

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

        //TODO: GameProfile doesn't exist
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { base })
                .getBytes());

        //TODO: Property class doesn't exist
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

    public static ItemStack getActionItem(PencilAction action) {
        ItemStack stack = null;

        if (action instanceof BlockChangeAction) {
            stack = getSkullItem(1, "flashlight", ChatColor.AQUA + "Block Change Action",
                    ChatColor.WHITE + "ID: " + action.getID(),
                    ChatColor.WHITE + "Size: " + ((BlockChangeAction) action).getStates().size(),
                    ChatColor.WHITE + "Undoable: " + action.canUndo());
        } else if (action instanceof PassiveChangeAction) {
            stack = getItem(Material.PAPER, 1, ChatColor.AQUA + "Passive Action",
                    ChatColor.WHITE + "ID: " + action.getID(),
                    ChatColor.WHITE + "Operation Type: " + ((PassiveChangeAction) action).getOperation().toString(),
                    ChatColor.WHITE + "Undoable: " + action.canUndo());
        }

        return stack;
    }

    public static ItemStack getClipboardItem(Clipboard clipboard) {
        ItemStack stack = null;

        if (clipboard.getSelection() instanceof CuboidSelection) {
            CuboidSelection cuboidSelection = (CuboidSelection) clipboard.getSelection();

            stack = getItem(Material.PAPER, 1, ChatColor.AQUA + "Clipboard",
                    ChatColor.WHITE + "Owner: " + clipboard.getOwner().getPlayer().getName(),
                    ChatColor.WHITE + "Selection Type: " + clipboard.getSelection().getSelectionType(),
                    ChatColor.WHITE + "Minimum Vector: " + VectorUtils.toString(cuboidSelection.getNativeMinimum()),
                    ChatColor.WHITE + "Maximum Vector: " + VectorUtils.toString(cuboidSelection.getNativeMaximum()),
                    ChatColor.WHITE + "Size: " + clipboard.getPreStates().size());
        } else if (clipboard.getSelection() instanceof ShapeSelection) {
            ShapeSelection shapeSelection = (ShapeSelection) clipboard.getSelection();

            stack = getItem(Material.PAPER, 1, ChatColor.AQUA + "Clipboard",
                    ChatColor.WHITE + "Owner: " + clipboard.getOwner().getPlayer().getName(),
                    ChatColor.WHITE + "Selection Type: " + clipboard.getSelection().getSelectionType(),
                    ChatColor.WHITE + "Shape Type: " + shapeSelection.getType().getName(),
                    ChatColor.WHITE + "Shape Scale: " + VectorUtils.toString(shapeSelection.getScale()),
                    ChatColor.WHITE + "Size: " + clipboard.getPreStates().size());
        }

        return stack;
    }

    public static ItemStack getNoActionsItem() {
        return getItem(Material.PAPER, 1, ChatColor.AQUA + "You have no actions listed");
    }

    public static ItemStack getNoClipboardItem() {
        return getItem(Material.PAPER, 1, ChatColor.AQUA + "You have no clipboard listed");
    }

    public static ItemStack getExitItem() {
        return getItem(Material.BARRIER, 1, ChatColor.RED + "Exit", new String[0]);
    }

    public static ItemStack getBackItem() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Back");
    }

    public static ItemStack getFillItem() {
        return getItem(Material.GRAY_STAINED_GLASS_PANE, 1, "");
    }

    public static ItemStack getNextPageItem() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Next Page");
    }

    public static ItemStack getPreviousPageItem() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Previous Page");
    }

    public static ItemStack getUndoItem() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Undo");
    }

    public static ItemStack getRedoItem() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Redo");
    }

    public static ItemStack getLatestUndo() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Undo latest action");
    }

    public static ItemStack getLatestRedo() {
        return getSkullItem(1, "MHF_ArrowLeft", ChatColor.GREEN + "Redo latest action");
    }

    public static ItemStack getMenuItem() {
        return getItem(Material.COMPASS, 1, Pencil.getPrefix() + ChatColor.AQUA + "Menu");
    }

    public static ItemStack getWandItem() {
        return getItem(Material.DIAMOND_AXE, 1, Pencil.getPrefix() + ChatColor.AQUA + "Pencil Wand");
    }

    public static ItemStack getConfirmItem() {
        return getSkullItem(1, "MHF_ArrowRight", ChatColor.GREEN + "Confirm");
    }

    public static ItemStack getYesItem() {
        return getItem(Material.GREEN_STAINED_GLASS_PANE, 1, ChatColor.GREEN + "Yes");
    }

    public static ItemStack getNoItem() {
        return getItem(Material.RED_STAINED_GLASS_PANE, 1, ChatColor.RED + "No");
    }

    public static boolean isCrayonItem(ItemStack item) {
        if (item.hasItemMeta()) {
            return item.getItemMeta().getDisplayName().contains("Pencil");
        }
        return false;
    }

    public static boolean hasActionAssigned(ItemStack stack) {
        return (stack != getFillItem() && stack != getExitItem());
    }

}

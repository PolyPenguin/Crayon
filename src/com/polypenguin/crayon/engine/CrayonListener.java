package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;
import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.action.PassiveChangeAction;
import com.polypenguin.crayon.engine.event.CrayonInventoryEvent;
import com.polypenguin.crayon.engine.event.CrayonItemEvent;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.NullSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;
import com.polypenguin.crayon.engine.geometry.selection.VectorSelection;
import com.polypenguin.crayon.engine.manager.RenderManager;
import com.polypenguin.crayon.engine.operation.CopyOperation;
import com.polypenguin.crayon.engine.operation.FillOperation;
import com.polypenguin.crayon.engine.operation.PasteOperation;
import com.polypenguin.crayon.engine.utils.InterfaceUtils;
import com.polypenguin.crayon.engine.utils.ItemUtils;
import com.polypenguin.crayon.engine.utils.VectorUtils;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class CrayonListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInteract(CrayonItemEvent event) {
        CrayonPlayer player = event.getPlayer();
        Action action = event.getAction();
        Vector target = event.getTarget();

        if (event.getItem().equals(ItemUtils.getMenuItem())) {
            CrayonInterface.openInventory(event.getPlayer(), InterfaceUtils.getCrayonMenu());
        }

        if (event.getItem().equals(ItemUtils.getWandItem())) {
            if (player.getSelectionMode() == CrayonPlayer.SelectionMode.NA) {
                CrayonInterface.openInventory(player, InterfaceUtils.getPositionMenu());

                return;
            }

            if (action == Action.LEFT_CLICK_BLOCK) {
                if (player.getSelectionMode() == CrayonPlayer.SelectionMode.SINGLE) {
                    int i = player.getSelectionManager().update(target);
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Position " + i + " set at " + VectorUtils.toString(target));
                } else if (player.getSelectionMode() == CrayonPlayer.SelectionMode.DOUBLE) {
                    int i = player.getSelectionManager().update(target);
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Position " + i + " set at " + VectorUtils.toString(target));
                }
            } else {
                CrayonInterface.openInventory(player, InterfaceUtils.getWandMenu());
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInventory(CrayonInventoryEvent event) {
        CrayonPlayer player = event.getPlayer();
        Inventory inventory = event.getInventory();
        int slot = event.getSlot();

        if (inventory.getName().contains("Menu")) {
            if (inventory.getName().contains("Main")) {
                if (slot == 10) {
                    if (player.getPlayer().getInventory().firstEmpty() != -1) {
                        player.getPlayer().getInventory().addItem(ItemUtils.getWandItem());
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "You received the Crayon Wand");
                    } else {
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Seems like you inventory is full");
                    }
                } else if (slot == 11) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getPositionMenu());
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Wand")) {
                if (slot == 10) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getPositionMenu());
                } else if (slot == 11) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getGenerateMenu());
                }  else if (slot == 12) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getOperationsMenu());
                }  else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Generate")) {
                if (slot == 10) {

                } else if (slot == 11) {

                } else if (slot == 12) {
                    Selection selection = player.getSelectionManager().getSelection();

                    if ((selection == null) || (selection instanceof NullSelection)) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a selection first");
                    }

                    ArrayList<CrayonState> states = new ArrayList<>();

                    for (Vector vector : selection.getVectors(true)) {
                        states.add(new CrayonState(
                                vector,
                                player.getPlayer().getWorld().getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).getType(),
                                null
                        ));
                    }

                    player.setOperation(new FillOperation(player, states));

                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getStone());
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Operations")) {
                if (slot == 10) {
                    Selection selection = player.getSelectionManager().getSelection();

                    if ((selection == null) || (selection instanceof NullSelection)) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a selection first");
                    }

                    //The origin will always be the minimum of the selection!
                    ArrayList<Vector> offsets = new ArrayList<>();
                    Vector origin = selection.getNativeMinimum();

                    for (Vector vector : selection.getVectors(true)) {
                        offsets.add(VectorUtils.getOffset(selection.getNativeMinimum(), vector));
                    }

                    PassiveChangeAction action = RenderManager.handle(new CopyOperation(player, offsets, origin));

                    player.getActionManager().add(action);
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Your selection has been copied to your Clipboard");
                    player.getPlayer().closeInventory();
                } else if (slot == 11) {
                    if (player.getClipboard().getPreStates() == null) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please copy a selection first");
                    }

                    Selection selection = player.getSelectionManager().getSelection();

                    if (selection == null) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a selection first");
                    } else if (!(selection instanceof VectorSelection)) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a single-point selection first");
                    }

                    VectorSelection vectorSelection = (VectorSelection) selection;
                    BlockChangeAction action = RenderManager.render(
                            new PasteOperation(player, player.getClipboard().getPreStates(), vectorSelection.getNativeMinimum())
                    );

                    player.getActionManager().add(action);
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Your selection has been pasted");
                    player.getPlayer().closeInventory();
                } else if (slot == 12) {

                } else if (slot == 13) {

                } else if (slot == 14) {

                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        } else if (inventory.getName().contains("Selection")) {
            if (inventory.getName().contains("Mode")) {
                if (slot == 10) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.SINGLE);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Single!");
                } else if (slot == 11) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.DOUBLE);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Double!");
                } else if (slot == 12) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.MULTI);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Multi!");
                } else if (slot == 13) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.NA);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been reset!");
                } else if (slot == 14) {
                    player.getSelectionManager().update(null);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selected positions has been reset!");
                } else if (slot == 15) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.NA);
                    player.getSelectionManager().update(null);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode and selected positions have been reset!");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        }

        //From here, Crayon will assume this is the last dialog!
        //Thus it will ask for a specific amount of parameters!
        else if (inventory.getName().contains("Materials")) {
            if (inventory.getName().contains("Stone")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getRandom());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getNatural());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Natural")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getStone());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getWood());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Wooden")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getNatural());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSlab());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Slab & Stair")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getWood());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcOne());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Colored Materials 1")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSlab());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcTwo());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Colored Materials 2")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcOne());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcThree());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Colored Materials 3")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcTwo());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSea());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Sea")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcThree());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getRandom());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Random")) {
                if ((slot < 45) && (!inventory.getItem(slot).getType().equals(Material.AIR))) {
                    RenderManager.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSea());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getStone());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            }
        }
    }
}

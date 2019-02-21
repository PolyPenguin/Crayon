package com.polypenguin.pencil.engine;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.core.gui.PencilInterface;
import com.polypenguin.pencil.engine.action.BlockChangeAction;
import com.polypenguin.pencil.engine.action.PassiveChangeAction;
import com.polypenguin.pencil.engine.event.PencilInventoryEvent;
import com.polypenguin.pencil.engine.event.PencilItemEvent;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.geometry.blueprint.PencilBlueprintFile;
import com.polypenguin.pencil.engine.geometry.selection.NullSelection;
import com.polypenguin.pencil.engine.geometry.selection.Selection;
import com.polypenguin.pencil.engine.geometry.selection.VectorSelection;
import com.polypenguin.pencil.engine.render.Renderer;
import com.polypenguin.pencil.engine.operation.*;
import com.polypenguin.pencil.engine.utils.InterfaceUtils;
import com.polypenguin.pencil.engine.utils.ItemUtils;
import com.polypenguin.pencil.engine.utils.StringUtils;
import com.polypenguin.pencil.engine.utils.VectorUtils;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilPreState;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilState;
import com.polypenguin.pencil.engine.utils.miscellaneous.ShapeType;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Listener that handles all Pencil-related events.
 */
public class PencilListener implements Listener {

    private ArrayList<PencilPlayer> hasBlueprint = new ArrayList<>();

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInteract(PencilItemEvent event) {
        PencilPlayer player = event.getPlayer();
        Action action = event.getAction();
        Vector target = event.getTarget();

        if (event.getItem().equals(ItemUtils.getMenuItem())) {
            PencilInterface.openInventory(event.getPlayer(), InterfaceUtils.getCrayonMenu());
        }

        if (event.getItem().equals(ItemUtils.getWandItem())) {
            if (player.getSelectionMode() == PencilPlayer.SelectionMode.NA) {
                PencilInterface.openInventory(player, InterfaceUtils.getPositionMenu());

                return;
            }

            if (action == Action.LEFT_CLICK_BLOCK) {
                if (player.getSelectionMode() == PencilPlayer.SelectionMode.SINGLE) {
                    int i = player.getSelectionManager().update(target);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Position " + i + " set at " + VectorUtils.toString(target));
                } else if (player.getSelectionMode() == PencilPlayer.SelectionMode.DOUBLE) {
                    int i = player.getSelectionManager().update(target);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Position " + i + " set at " + VectorUtils.toString(target));
                }
            } else {
                PencilInterface.openInventory(player, InterfaceUtils.getWandMenu());
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        PencilPlayer player = Pencil.getPlayerService().getPlayer(event.getPlayer());

        if (hasBlueprint.contains(player)) {
            hasBlueprint.remove(player);

            event.setCancelled(true);

            String name = StringUtils.getFirstString(event.getMessage());
            PencilBlueprintFile.createBlueprint(name, player, false);
        }
    }

    //TODO: Fix shape generation
    @EventHandler (priority = EventPriority.HIGHEST)
    public void onInventory(PencilInventoryEvent event) {
        PencilPlayer player = event.getPlayer();
        Inventory inventory = event.getInventory();
        int slot = event.getSlot();

        if (inventory.getName().contains("Menu")) {
            if (inventory.getName().contains("Main")) {
                if (slot == 10) {
                    if (player.getPlayer().getInventory().firstEmpty() != -1) {
                        player.getPlayer().getInventory().addItem(ItemUtils.getWandItem());
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "You received the Pencil Wand");
                    } else {
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "Seems like you inventory is full");
                    }
                } else if (slot == 11) {
                    PencilInterface.openInventory(player, InterfaceUtils.getPositionMenu());
                } else if (slot == 12) {
                    PencilInterface.openInventory(player, InterfaceUtils.getModesMenu());
                } else if (slot == 13) {
                    PencilInterface.openInventory(player, InterfaceUtils.getUtilitiesMenu());
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Wand")) {
                if (slot == 10) {
                    PencilInterface.openInventory(player, InterfaceUtils.getPositionMenu());
                } else if (slot == 11) {
                    PencilInterface.openInventory(player, InterfaceUtils.getGenerateMenu());
                }  else if (slot == 12) {
                    PencilInterface.openInventory(player, InterfaceUtils.getOperationsMenu());
                }  else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Generate")) {
                if (!Pencil.getPermissionService().hasPermission(player, Pencil.getPermissionService().getGenerationPermission())) {
                    player.getPlayer().closeInventory();
                }

                if (slot == 10) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidShapesMenu());
                } else if (slot == 11) {
                    PencilInterface.openInventory(player, InterfaceUtils.getSphericalShapesMenu());
                } else if (slot == 12) {
                    Selection selection = player.getSelectionManager().getSelection();

                    if ((selection == null) || (selection instanceof NullSelection)) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "Please make a selection first");
                    }

                    ArrayList<PencilState> states = new ArrayList<>();

                    for (Vector vector : selection.getVectors(true)) {
                        states.add(new PencilState(
                                vector,
                                player.getPlayer().getWorld().getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).getType(),
                                null
                        ));
                    }

                    player.setOperation(new FillOperation(player, states));

                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getStone());
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Operations")) {
                if (!Pencil.getPermissionService().hasPermission(player, Pencil.getPermissionService().getOperationPermission())) {
                    player.getPlayer().closeInventory();
                }

                if (slot == 10) {
                    Selection selection = player.getSelectionManager().getSelection();

                    if ((selection == null) || (selection instanceof NullSelection)) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "Please make a selection first");
                    }

                    //The origin will always be the minimum of the selection!
                    ArrayList<PencilPreState> preStates = new ArrayList<>();
                    ArrayList<Vector> positions = selection.getVectors(true);
                    Vector origin = selection.getNativeMinimum();

                    for (Vector vector : positions) {
                        Material material = player.getPlayer().getWorld().getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).getType();

                        preStates.add(new PencilPreState(
                                VectorUtils.getOffset(selection.getNativeMinimum(), vector),
                                material
                        ));
                    }

                    PassiveChangeAction action = Renderer.handle(new CopyOperation(player, selection, preStates, origin));

                    player.getActionManager().add(action);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your selection has been copied to your Clipboard");
                    player.getPlayer().closeInventory();
                } else if (slot == 11) {
                    if (player.getClipboard().getPreStates() == null) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "Please copy a selection first");
                    }

                    BlockChangeAction action = Renderer.render(
                            new PasteOperation(player, player.getClipboard().getPreStates(), getOrigin(player))
                    );

                    player.getActionManager().add(action);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your selection has been pasted");
                    player.getPlayer().closeInventory();
                } else if (slot == 12) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 13) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 14) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Please type the name of the file you want to save your blueprint to");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("History")) {
                if (slot == 10) {
                    if (!Pencil.getPermissionService().hasPermission(player, Pencil.getPermissionService().getUndoPermission())) {
                        player.getPlayer().closeInventory();
                    }

                    if ((!player.getActionManager().hasAction()) || (player.getActionManager().getLast() == null)) {
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "No action to redo");
                        player.getPlayer().closeInventory();

                        return;
                    }

                    if (player.getActionManager().getLast() != null) {
                        player.getActionManager().getLast().redo();
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Last action has been redone");
                        player.getPlayer().closeInventory();
                    } else {
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "No actions left to redo");
                        player.getPlayer().closeInventory();
                    }
                } else if (slot == 11) {
                    if (!Pencil.getPermissionService().hasPermission(player, Pencil.getPermissionService().getUndoPermission())) {
                        player.getPlayer().closeInventory();
                    }

                    if ((!player.getActionManager().hasAction()) || (player.getActionManager().getLast() == null)) {
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "No action to undo");
                        player.getPlayer().closeInventory();

                        return;
                    }

                    if (player.getActionManager().getLast() != null && player.getActionManager().getLast().canUndo()) {
                        player.getActionManager().getLast().undo();
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Last action has been undone");
                        player.getPlayer().closeInventory();
                    } else {
                        player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "No actions left to undo");
                        player.getPlayer().closeInventory();
                    }
                } else if (slot == 13) {
                    PencilInterface.openInventory(player, InterfaceUtils.getHistoryTimeline(player));
                } else if (slot == 14) {
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "History has been cleared! (Cleared " + player.getActionManager().getSize() + " actions)");
                    player.getActionManager().flush();
                    player.getPlayer().closeInventory();
                } else if (slot == 15) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        } else if (inventory.getName().contains("Clipboard")) {
            if (slot == 12) {
                PencilInterface.openInventory(player, InterfaceUtils.getOperationsMenu());
            } else if (slot == 15) {
                player.getClipboard().flush();
            } else if (slot == 16) {
                player.getPlayer().closeInventory();
            }
        } else if (inventory.getName().contains("Player")) {
            if (inventory.getName().contains("Modes")) {
                if (slot == 10) {
                    player.setPlayerMode(PencilPlayer.PlayerMode.GENERAL);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your mode has been set to General");
                    player.getPlayer().closeInventory();
                } else if (slot == 11) {
                    player.setPlayerMode(PencilPlayer.PlayerMode.SCULPTING);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your mode has been set to Sculpting");
                    player.getPlayer().closeInventory();
                } else if (slot == 12) {
                    player.setPlayerMode(PencilPlayer.PlayerMode.TEXTURING);
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Your mode has been set to Texturing");
                    player.getPlayer().closeInventory();
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        } else if (inventory.getName().contains("Utilities")) {
            if (slot == 10) {
                 PencilInterface.openInventory(player, InterfaceUtils.getHistoryMenu());
            } else if (slot == 11) {
                PencilInterface.openInventory(player, InterfaceUtils.getClipboardView(player));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();
            }
        } else if (inventory.getName().contains("Selection")) {
            if (inventory.getName().contains("Mode")) {
                if (slot == 10) {
                    player.setSelectionMode(PencilPlayer.SelectionMode.SINGLE);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Single");
                } else if (slot == 11) {
                    player.setSelectionMode(PencilPlayer.SelectionMode.DOUBLE);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Double");
                } else if (slot == 12) {
                    player.setSelectionMode(PencilPlayer.SelectionMode.MULTI);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Multi");
                } else if (slot == 13) {
                    player.setSelectionMode(PencilPlayer.SelectionMode.NA);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Selection mode has been reset");
                } else if (slot == 14) {
                    player.getSelectionManager().update(null);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Selected positions has been reset");
                } else if (slot == 15) {
                    player.setSelectionMode(PencilPlayer.SelectionMode.NA);
                    player.getSelectionManager().update(null);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Selection mode and selected positions have been reset");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        } else if (inventory.getName().contains("Shapes")) {
            if (inventory.getName().contains("Cuboid")) {
                if (slot == 10) {
                    player.setOperation(new ShapeOperation(player, ShapeType.CUBE, getOrigin(player)));

                    PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu(1));
                } else if (slot == 11) {
                    player.setOperation(new ShapeOperation(player, ShapeType.CUBOID, getOrigin(player)));

                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(1, 1, 1));
                } else if (slot == 12) {
                    player.setOperation(new ShapeOperation(player, ShapeType.PYRAMID, getOrigin(player)));

                    PencilInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu(1));
                } else if (slot == 13) {
                    player.setOperation(new ShapeOperation(player, ShapeType.PRISM, getOrigin(player)));
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Spherical")) {
                if (slot == 10) {
                    player.setOperation(new ShapeOperation(player, ShapeType.SPHERE, getOrigin(player)));

                    PencilInterface.openInventory(player, InterfaceUtils.getSphereDimensionMenu(1));
                } else if (slot == 11) {
                    player.setOperation(new ShapeOperation(player, ShapeType.ELLIPSOID, getOrigin(player)));

                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(1, 1, 1));
                } else if (slot == 12) {
                    player.setOperation(new ShapeOperation(player, ShapeType.CYLINDER, getOrigin(player)));

                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(1, 1, 1));
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        }

        else if (inventory.getName().contains("Timeline")) {
            if (slot < 45) {
                if (ItemUtils.hasActionAssigned(inventory.getItem(slot))) {
                    PencilInterface.openInventory(player, InterfaceUtils.getActionInventory(player, inventory.getItem(slot)));
                }
            }

            if (slot == 49) {
                player.getPlayer().closeInventory();
            }
        } else if (inventory.getName().contains("Action")) {
            int ID = -1;

            for (String str : inventory.getItem(13).getItemMeta().getLore()) {
                if (str.contains("ID")) {
                    ID = StringUtils.extractNumber(str);
                }
            }

            if (slot == 10) {
                if (!Pencil.getPermissionService().hasPermission(player, Pencil.getPermissionService().getRedoPermission())) {
                    player.getPlayer().closeInventory();
                }

                player.getActionManager().get(ID).redo();
                player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Action has been redone");
            } else if (slot == 11) {
                if (!Pencil.getPermissionService().hasPermission(player, Pencil.getPermissionService().getUndoPermission())) {
                    player.getPlayer().closeInventory();
                }

                if (player.getActionManager().get(ID).canUndo()) {
                    player.getActionManager().get(ID).undo();
                    player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Action has been undone");
                }
            } else if(slot == 15) {
                PencilInterface.openInventory(player, InterfaceUtils.getHistoryTimeline(player));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();
            }
        }

        else if (inventory.getName().contains("Angle")) {
            //TODO: Recode

            player.getPlayer().closeInventory();
            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
        }

        else if (inventory.getName().contains("Cube") && inventory.getName().contains("Scale")) {
            if (slot == 13) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale + 1)));
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                if (scale == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale)));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale - 1)));
                }
            } else if (slot == 26) {
                if (player.getOperation() instanceof ShapeOperation) {
                    int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                    ShapeOperation shapeOperation = (ShapeOperation) player.getOperation();

                    shapeOperation.getParameter().setParamOne(scale - 1);
                    shapeOperation.getParameter().setParamTwo(scale - 1);
                    shapeOperation.getParameter().setParamThree(scale - 1);

                    shapeOperation.finalizeOperation();
                }
            } else if (slot == 49) {
                player.getPlayer().closeInventory();

                //TODO: Delete all previous settings for the operation
            }
        } else if (inventory.getName().contains("Cuboid") && inventory.getName().contains("Scale")) {
            if (slot == 10) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu((scaleX + 1), scaleY, scaleZ));
            } else if (slot == 13) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, (scaleY + 1), scaleZ));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, (scaleZ + 1)));
            } else if (slot == 28) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleX == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu((scaleX - 1), scaleY, scaleZ));
                }
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleY == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, (scaleY - 1), scaleZ));
                }
            } else if (slot == 34) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleZ == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, (scaleZ - 1)));
                }
            } else if (slot == 26) {
                if (player.getOperation() instanceof ShapeOperation) {
                    int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                    int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                    int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                    ShapeOperation shapeOperation = (ShapeOperation) player.getOperation();

                    shapeOperation.getParameter().setParamOne(scaleX - 1);
                    shapeOperation.getParameter().setParamTwo(scaleY - 1);
                    shapeOperation.getParameter().setParamThree(scaleZ - 1);

                    shapeOperation.finalizeOperation();
                } else {
                    player.getPlayer().closeInventory();

                    //TODO: Delete all previous settings for the operation
                }
            } else if (slot == 49) {
                player.getPlayer().closeInventory();

                //TODO: Delete all previous settings for the operation
            }
        } else if (inventory.getName().contains("Pyramid") && inventory.getName().contains("Scale")) {
            if (slot == 13) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu((scale + 1)));
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                if (scale == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu((scale)));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu((scale - 1)));
                }
            } else if (slot == 26) {
                if (player.getOperation() instanceof ShapeOperation) {
                    int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                    ShapeOperation shapeOperation = (ShapeOperation) player.getOperation();

                    shapeOperation.getParameter().setParamOne(scale);
                    shapeOperation.getParameter().setParamTwo(scale);
                    shapeOperation.getParameter().setParamThree(scale);

                    shapeOperation.finalizeOperation();
                }
            } else if (slot == 49) {
                player.getPlayer().closeInventory();

                //TODO: Delete all previous settings for the operation
            }
        } else if (inventory.getName().contains("Sphere") && inventory.getName().contains("Scale")) {
            if (slot == 13) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale + 1)));
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                if (scale == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale)));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale - 1)));
                }
            } else if (slot == 26) {
                if (player.getOperation() instanceof ShapeOperation) {
                    int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                    ShapeOperation shapeOperation = (ShapeOperation) player.getOperation();

                    shapeOperation.getParameter().setParamOne(scale - 1);
                    shapeOperation.getParameter().setParamTwo(scale - 1);
                    shapeOperation.getParameter().setParamThree(scale - 1);

                    shapeOperation.finalizeOperation();
                }
            } else if (slot == 49) {
                player.getPlayer().closeInventory();

                //TODO: Delete all previous settings for the operation
            }
        } else if (inventory.getName().contains("Ellipsoid") && inventory.getName().contains("Scale")) {
            if (slot == 10) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu((scaleX + 1), scaleY, scaleZ));
            } else if (slot == 13) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, (scaleY + 1), scaleZ));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, (scaleZ + 1)));
            } else if (slot == 28) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleX == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu((scaleX - 1), scaleY, scaleZ));
                }
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleY == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, (scaleY - 1), scaleZ));
                }
            } else if (slot == 34) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleZ == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, (scaleZ - 1)));
                }
            } else if (slot == 26) {
                if (player.getOperation() instanceof ShapeOperation) {
                    int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                    int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                    int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                    ShapeOperation shapeOperation = (ShapeOperation) player.getOperation();

                    shapeOperation.getParameter().setParamOne(scaleX - 1);
                    shapeOperation.getParameter().setParamTwo(scaleY - 1);
                    shapeOperation.getParameter().setParamThree(scaleZ - 1);

                    shapeOperation.finalizeOperation();
                } else {
                    player.getPlayer().closeInventory();

                    //TODO: Delete all previous settings for the operation
                }
            } else if (slot == 49) {
                player.getPlayer().closeInventory();

                //TODO: Delete all previous settings for the operation
            }
        } else if (inventory.getName().contains("Cylinder") && inventory.getName().contains("Scale")) {
            if (slot == 10) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu((scaleX + 1), scaleY, scaleZ));
            } else if (slot == 13) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, (scaleY + 1), scaleZ));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, (scaleZ + 1)));
            } else if (slot == 28) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleX == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu((scaleX - 1), scaleY, scaleZ));
                }
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleY == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, (scaleY - 1), scaleZ));
                }
            } else if (slot == 34) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleZ == 1) {
                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    PencilInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, (scaleZ - 1)));
                }
            } else if (slot == 26) {
                if (player.getOperation() instanceof ShapeOperation) {
                    int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                    int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                    int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                    ShapeOperation shapeOperation = (ShapeOperation) player.getOperation();

                    shapeOperation.getParameter().setParamOne(scaleX - 1);
                    shapeOperation.getParameter().setParamTwo(scaleY - 1);
                    shapeOperation.getParameter().setParamThree(scaleZ - 1);

                    shapeOperation.finalizeOperation();
                } else {
                    player.getPlayer().closeInventory();

                    //TODO: Delete all previous settings for the operation
                }
            } else if (slot == 49) {
                player.getPlayer().closeInventory();

                //TODO: Delete all previous settings for the operation
            }
        }

        //From here, Pencil will assume this is the last dialog!
        //Thus it will ask for a specific amount of parameters!
        else if (inventory.getName().contains("Materials")) {
            if (inventory.getName().contains("Stone")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getRandom());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getNatural());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Natural")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getStone());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getWood());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Wooden")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getNatural());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getSlab());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Slab & Stair")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getWood());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getcOne());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Colored Materials 1")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getSlab());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getcTwo());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Colored Materials 2")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getcOne());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getcThree());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Colored Materials 3")) {
                if (slot == 37) {
                    Renderer.finalize(player, Material.GRAY_STAINED_GLASS_PANE);

                    player.getPlayer().closeInventory();
                } else if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getcTwo());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getSea());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Sea")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    Renderer.finalize(player, inventory.getItem(slot).getType());

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getcThree());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getRandom());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Random")) {
                if ((slot < 45)
                        && (!inventory.getItem(slot).getType().equals(Material.AIR))
                        && (!inventory.getItem(slot).getType().equals(Material.GRAY_STAINED_GLASS_PANE))
                        ) {
                    if (inventory.getItem(slot).getType().equals(Material.COBWEB)) {
                        Renderer.finalize(player, Material.AIR);
                    } else {
                        Renderer.finalize(player, inventory.getItem(slot).getType());
                    }

                    player.getPlayer().closeInventory();
                } else if (slot == 45) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getSea());
                } else if (slot == 53) {
                    PencilInterface.openInventory(player, Pencil.getMaterialSet().getStone());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            }
        }
    }

    private Vector getOrigin(PencilPlayer player) {
        Selection selection = player.getSelectionManager().getSelection();

        if (selection == null) {
            player.getPlayer().closeInventory();
            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "Please make a selection first");
        } else if (!(selection instanceof VectorSelection)) {
            player.getPlayer().closeInventory();
            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.RED + "Please make a single-point selection first");
        }

        VectorSelection vectorSelection = (VectorSelection) selection;
        Vector origin = vectorSelection.getNativeMinimum();

        return origin;
    }
}

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
import com.polypenguin.crayon.engine.render.Renderer;
import com.polypenguin.crayon.engine.operation.*;
import com.polypenguin.crayon.engine.utils.InterfaceUtils;
import com.polypenguin.crayon.engine.utils.ItemUtils;
import com.polypenguin.crayon.engine.utils.StringUtils;
import com.polypenguin.crayon.engine.utils.VectorUtils;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonPreState;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;
import com.polypenguin.crayon.engine.utils.miscellaneous.ShapeType;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Listener that handles all Crayon-related events.
 */
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

    //TODO: Fix shape generation
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
                } else if (slot == 12) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getHistoryMenu());
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
                if (!Crayon.getPermissionService().hasPermission(player, Crayon.getPermissionService().getGenerationPermission())) {
                    player.getPlayer().closeInventory();
                }

                if (slot == 10) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidShapesMenu());
                } else if (slot == 11) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getSphericalShapesMenu());
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
                if (!Crayon.getPermissionService().hasPermission(player, Crayon.getPermissionService().getOperationPermission())) {
                    player.getPlayer().closeInventory();
                }

                if (slot == 10) {
                    Selection selection = player.getSelectionManager().getSelection();

                    if ((selection == null) || (selection instanceof NullSelection)) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a selection first");
                    }

                    //The origin will always be the minimum of the selection!
                    ArrayList<CrayonPreState> preStates = new ArrayList<>();
                    ArrayList<Vector> positions = selection.getVectors(true);
                    Vector origin = selection.getNativeMinimum();

                    for (Vector vector : positions) {
                        Material material = player.getPlayer().getWorld().getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).getType();

                        preStates.add(new CrayonPreState(
                                VectorUtils.getOffset(selection.getNativeMinimum(), vector),
                                material
                        ));
                    }

                    PassiveChangeAction action = Renderer.handle(new CopyOperation(player, preStates, origin));

                    player.getActionManager().add(action);
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Your selection has been copied to your Clipboard");
                    player.getPlayer().closeInventory();
                } else if (slot == 11) {
                    if (player.getClipboard().getPreStates() == null) {
                        player.getPlayer().closeInventory();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please copy a selection first");
                    }

                    BlockChangeAction action = Renderer.render(
                            new PasteOperation(player, player.getClipboard().getPreStates(), getOrigin(player))
                    );

                    player.getActionManager().add(action);
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Your selection has been pasted");
                    player.getPlayer().closeInventory();
                } else if (slot == 12) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 13) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 14) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("History")) {
                if (slot == 10) {
                    if (!Crayon.getPermissionService().hasPermission(player, Crayon.getPermissionService().getUndoPermission())) {
                        player.getPlayer().closeInventory();
                    }

                    if ((!player.getActionManager().hasAction()) || (player.getActionManager().getLast() == null)) {
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "No action to redo");
                        player.getPlayer().closeInventory();

                        return;
                    }

                    if (player.getActionManager().getLast() != null) {
                        player.getActionManager().getLast().redo();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Last action has been redone");
                        player.getPlayer().closeInventory();
                    } else {
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "No actions left to redo");
                        player.getPlayer().closeInventory();
                    }
                } else if (slot == 11) {
                    if (!Crayon.getPermissionService().hasPermission(player, Crayon.getPermissionService().getUndoPermission())) {
                        player.getPlayer().closeInventory();
                    }

                    if ((!player.getActionManager().hasAction()) || (player.getActionManager().getLast() == null)) {
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "No action to undo");
                        player.getPlayer().closeInventory();

                        return;
                    }

                    if (player.getActionManager().getLast() != null && player.getActionManager().getLast().canUndo()) {
                        player.getActionManager().getLast().undo();
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Last action has been undone");
                        player.getPlayer().closeInventory();
                    } else {
                        player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "No actions left to undo");
                        player.getPlayer().closeInventory();
                    }
                } else if (slot == 13) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getHistoryTimeline(player));
                } else if (slot == 14) {
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "History has been cleared! (Cleared " + player.getActionManager().getSize() + " actions)");
                    player.getActionManager().flush();
                    player.getPlayer().closeInventory();
                } else if (slot == 15) {
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        } else if (inventory.getName().contains("Selection")) {
            if (inventory.getName().contains("Mode")) {
                if (slot == 10) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.SINGLE);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Single");
                } else if (slot == 11) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.DOUBLE);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Double");
                } else if (slot == 12) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.MULTI);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been set to Multi");
                } else if (slot == 13) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.NA);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode has been reset");
                } else if (slot == 14) {
                    player.getSelectionManager().update(null);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selected positions has been reset");
                } else if (slot == 15) {
                    player.setSelectionMode(CrayonPlayer.SelectionMode.NA);
                    player.getSelectionManager().update(null);
                    player.getPlayer().closeInventory();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Selection mode and selected positions have been reset");
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        } else if (inventory.getName().contains("Shapes")) {
            if (inventory.getName().contains("Cuboid")) {
                if (slot == 10) {
                    player.setOperation(new ShapeOperation(player, ShapeType.CUBE, getOrigin(player)));

                    CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu(1));
                } else if (slot == 11) {
                    player.setOperation(new ShapeOperation(player, ShapeType.CUBOID, getOrigin(player)));

                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(1, 1, 1));
                } else if (slot == 12) {
                    player.setOperation(new ShapeOperation(player, ShapeType.PYRAMID, getOrigin(player)));
                } else if (slot == 13) {
                    player.setOperation(new ShapeOperation(player, ShapeType.PRISM, getOrigin(player)));
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            } else if (inventory.getName().contains("Spherical")) {
                if (slot == 10) {
                    player.setOperation(new ShapeOperation(player, ShapeType.SPHERE, getOrigin(player)));

                    CrayonInterface.openInventory(player, InterfaceUtils.getSphereDimensionMenu(1));
                } else if (slot == 11) {
                    player.setOperation(new ShapeOperation(player, ShapeType.ELLIPSOID, getOrigin(player)));

                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(1, 1, 1));
                } else if (slot == 12) {
                    player.setOperation(new ShapeOperation(player, ShapeType.CYLINDER, getOrigin(player)));

                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(1, 1, 1));
                } else if (slot == 16) {
                    player.getPlayer().closeInventory();
                }
            }
        }

        else if (inventory.getName().contains("Timeline")) {
            if (slot < 45) {
                if (ItemUtils.hasActionAssigned(inventory.getItem(slot))) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getActionInventory(player, inventory.getItem(slot)));
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
                if (!Crayon.getPermissionService().hasPermission(player, Crayon.getPermissionService().getRedoPermission())) {
                    player.getPlayer().closeInventory();
                }

                player.getActionManager().get(ID).redo();
                player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Action has been redone");
            } else if (slot == 11) {
                if (!Crayon.getPermissionService().hasPermission(player, Crayon.getPermissionService().getUndoPermission())) {
                    player.getPlayer().closeInventory();
                }

                if (player.getActionManager().get(ID).canUndo()) {
                    player.getActionManager().get(ID).undo();
                    player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Action has been undone");
                }
            } else if(slot == 15) {
                CrayonInterface.openInventory(player, InterfaceUtils.getHistoryTimeline(player));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();
            }
        }

        else if (inventory.getName().contains("Angle")) {
            //TODO: Recode

            player.getPlayer().closeInventory();
            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "This feature will be available in a future update");
        }

        else if (inventory.getName().contains("Cube") && inventory.getName().contains("Scale")) {
            if (slot == 13) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale + 1)));
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                if (scale == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale)));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale - 1)));
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
        } else if (inventory.getName().contains("Pyramid") && inventory.getName().contains("Scale")) {
            if (slot == 13) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu((scale + 1)));
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                if (scale == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu((scale)));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getPyramidDimensionMenu((scale - 1)));
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

                CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu((scaleX + 1), scaleY, scaleZ));
            } else if (slot == 13) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, (scaleY + 1), scaleZ));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, (scaleZ + 1)));
            } else if (slot == 28) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleX == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu((scaleX - 1), scaleY, scaleZ));
                }
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleY == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, (scaleY - 1), scaleZ));
                }
            } else if (slot == 34) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleZ == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCuboidDimensionMenu(scaleX, scaleY, (scaleZ - 1)));
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
        } else if (inventory.getName().contains("Sphere") && inventory.getName().contains("Scale")) {
            if (slot == 13) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale + 1)));
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scale = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());

                if (scale == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale)));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCubeDimensionMenu((scale - 1)));
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

                CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu((scaleX + 1), scaleY, scaleZ));
            } else if (slot == 13) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, (scaleY + 1), scaleZ));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, (scaleZ + 1)));
            } else if (slot == 28) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleX == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu((scaleX - 1), scaleY, scaleZ));
                }
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleY == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, (scaleY - 1), scaleZ));
                }
            } else if (slot == 34) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleZ == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getEllipsoidDimensionMenu(scaleX, scaleY, (scaleZ - 1)));
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

                CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu((scaleX + 1), scaleY, scaleZ));
            } else if (slot == 13) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, (scaleY + 1), scaleZ));
            } else if (slot == 16) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, (scaleZ + 1)));
            } else if (slot == 28) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleX == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu((scaleX - 1), scaleY, scaleZ));
                }
            } else if (slot == 31) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleY == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, (scaleY - 1), scaleZ));
                }
            } else if (slot == 34) {
                player.getPlayer().closeInventory();

                int scaleX = StringUtils.extractNumber(inventory.getItem(19).getItemMeta().getDisplayName());
                int scaleY = StringUtils.extractNumber(inventory.getItem(22).getItemMeta().getDisplayName());
                int scaleZ = StringUtils.extractNumber(inventory.getItem(25).getItemMeta().getDisplayName());

                if (scaleZ == 1) {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, scaleZ));
                } else {
                    CrayonInterface.openInventory(player, InterfaceUtils.getCylinderDimensionMenu(scaleX, scaleY, (scaleZ - 1)));
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

        //From here, Crayon will assume this is the last dialog!
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getRandom());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getNatural());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getStone());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getWood());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getNatural());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSlab());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getWood());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcOne());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSlab());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcTwo());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcOne());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcThree());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcTwo());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSea());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getcThree());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getRandom());
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
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getSea());
                } else if (slot == 53) {
                    CrayonInterface.openInventory(player, Crayon.getMaterialSet().getStone());
                } else if (slot == 49) {
                    player.getPlayer().closeInventory();
                }
            }
        }
    }

    private Vector getOrigin(CrayonPlayer player) {
        Selection selection = player.getSelectionManager().getSelection();

        if (selection == null) {
            player.getPlayer().closeInventory();
            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a selection first");
        } else if (!(selection instanceof VectorSelection)) {
            player.getPlayer().closeInventory();
            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.RED + "Please make a single-point selection first");
        }

        VectorSelection vectorSelection = (VectorSelection) selection;
        Vector origin = vectorSelection.getNativeMinimum();

        return origin;
    }
}

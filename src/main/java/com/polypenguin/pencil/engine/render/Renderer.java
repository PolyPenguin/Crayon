package com.polypenguin.pencil.engine.render;

import com.polypenguin.pencil.Pencil;
import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.action.BlockChangeAction;
import com.polypenguin.pencil.engine.action.PassiveChangeAction;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.operation.*;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilPreState;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilState;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Manager that will manager operations
 * and execute them accordingly, returning
 * actions for each player.
 */
public class Renderer {

    /**
     * Renders a StateOperation into the world.
     *
     * @param operation The operation that has to be rendered.
     * @return A BlockChangeAction to add to the performer's ActionManager.
     *
     * @see StateOperation
     * @see BlockChangeAction
     */
    public static BlockChangeAction render(StateOperation operation) {
        PencilPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof FillOperation) {
            FillOperation fillOperation = (FillOperation) operation;

            for (PencilState state : fillOperation.getStates()) {
                Vector vector = state.getVector();

                world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getUpdated());
            }

            player.getPlayer().sendMessage(Pencil.getPrefix() + ChatColor.GREEN + "Filled " + fillOperation.getStates().size() + " blocks");

            return new BlockChangeAction(
                    player,
                    ((FillOperation) operation).getStates(),
                    player.getActionManager().getNextID());
        } else if (operation instanceof PasteOperation) {
            PasteOperation pasteOperation = (PasteOperation) operation;
            Vector target = pasteOperation.getTarget();
            ArrayList<PencilState> states = new ArrayList<>();

            for (PencilPreState state : pasteOperation.getTransformations()) {
                Vector offset = new Vector(
                        (target.getBlockX() + state.getOffset().getBlockX()),
                        (target.getBlockY() + state.getOffset().getBlockY()),
                        (target.getBlockZ() + state.getOffset().getBlockZ())
                );

                Material material = state.getMaterial();

                states.add(new PencilState(offset,
                        world.getBlockAt(offset.getBlockX(), offset.getBlockY(), offset.getBlockZ()).getType(),
                        material)
                );
            }

            return render(new FillOperation(player, states));
        }

        return null;
    }

    /**
     * Handles operations that don't have to be rendered instantly.
     *
     * @param operation The operation that has to be handled.
     * @return A PassiveChangeAction to add to the performer's ActionManager.
     *
     * @see TransformOperation
     * @see PassiveChangeAction
     */
    public static PassiveChangeAction handle(TransformOperation operation) {
        PencilPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof CopyOperation) {
            CopyOperation copyOperation = (CopyOperation) operation;

            player.getClipboard().update(copyOperation.getPreSelection(), copyOperation.getTransformations());

            return new PassiveChangeAction(
                    player,
                    copyOperation,
                    player.getActionManager().getNextID()
            );
        } else if (operation instanceof FlipOperation) {
        } else if (operation instanceof RotateOperation) {

        }

        return null;
    }

    /**
     * Finalize certain operations for a player.
     *
     * @param player The player who's operation should be finalized.
     * @param material Optional material that has to be added to the operation states.
     */
    public static void finalize(PencilPlayer player, Material material) {
        if (player.getOperation() instanceof StateOperation) {
            if (player.getOperation() instanceof FillOperation) {
                ArrayList<PencilState> states = new ArrayList<>();

                for (PencilState state : ((FillOperation) player.getOperation()).getStates()) {
                    states.add(new PencilState(
                            state.getVector(),
                            state.getOutdated(),
                            material
                    ));
                }

                BlockChangeAction action = render(new FillOperation(player, states));

                player.getActionManager().add(action);
                player.resetOperation();
            }
        }
    }
}

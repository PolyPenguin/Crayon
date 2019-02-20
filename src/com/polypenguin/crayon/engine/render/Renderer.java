package com.polypenguin.crayon.engine.render;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.action.PassiveChangeAction;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.operation.*;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonPreState;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

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
        CrayonPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof FillOperation) {
            FillOperation fillOperation = (FillOperation) operation;

            for (CrayonState state : fillOperation.getStates()) {
                Vector vector = state.getVector();

                world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getUpdated());
            }

            player.getPlayer().sendMessage(Crayon.getPrefix() + ChatColor.GREEN + "Filled " + fillOperation.getStates().size() + " blocks");

            return new BlockChangeAction(
                    player,
                    ((FillOperation) operation).getStates(),
                    player.getActionManager().getNextID());
        } else if (operation instanceof PasteOperation) {
            PasteOperation pasteOperation = (PasteOperation) operation;
            Vector target = pasteOperation.getTarget();
            ArrayList<CrayonState> states = new ArrayList<>();

            for (CrayonPreState state : pasteOperation.getTransformations()) {
                Vector offset = new Vector(
                        (target.getBlockX() + state.getOffset().getBlockX()),
                        (target.getBlockY() + state.getOffset().getBlockY()),
                        (target.getBlockZ() + state.getOffset().getBlockZ())
                );

                Material material = state.getMaterial();

                states.add(new CrayonState(offset,
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
        CrayonPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof CopyOperation) {
            CopyOperation copyOperation = (CopyOperation) operation;

            player.getClipboard().update(copyOperation.getTransformations());

            return new PassiveChangeAction(
                    player,
                    copyOperation,
                    player.getActionManager().getNextID()
            );
        } else if (operation instanceof FlipOperation) {
            FlipOperation flipOperation = (FlipOperation) operation;

            player.getClipboard().flip();

            return new PassiveChangeAction(
                    player,
                    flipOperation,
                    player.getActionManager().getNextID()
            );
        } else if (operation instanceof RotateOperation) {
            RotateOperation rotateOperation = (RotateOperation) operation;

            player.getClipboard().rotate(
                    (double) rotateOperation.getParameter().getParamOne(),
                    (double) rotateOperation.getParameter().getParamTwo(),
                    (double) rotateOperation.getParameter().getParamThree()
            );

            return new PassiveChangeAction(
                    player,
                    rotateOperation,
                    player.getActionManager().getNextID()
            );
        }

        return null;
    }

    /**
     * Finalize certain operations for a player.
     *
     * @param player The player who's operation should be finalized.
     * @param material Optional material that has to be added to the operation states.
     */
    public static void finalize(CrayonPlayer player, Material material) {
        if (player.getOperation() instanceof StateOperation) {
            if (player.getOperation() instanceof FillOperation) {
                ArrayList<CrayonState> states = new ArrayList<>();

                for (CrayonState state : ((FillOperation) player.getOperation()).getStates()) {
                    states.add(new CrayonState(
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

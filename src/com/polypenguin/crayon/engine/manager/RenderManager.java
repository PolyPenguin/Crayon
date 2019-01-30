package com.polypenguin.crayon.engine.manager;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.operation.*;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import org.bukkit.World;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * The RenderManager will take in operations
 * and execute them accordingly, returning
 * actions for each player.
 */
public class RenderManager {


    public static BlockChangeAction render(StateOperation operation) {
        CrayonPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof FillOperation) {
            FillOperation fillOperation = (FillOperation) operation;

            for (CrayonState state : fillOperation.getStates()) {
                Vector vector = state.getVector();

                world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getUpdated());
            }

            return new BlockChangeAction(
                    player,
                    ((FillOperation) operation).getStates(),
                    player.getActionManager().getNextID());
        } else if (operation instanceof ShapeOperation) {
            ShapeOperation shapeOperation = (ShapeOperation) operation;

            render(shapeOperation.getOperation());
        } else if (operation instanceof PasteOperation) {
            PasteOperation pasteOperation = (PasteOperation) operation;
            Vector origin = pasteOperation.getOrigin();
            Vector target = pasteOperation.getTarget();

            ArrayList<CrayonState> states = new ArrayList<>();

            for (Vector vector : pasteOperation.getTransformations()) {
                Vector outdated = origin.add(vector);
                Vector updated = target.add(vector);

                states.add(new CrayonState(
                        target.add(vector),
                        world.getBlockAt(updated.getBlockX(), updated.getBlockY(), updated.getBlockZ()).getType(),
                        world.getBlockAt(outdated.getBlockX(), outdated.getBlockY(), outdated.getBlockZ()).getType())
                );
            }

            render(new FillOperation(player, states));
        }

        throw new IllegalStateException("[Crayon] [RenderManager.43] Critical render exception! Please contact a developer!");
    }

    //TODO: Return a PassiveChangeAction
    public static void handle(TransformOperation operation) {
        CrayonPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof CopyOperation) {
            CopyOperation copyOperation = (CopyOperation) operation;

            player.getClipboard().update(copyOperation.getTransformations());
        } else if (operation instanceof FlipOperation) {
            FlipOperation flipOperation = (FlipOperation) operation;

            player.getClipboard().flip();
        } else if (operation instanceof RotateOperation) {
            RotateOperation rotateOperation = (RotateOperation) operation;

            player.getClipboard().rotate();
        }
    }

}

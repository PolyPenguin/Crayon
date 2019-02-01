package com.polypenguin.crayon.engine.manager;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.action.PassiveChangeAction;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.operation.*;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonPreState;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import org.bukkit.Material;
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
            Vector target = pasteOperation.getTarget();

            ArrayList<CrayonState> states = new ArrayList<>();

            for (CrayonPreState state : pasteOperation.getTransformations()) {
                Vector offset = target.add(state.getOffset());

                states.add(new CrayonState(offset,
                        world.getBlockAt(offset.getBlockX(), offset.getBlockY(), offset.getBlockZ()).getType(),
                        state.getMaterial())
                );
            }

            render(new FillOperation(player, states));
        }

        throw new IllegalStateException("[Crayon] [RenderManager.65] Critical render exception! Please contact a developer!");
    }

    public static PassiveChangeAction handle(TransformOperation operation) {
        CrayonPlayer player = operation.getPlayer();
        World world = player.getPlayer().getWorld();

        if (operation instanceof CopyOperation) {
            CopyOperation copyOperation = (CopyOperation) operation;
            ArrayList<CrayonPreState> preStates = new ArrayList<>();

            for (Vector vector : copyOperation.getTransformations()) {
                Vector target = copyOperation.getOrigin().add(vector);

                preStates.add(new CrayonPreState(
                        vector, world.getBlockAt(target.getBlockX(), target.getBlockY(), target.getBlockZ()).getType()));
            }

            player.getClipboard().update(preStates);

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

            player.getClipboard().rotate();

            return new PassiveChangeAction(
                    player,
                    rotateOperation,
                    player.getActionManager().getNextID()
            );
        }

        throw new IllegalStateException("[Crayon] [RenderManager.112] Critical render exception! Please contact a developer!");
    }

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

                render(new FillOperation(player, states));

                player.resetOperation();
            } else if (player.getOperation() instanceof ShapeOperation) {

            }

            throw new IllegalStateException("[Crayon] [RenderManager.106] Critical render exception! Please contact a developer!");
        }

        throw new IllegalStateException("[Crayon] [RenderManager.106] Critical render exception! Please contact a developer!");
    }

}

package com.polypenguin.pencil.engine.action;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Action used for true block action.
 */
public class BlockChangeAction implements PencilAction {

    private PencilPlayer player;
    private ArrayList<PencilState> states;
    private int ID;

    private boolean isUndo;

    /**
     * Constructor that takes in parameters needed
     * to undo/redo the action if necessary.
     *
     * @param player The player that executed the action.
     * @param states The states of all changed blocks.
     * @param ID The ID of the current action.
     */
    public BlockChangeAction(PencilPlayer player, ArrayList<PencilState> states, int ID) {
        this.player = player;
        this.states = states;
        this.ID = ID;

        this.isUndo = true;
    }

    @Override
    public boolean canUndo() {
        return isUndo;
    }

    @Override
    public void undo() {
        if (isUndo) {
            isUndo = false;

            for (PencilState state : states) {
                Vector vector = state.getVector();

                player.getPlayer().getWorld().getBlockAt(
                        vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getOutdated());
            }
        }
    }

    @Override
    public void redo() {
        if (!isUndo) {
            for (PencilState state : states) {
                Vector vector = state.getVector();

                player.getPlayer().getWorld().getBlockAt(
                        vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getUpdated());
            }

            isUndo = true;
        }
    }

    /**
     * @return Get the player.
     */
    public PencilPlayer getPlayer() {
        return player;
    }

    /**
     * @return Get the states.
     */
    public ArrayList<PencilState> getStates() {
        return states;
    }

    @Override
    public int getID() {
        return ID;
    }
}

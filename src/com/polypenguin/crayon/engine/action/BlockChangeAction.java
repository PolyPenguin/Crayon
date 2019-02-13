package com.polypenguin.crayon.engine.action;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Action used for true block action.
 */
public class BlockChangeAction implements CrayonAction {

    private CrayonPlayer player;
    private ArrayList<CrayonState> states;
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
    public BlockChangeAction(CrayonPlayer player, ArrayList<CrayonState> states, int ID) {
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

            for (CrayonState state : states) {
                Vector vector = state.getVector();

                player.getPlayer().getWorld().getBlockAt(
                        vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getOutdated());
            }
        }
    }

    @Override
    public void redo() {
        if (!isUndo) {
            for (CrayonState state : states) {
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
    public CrayonPlayer getPlayer() {
        return player;
    }

    /**
     * @return Get the states.
     */
    public ArrayList<CrayonState> getStates() {
        return states;
    }

    @Override
    public int getID() {
        return ID;
    }
}

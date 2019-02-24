package com.polypenguin.pencil.engine.action;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.operation.TransformOperation;

/**
 * @author Matthias Kovacic
 *
 * Action used for passive block action.
 */
public class PassiveChangeAction implements PencilAction {

    private PencilPlayer player;
    private TransformOperation operation;
    private int ID;

    /**
     * Constructor that takes in parameters needed
     * to undo/redo the action if necessary.
     *
     * @param player The player that executed the action.
     * @param operation The operation that has been performed.
     * @param ID The ID of the current action.
     */
    public PassiveChangeAction(PencilPlayer player, TransformOperation operation, int ID) {
        this.player = player;
        this.operation = operation;
        this.ID = ID;
    }

    @Override
    public boolean canUndo() {
        return false;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }

    /**
     * @return Get the player.
     */
    public PencilPlayer getPlayer() {
        return player;
    }

    /**
     * @return Get the operation.
     */
    public TransformOperation getOperation() {
        return operation;
    }

    @Override
    public int getID() {
        return ID;
    }
}

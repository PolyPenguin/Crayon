package com.polypenguin.crayon.engine.action;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.operation.TransformOperation;

public class PassiveChangeAction implements CrayonAction {

    private CrayonPlayer player;
    private TransformOperation operation;
    private int ID;

    public PassiveChangeAction(CrayonPlayer player, TransformOperation operation, int ID) {
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

    public CrayonPlayer getPlayer() {
        return player;
    }

    public TransformOperation getOperation() {
        return operation;
    }

    @Override
    public int getID() {
        return ID;
    }
}

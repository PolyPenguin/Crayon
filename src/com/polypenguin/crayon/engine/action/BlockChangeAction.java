package com.polypenguin.crayon.engine.action;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import java.util.ArrayList;

public class BlockChangeAction implements CrayonAction {

    private CrayonPlayer player;
    private ArrayList<CrayonState> states;
    private int ID;

    public BlockChangeAction(CrayonPlayer player, ArrayList<CrayonState> states, int ID) {
        this.player = player;
        this.states = states;
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

    public ArrayList<CrayonState> getStates() {
        return states;
    }

    @Override
    public int getID() {
        return ID;
    }
}

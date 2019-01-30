package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import java.util.ArrayList;

public class FillOperation implements StateOperation {

    private CrayonPlayer player;
    private ArrayList<CrayonState> states;

    public FillOperation(CrayonPlayer player, ArrayList<CrayonState> states) {
        this.player = player;
        this.states = states;
    }

    public ArrayList<CrayonState> getStates() {
        return states;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

    public void execute() {
        //TODO: Send to render engine
    }

}

package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Operation that fills certain blocks.
 */
public class FillOperation extends StateOperation {

    private CrayonPlayer player;
    private ArrayList<CrayonState> states;

    public FillOperation(CrayonPlayer player, ArrayList<CrayonState> states) {
        this.player = player;
        this.states = states;
    }

    public ArrayList<CrayonState> getStates() {
        return states;
    }

    public void  setStates(ArrayList<CrayonState> states) {
        this.states = states;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

}

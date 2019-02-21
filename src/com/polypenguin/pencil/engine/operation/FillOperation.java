package com.polypenguin.pencil.engine.operation;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Operation that fills certain blocks.
 */
public class FillOperation extends StateOperation {

    private PencilPlayer player;
    private ArrayList<PencilState> states;

    public FillOperation(PencilPlayer player, ArrayList<PencilState> states) {
        this.player = player;
        this.states = states;
    }

    public ArrayList<PencilState> getStates() {
        return states;
    }

    public void  setStates(ArrayList<PencilState> states) {
        this.states = states;
    }

    @Override
    public PencilPlayer getPlayer() {
        return player;
    }

}

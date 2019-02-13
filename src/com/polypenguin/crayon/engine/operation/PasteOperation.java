package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonPreState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Operation that pastes a copied selection.
 */
public class PasteOperation extends StateOperation {

    private CrayonPlayer player;
    private ArrayList<CrayonPreState> transformations;
    private Vector target;

    public PasteOperation(CrayonPlayer player, ArrayList<CrayonPreState> transformations, Vector target) {
        this.player = player;
        this.transformations = transformations;
        this.target = target;
    }

    public Vector getTarget() {
        return target;
    }

    public ArrayList<CrayonPreState> getTransformations() {
        return transformations;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }
}

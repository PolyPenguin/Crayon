package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonPreState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Operation that copies a selection to a clipboard.
 */
public class CopyOperation extends TransformOperation {

    private CrayonPlayer player;
    private ArrayList<CrayonPreState> transformations;
    private Vector origin;

    public CopyOperation(CrayonPlayer player, ArrayList<CrayonPreState> transformations, Vector origin) {
        this.player = player;
        this.transformations = transformations;
        this.origin = origin;
    }

    public Vector getOrigin() {
        return origin;
    }

    public ArrayList<CrayonPreState> getTransformations() {
        return transformations;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }


    public void execute() {
        //TODO: Send to clipboard
    }

    @Override
    public String toString() {
        return "Copy";
    }
}

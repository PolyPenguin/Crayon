package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Special class!
 * Only sends copied states to the clipboard!
 */
public class CopyOperation implements TransformOperation {

    private CrayonPlayer player;
    private ArrayList<Vector> transformations;

    public CopyOperation(CrayonPlayer player, ArrayList<Vector> transformations) {
        this.player = player;
        this.transformations = transformations;
    }

    public ArrayList<Vector> getTransformations() {
        return transformations;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

    public void execute() {
        //TODO: Send to clipboard
    }
}

package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.VectorUtils;

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
    private Vector origin;

    public CopyOperation(CrayonPlayer player, ArrayList<Vector> transformations, Vector origin) {
        this.player = player;
        this.transformations = transformations;
        this.origin = origin;
    }

    public Vector getOrigin() {
        return origin;
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

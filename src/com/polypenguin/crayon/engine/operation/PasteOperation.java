package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;

import java.util.ArrayList;

public class PasteOperation implements StateOperation {

    private CrayonPlayer player;
    private ArrayList<Vector> transformations;
    private Vector origin, target;

    public PasteOperation(CrayonPlayer player, ArrayList<Vector> transformations, Vector origin, Vector target) {
        this.player = player;
        this.transformations = transformations;
        this.origin = origin;
        this.target = target;
    }

    public Vector getTarget() {
        return target;
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

    @Override
    public void execute() {

    }
}

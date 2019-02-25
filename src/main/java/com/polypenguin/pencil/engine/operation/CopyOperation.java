package com.polypenguin.pencil.engine.operation;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.geometry.selection.Selection;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilPreState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Operation that copies a selection to a clipboard.
 */
public class CopyOperation extends TransformOperation {

    private PencilPlayer player;
    private Selection preSelection;
    private ArrayList<PencilPreState> transformations;
    private Vector origin;

    public CopyOperation(PencilPlayer player, Selection preSelection, ArrayList<PencilPreState> transformations, Vector origin) {
        this.player = player;
        this.preSelection = preSelection;
        this.transformations = transformations;
        this.origin = origin;
    }

    public Vector getOrigin() {
        return origin;
    }

    public Selection getPreSelection() {
        return preSelection;
    }

    public ArrayList<PencilPreState> getTransformations() {
        return transformations;
    }

    @Override
    public PencilPlayer getPlayer() {
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

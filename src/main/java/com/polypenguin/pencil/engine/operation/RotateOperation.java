package com.polypenguin.pencil.engine.operation;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilParameter;

public class RotateOperation extends TransformOperation {

    private PencilPlayer player;
    private PencilParameter parameter;

    public RotateOperation(PencilPlayer player) {
        this.player = player;
        this.parameter = new PencilParameter();
    }

    @Override
    public PencilPlayer getPlayer() {
        return player;
    }

    public PencilParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "Rotate";
    }
}

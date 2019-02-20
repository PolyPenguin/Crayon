package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonParameter;

public class RotateOperation extends TransformOperation {

    private CrayonPlayer player;
    private CrayonParameter parameter;

    public RotateOperation(CrayonPlayer player) {
        this.player = player;
        this.parameter = new CrayonParameter();
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

    public CrayonParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "Rotate";
    }
}

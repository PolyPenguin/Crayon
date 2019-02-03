package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonParameter;
import com.polypenguin.crayon.engine.utils.miscellaneous.ShapeType;

public class ShapeOperation extends StateOperation {

    private CrayonPlayer player;
    private ShapeType type;
    private Vector origin;
    private CrayonParameter parameter;
    private FillOperation operation;

    public ShapeOperation(CrayonPlayer player, ShapeType type, Vector origin) {
        this.player = player;
        this.type = type;
        this.parameter = new CrayonParameter();
    }

    public FillOperation getOperation() {
        return operation;
    }

    public ShapeType getType() {
        return type;
    }

    public Vector getOrigin() {
        return origin;
    }

    public CrayonParameter getParameter() {
        return parameter;
    }

    public void finalize() {
        //TODO: Calculate vectors -> set as a fill operation
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }
}

package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import java.util.ArrayList;

public class ShapeOperation extends StateOperation {

    private CrayonPlayer player;
    private FillOperation operation;

    public ShapeOperation(CrayonPlayer player, ArrayList<CrayonState> states) {
        this.player = player;
        this.operation = new FillOperation(player, states);
    }

    public FillOperation getOperation() {
        return operation;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

    public void execute() {
        //TODO: Send to render engine
    }
}

package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;

public class RotateOperation implements TransformOperation {

    private CrayonPlayer player;

    public RotateOperation(CrayonPlayer player) {
        this.player = player;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }

}

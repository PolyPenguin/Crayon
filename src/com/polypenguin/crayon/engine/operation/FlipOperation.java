package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;

public class FlipOperation implements TransformOperation {

    private CrayonPlayer player;

    public FlipOperation(CrayonPlayer player) {
        this.player = player;
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }
}

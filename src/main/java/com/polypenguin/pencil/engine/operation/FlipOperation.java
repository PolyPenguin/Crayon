package com.polypenguin.pencil.engine.operation;

import com.polypenguin.pencil.engine.PencilPlayer;

public class FlipOperation extends TransformOperation {

    private PencilPlayer player;

    public FlipOperation(PencilPlayer player) {
        this.player = player;
    }

    @Override
    public PencilPlayer getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Flip";
    }
}

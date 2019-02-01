package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;

public abstract class StateOperation implements CrayonOperation {

    public abstract CrayonPlayer getPlayer();

    public abstract void execute();

}

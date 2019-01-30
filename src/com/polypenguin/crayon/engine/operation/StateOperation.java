package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import java.util.ArrayList;

public interface StateOperation {

    CrayonPlayer getPlayer();

    void execute();

}

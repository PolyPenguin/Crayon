package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonPreState;

import java.util.ArrayList;

public class Clipboard {

    private CrayonPlayer owner;
    private ArrayList<CrayonPreState> preStates;

    public Clipboard(CrayonPlayer owner) {
        this.owner = owner;
        this.preStates = new ArrayList<>();
    }

    /**
     * Add offsets to the clipboard!
     *
     * @param preStates The offsets to be updated.
     */
    public void update(ArrayList<CrayonPreState> preStates) {
        this.preStates = preStates;
    }

    public CrayonPlayer getOwner() {
        return owner;
    }

    public ArrayList<CrayonPreState> getPreStates() {
        return preStates;
    }

    public void paste() {

    }

    public void flip() {

    }

    public void rotate() {
        //TODO: Find a way?
    }
}

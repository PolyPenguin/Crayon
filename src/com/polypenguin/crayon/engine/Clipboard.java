package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.engine.geometry.Vector;

import java.util.ArrayList;

public class Clipboard {

    private CrayonPlayer owner;
    private ArrayList<Vector> vectors;

    public Clipboard(CrayonPlayer owner) {
        this.owner = owner;
        this.vectors = new ArrayList<>();
    }

    /**
     * Add offsets to the clipboard!
     *
     * @param vectors The offsets to be updated.
     */
    public void update(ArrayList<Vector> vectors) {
        this.vectors = vectors;
    }

    public CrayonPlayer getOwner() {
        return owner;
    }

    public ArrayList<Vector> getVectors() {
        return vectors;
    }

    public void paste() {

    }

    public void flip() {

    }

    public void rotate() {
        //TODO: Find a way?
    }
}

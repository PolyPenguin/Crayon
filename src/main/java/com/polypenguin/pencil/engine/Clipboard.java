package com.polypenguin.pencil.engine;

import com.polypenguin.pencil.engine.geometry.selection.Selection;
import com.polypenguin.pencil.engine.utils.miscellaneous.PencilPreState;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * The clipboard handles cached selections
 * for CrayonPlayers.
 */
public class Clipboard {

    private PencilPlayer owner;
    private Selection selection;
    private ArrayList<PencilPreState> preStates;
    private boolean hasCache;

    public Clipboard(PencilPlayer owner) {
        this.owner = owner;
        this.preStates = new ArrayList<>();
        this.hasCache = false;
    }

    /**
     * Add offsets to the clipboard!
     *
     * @param preStates The offsets to be updated.
     */
    public void update(Selection selection, ArrayList<PencilPreState> preStates) {
        this.selection = selection;
        this.preStates = preStates;

        this.hasCache = true;
    }

    public PencilPlayer getOwner() {
        return owner;
    }

    public Selection getSelection() {
        return selection;
    }

    public ArrayList<PencilPreState> getPreStates() {
        return preStates;
    }

    public boolean hasCache() {
        return hasCache;
    }

    public void flush() {
        selection = null;
        preStates.clear();
        hasCache = false;
    }
}

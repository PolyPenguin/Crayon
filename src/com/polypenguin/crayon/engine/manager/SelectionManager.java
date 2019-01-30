package com.polypenguin.crayon.engine.manager;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.CuboidSelection;
import com.polypenguin.crayon.engine.geometry.selection.NullSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;
import com.polypenguin.crayon.engine.geometry.selection.VectorSelection;

public class SelectionManager {

    private CrayonPlayer owner;
    private Selection selection;

    public SelectionManager(CrayonPlayer owner) {
        this.owner = owner;
    }

    public CrayonPlayer getOwner() {
        return owner;
    }

    public Selection getSelection() {
        return selection;
    }

    public boolean hasSelection() {
        return selection != null;
    }

    public Selection.SelectionType getCurrentType() {
        if (hasSelection()) {
            if (selection instanceof VectorSelection) {
                return Selection.SelectionType.SINGLE;
            } else if (selection instanceof CuboidSelection) {
                return Selection.SelectionType.DOUBLE;
            } else {
                return Selection.SelectionType.MULTI;
            }
        }

        return null;
    }

    /**
     * Updates the selection.
     * 
     * If the current selection is a VectorSelection, 
     * it will get updated to a CuboidSelection.
     * 
     *  If the current selection is a CuboidSelection, 
     *  it will get updated to a VectorSelection.
     * 
     * @param vector
     */
    public void update(Vector vector) {
        if (vector == null) {
            selection = new NullSelection();
        }

        if (owner.getSelectionMode() == CrayonPlayer.SelectionMode.SINGLE) {
            selection = new VectorSelection(vector);
        } else if (owner.getSelectionMode() == CrayonPlayer.SelectionMode.DOUBLE) {
            if (selection.getNativeMaximum() != null) {
                selection.setNativeMaximum(vector);
            } else {
                selection = new CuboidSelection(vector, null);
            }
        } else if (owner.getSelectionMode() == CrayonPlayer.SelectionMode.MULTI) {
            /**
             * @// FIXME: 26/01/2019
             */
        }
    }
}

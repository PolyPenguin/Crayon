package com.polypenguin.crayon.engine.manager;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.CuboidSelection;
import com.polypenguin.crayon.engine.geometry.selection.NullSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;
import com.polypenguin.crayon.engine.geometry.selection.VectorSelection;
import com.polypenguin.crayon.engine.utils.VectorUtils;

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
     *  TODO: Recode this method. It's a bit dull.
     * 
     * @param vector The vector that is to be added.
     * @return Which number of position has been updated.
     */
    public int update(Vector vector) {
        if (vector == null) {
            selection = new NullSelection();

            return 1;
        }

        if (owner.getSelectionMode() == CrayonPlayer.SelectionMode.SINGLE) {
            selection = new VectorSelection(vector);

            return 1;
        } else if (owner.getSelectionMode() == CrayonPlayer.SelectionMode.DOUBLE) {
            if (selection == null) {
                selection = new CuboidSelection(vector, null);

                return 1;
            } else if (selection.getNativeMaximum() != null) {
                selection = new CuboidSelection(vector, null);

                return 1;
            } else if (selection.getNativeMinimum() != null) {
                selection.setNativeMaximum(vector);

                return 2;
            }
        } else if (owner.getSelectionMode() == CrayonPlayer.SelectionMode.MULTI) {
            /**
             * @// FIXME: 26/01/2019
             */

            return 0;
        }

        return 0;
    }
}

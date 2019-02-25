package com.polypenguin.pencil.engine.manager;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.geometry.Vector;
import com.polypenguin.pencil.engine.geometry.selection.CuboidSelection;
import com.polypenguin.pencil.engine.geometry.selection.NullSelection;
import com.polypenguin.pencil.engine.geometry.selection.Selection;
import com.polypenguin.pencil.engine.geometry.selection.VectorSelection;

/**
 * @author Matthias Kovacic
 *
 * Manager that manages all the selections for
 * a specific player. Each player has it's
 * own SelectionManager.
 */
public class SelectionManager {

    private PencilPlayer owner;
    private Selection selection;

    public SelectionManager(PencilPlayer owner) {
        this.owner = owner;
    }

    /**
     * @return Get the owner.
     */
    public PencilPlayer getOwner() {
        return owner;
    }

    /**
     * @return Get the current selection.
     */
    public Selection getSelection() {
        return selection;
    }

    /**
     * @return True if a selection is present.
     */
    public boolean hasSelection() {
        return selection != null;
    }

    /**
     * @return Get the current type of selection.
     *
     * @see com.polypenguin.pencil.engine.geometry.selection.Selection.SelectionType
     */
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

        if (owner.getSelectionMode() == PencilPlayer.SelectionMode.SINGLE) {
            selection = new VectorSelection(vector);

            return 1;
        } else if (owner.getSelectionMode() == PencilPlayer.SelectionMode.DOUBLE) {
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
        }

        return 0;
    }
}

package com.polypenguin.crayon.engine.action;

/**
 * @author Matthias Kovacic
 *
 * Interface that is used for every
 * action in Crayon.
 */
public interface CrayonAction {

    /**
     * @return True if the action can be undone.
     */
    boolean canUndo();

    /**
     * @return The ID of the current action.
     */
    int getID();

    /**
     * Undo the current action.
     */
    void undo();

    /**
     * Redo the current action.
     */
    void redo();

}

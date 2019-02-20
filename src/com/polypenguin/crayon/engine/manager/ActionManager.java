package com.polypenguin.crayon.engine.manager;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.action.CrayonAction;

import java.util.LinkedList;

/**
 * @author Matthias Kovacic
 *
 * Manager that manages all the actions for
 * a specific player. Each player has it's
 * own ActionManager.
 *
 * @see CrayonPlayer
 */
public class ActionManager {

    private CrayonPlayer owner;
    private LinkedList<CrayonAction> actions;

    public ActionManager(CrayonPlayer owner) {
        this.owner = owner;
        this.actions = new LinkedList<>();
    }

    /**
     * Add actions to the players action stream.
     * A LinkedList automatically allows for a
     * flipped action tree!
     *
     * Automatically limit amount of actions to be 45!
     *
     * @param action The action that is to be added.
     */
    public void add(CrayonAction action) {
        if (getSize() == 45) {
            actions.removeLast();
        }

        actions.addFirst(action);
    }

    public int getNextID() {
        return actions.size() + 1;
    }

    /**
     * Getting the last added action!
     *
     * @return The last added action.
     */
    public CrayonAction getLast() {
        return actions.getFirst();
    }

    /**
     * @return Get a list with all the actions
     */
    public LinkedList<CrayonAction> getActions() {
        return actions;
    }

    /**
     * Getting an action with a fixed ID.
     *
     * @param ID The ID that has to be searched.
     * @return The action with the given ID if found.
     */
    public CrayonAction get(int ID) {
        for (CrayonAction action : actions) {
            if (action.getID() == ID) {
                return action;
            }
        }

        return null;
    }

    /**
     * Clears all actions from this manager.
     */
    public void flush() {
        actions.clear();
    }

    /**
     * @return The amount of actions performed.
     */
    public int getSize() {
        return actions.size();
    }

    public boolean hasAction() {
        return actions.size() != 0;
    }

}

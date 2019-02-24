package com.polypenguin.pencil.engine.manager;

import com.polypenguin.pencil.engine.PencilPlayer;
import com.polypenguin.pencil.engine.action.PencilAction;

import java.util.LinkedList;

/**
 * @author Matthias Kovacic
 *
 * Manager that manages all the actions for
 * a specific player. Each player has it's
 * own ActionManager.
 *
 * @see PencilPlayer
 */
public class ActionManager {

    private PencilPlayer owner;
    private LinkedList<PencilAction> actions;

    public ActionManager(PencilPlayer owner) {
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
    public void add(PencilAction action) {
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
    public PencilAction getLast() {
        return actions.getFirst();
    }

    /**
     * @return Get a list with all the actions
     */
    public LinkedList<PencilAction> getActions() {
        return actions;
    }

    /**
     * Getting an action with a fixed ID.
     *
     * @param ID The ID that has to be searched.
     * @return The action with the given ID if found.
     */
    public PencilAction get(int ID) {
        for (PencilAction action : actions) {
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

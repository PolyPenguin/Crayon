package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.action.BlockChangeAction;
import com.polypenguin.crayon.engine.action.CrayonAction;
import com.polypenguin.crayon.engine.render.RenderStack;

import java.util.LinkedList;

/**
 * @author Matthias Kovacic
 *
 * Custom Undo/Redo manager for Crayon.
 * Actions are stored in a LinkedList, actions can
 * be retrieved using their ID and can than accordingly
 * be unexecuted or re-executed.
 *
 * Actions are NEVER deleted by Crayon itself, the player
 * will be able to delete them.
 */
public class ActionManager {

    private CrayonPlayer owner;
    private LinkedList<CrayonAction> actions;

    public ActionManager(CrayonPlayer owner) {
        this.owner = owner;
        this.actions = new LinkedList<>();
    }

    public void addAction(CrayonAction action) {
        actions.addFirst(action);
    }

    public void addAndExecuteAction(CrayonAction action) {
        addAction(action);
        execute(action.getID());
    }

    public void clear() {
        actions.clear();
    }

    /**
    public void clear(int ID) {
        CrayonAction action = null;

        for (CrayonAction sub : actions) {
            if (sub.getID() == ID) {
                action = sub;
            }
        }

        remove(action);
    }

    protected void remove(CrayonAction action) {
        actions.remove(action);
    }
    */

    public int getNextID() {
        return actions.size() + 1;
    }

    public void execute(int ID) {
        for (CrayonAction action : actions) {
            if (action instanceof BlockChangeAction) {
                if (action.getID() == ID) {
                    RenderStack.RenderTask task = Crayon.getRenderer().getStack().generateTask(
                            owner,
                            (BlockChangeAction) action,
                            RenderStack.TaskType.EXECUTE
                    );

                    Crayon.getRenderer().render(task);
                }
            }
        }
    }

    public void undo(int ID) {
        for (CrayonAction action : actions) {
            if (action instanceof BlockChangeAction) {
                if (action.getID() == ID) {
                    if (action.canUndo()) {
                        RenderStack.RenderTask task = Crayon.getRenderer().getStack().generateTask(
                                owner,
                                (BlockChangeAction) action,
                                RenderStack.TaskType.UNDO
                        );

                        Crayon.getRenderer().render(task);
                    }
                }
            }
        }
    }

    public void redo(int ID) {
        for (CrayonAction action : actions) {
            if (action instanceof BlockChangeAction) {
                if (action.getID() == ID) {
                    if (!action.canUndo()) {
                        RenderStack.RenderTask task = Crayon.getRenderer().getStack().generateTask(
                                owner,
                                (BlockChangeAction) action,
                                RenderStack.TaskType.REDO
                        );

                        Crayon.getRenderer().render(task);
                    }
                }
            }
        }
    }
}

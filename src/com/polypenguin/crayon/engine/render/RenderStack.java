package com.polypenguin.crayon.engine.render;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.action.BlockChangeAction;

import java.util.LinkedList;

public class RenderStack {

    private LinkedList<RenderTask> tasks;
    private boolean hasNext;

    public RenderStack() {
        tasks = new LinkedList<>();
    }

    public void clear() {
        /*
        TODO: Make sure to notify players of these actions!
         */
    }

    public void addTask(RenderTask task) {
        tasks.addLast(task);
    }

    public RenderTask generateTask(CrayonPlayer player, BlockChangeAction action, TaskType type) {
        return new RenderTask(player, action, type);
    }

    /**
     * Continuous stream: When a task is finished, push the next one.
     *
     * @return
     */
    public RenderTask push() {
        if (hasNext) {
            Crayon.getRenderer().render(tasks.getFirst());

            hasNext = false;
        }

        return null;
    }

    public void move() {
        hasNext = true;
    }

    public enum TaskType {
        EXECUTE,
        UNDO,
        REDO
    }

    public class RenderTask {

        private CrayonPlayer owner;
        private BlockChangeAction action;
        private TaskType type;

        public RenderTask(CrayonPlayer owner, BlockChangeAction action, TaskType type) {
            this.owner = owner;
            this.action = action;
            this.type = type;
        }

        public CrayonPlayer getOwner() {
            return owner;
        }

        public BlockChangeAction getAction() {
            return action;
        }

        public TaskType getType() {
            return type;
        }
    }

}

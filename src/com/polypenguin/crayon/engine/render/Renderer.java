package com.polypenguin.crayon.engine.render;

public class Renderer {

    private RenderStack stack;

    public Renderer() {
        this.stack = new RenderStack();
    }

    public RenderStack getStack() {
        return stack;
    }

    public void render(RenderStack.RenderTask task) {
        switch (task.getType()) {
            case EXECUTE:
                task.getAction().execute();
            case UNDO:
                task.getAction().undo();
            case REDO:
                task.getAction().redo();
        }

        stack.move();
        stack.push();
    }

}

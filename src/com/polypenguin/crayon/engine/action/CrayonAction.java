package com.polypenguin.crayon.engine.action;

public abstract interface CrayonAction {

    boolean canUndo();

    int getID();

    void undo();

    void redo();

}
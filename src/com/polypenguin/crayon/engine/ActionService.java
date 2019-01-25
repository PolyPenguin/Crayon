package com.polypenguin.crayon.engine;

import com.polypenguin.crayon.engine.action.CrayonAction;

public class ActionService {

    private Node current = null;
    private Node parent = new Node();

    public ActionService() {
        current = parent;
    }

    public ActionService(ActionService manager){
        this();

        current = manager.current;
    }

    public void clear(){
        current = parent;
    }

    public void addChangeable(CrayonAction action){
        Node node = new Node(action);

        current.right = node;
        node.left = current;
        current = node;
    }

    public boolean canUndo(){
        return current != parent;
    }

    public boolean canRedo() {
        return current.right != null;
    }

    public boolean undo(){
        if (!canUndo()){
            return false;
        }

        current.action.undo();
        moveLeft();

        return true;
    }

    public boolean redo(){
        if ( !canRedo() ){
            return false;
        }

        moveRight();
        current.action.redo();

        return true;
    }

    private void moveLeft(){
        if (current.left == null){
            throw new IllegalStateException("Internal index set to null.");
        }

        current = current.left;
    }

    private void moveRight(){
        if (current.right == null){
            throw new IllegalStateException("Internal index set to null.");
        }

        current = current.right;
    }

    private class Node {

        private Node left = null;
        private Node right = null;

        private final CrayonAction action;

        public Node(CrayonAction action){
            this.action = action;
        }

        public Node(){
            action = null;
        }

    }

}

package com.polypenguin.crayon.engine.action;

import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;

import org.bukkit.World;

import java.util.ArrayList;

public class BlockChangeAction implements CrayonAction {

    private CrayonPlayer player;
    private World world;
    private ArrayList<CrayonState> states;
    private boolean canUndo;
    private int ID;

    public BlockChangeAction(CrayonPlayer player, World world, ArrayList<CrayonState> states) {
        this.player = player;
        this.states = states;

        canUndo = true;
        ID = player.getActionManager().getNextID();
    }

    public CrayonPlayer getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public ArrayList<CrayonState> getStates() {
        return states;
    }

    @Override
    public boolean canUndo() {
        return canUndo;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void execute() {
        canUndo = true;
    }

    @Override
    public void undo() {
        for (CrayonState state : states) {
            Vector vector = state.getVector();
            world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getOutdated());
        }

        canUndo = false;
    }

    @Override
    public void redo() {
        for (CrayonState state : states) {
            Vector vector = state.getVector();
            world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(state.getUpdated());
        }

        canUndo = true;
    }
}

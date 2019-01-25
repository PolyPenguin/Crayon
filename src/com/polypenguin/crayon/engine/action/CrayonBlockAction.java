package com.polypenguin.crayon.engine.action;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;

public class CrayonBlockAction implements CrayonAction {

    private World world;
    private ArrayList<CrayonState> states;
    private ArrayList<Vector> vectors;
    private HashMap<Vector, Material> outdated;
    private HashMap<Vector, Material> updated;

    public CrayonBlockAction(World world, ArrayList<CrayonState> states) {
        this.world = world;
        this.states = states;

        for (CrayonState state : states) {
            vectors.add(state.getVector());
            outdated.put(state.getVector(), state.getOutdated());
            updated.put(state.getVector(), state.getUpdated());
        }
    }

    public World getWorld() {
        return world;
    }

    public CrayonState getState(Vector vector) {
        for (CrayonState state : states) {
            if (state.getVector() == vector) {
                return state;
            }
        }

        return null;
    }

    public ArrayList<Vector> getVectors() {
        return vectors;
    }

    public HashMap<Vector, Material> getOutdated() {
        return outdated;
    }

    public HashMap<Vector, Material> getUpdated() {
        return updated;
    }

    /**
     * Undo = Updated -> Outdated
     */
    @Override
    public void undo() {
        for (Vector vector : vectors) {
            world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(getOutdated().get(vector));
        }
    }

    /**
     * Undo = Outdated -> Updated
     */
    @Override
    public void redo() {
        for (Vector vector : vectors) {
            world.getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).setType(getUpdated().get(vector));
        }
    }
}


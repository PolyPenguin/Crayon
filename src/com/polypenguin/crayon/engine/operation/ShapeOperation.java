package com.polypenguin.crayon.engine.operation;

import com.polypenguin.crayon.Crayon;
import com.polypenguin.crayon.core.gui.CrayonInterface;
import com.polypenguin.crayon.engine.CrayonPlayer;
import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.CuboidSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;
import com.polypenguin.crayon.engine.geometry.selection.ShapeSelection;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonParameter;
import com.polypenguin.crayon.engine.utils.miscellaneous.CrayonState;
import com.polypenguin.crayon.engine.utils.miscellaneous.ShapeType;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Operation that is used for rendering shapes.
 */
public class ShapeOperation extends StateOperation {

    private CrayonPlayer player;
    private ShapeType type;
    private Vector origin;
    private CrayonParameter parameter;

    public ShapeOperation(CrayonPlayer player, ShapeType type, Vector origin) {
        this.player = player;
        this.type = type;
        this.origin = origin;
        this.parameter = new CrayonParameter();
    }

    public ShapeType getType() {
        return type;
    }

    public Vector getOrigin() {
        return origin;
    }

    public CrayonParameter getParameter() {
        return parameter;
    }

    public void finalizeOperation() {
        Selection selection = null;

        if (type == ShapeType.CUBE || type == ShapeType.CUBOID) {
            Vector cubeMin = origin;
            Vector cubeMax = new Vector(
                    origin.getBlockX() + parameter.getParamOne(),
                    origin.getBlockY() + parameter.getParamTwo(),
                    origin.getBlockZ() + parameter.getParamThree()
            );

            selection = new CuboidSelection(cubeMin, cubeMax);
        } else if (type == ShapeType.SPHERE) {
            Vector shapeMin = origin;

            selection = new ShapeSelection(shapeMin, new Vector(parameter.getParamOne(), parameter.getParamTwo(), parameter.getParamThree()), ShapeType.SPHERE);
        } else if (type == ShapeType.ELLIPSOID) {
            Vector shapeMin = origin;

            selection = new ShapeSelection(shapeMin, new Vector(parameter.getParamOne(), parameter.getParamTwo(), parameter.getParamThree()), ShapeType.ELLIPSOID);
        }

        ArrayList<CrayonState> states = new ArrayList<>();

        for (Vector vector : selection.getVectors(true)) {
            states.add(new CrayonState(
                    vector,
                    player.getPlayer().getWorld().getBlockAt(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ()).getType(),
                    null
            ));
        }

        player.setOperation(new FillOperation(player, states));

        CrayonInterface.openInventory(player, Crayon.getMaterialSet().getStone());
    }

    @Override
    public CrayonPlayer getPlayer() {
        return player;
    }
}

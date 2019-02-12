package com.polypenguin.crayon.engine.geometry.selection;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.utils.VectorUtils;
import com.polypenguin.crayon.engine.utils.miscellaneous.ShapeType;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Selection used for shapes.
 */
public class ShapeSelection implements Selection {

    private Vector min, scale;
    private ShapeType type;

    public ShapeSelection(Vector min, Vector scale, ShapeType type) {
        this.min = min;
        this.scale = scale;
        this.type = type;
    }

    @Override
    public Vector getNativeMinimum() {
        return min;
    }

    @Override
    public void setNativeMinimum(Vector min) {
        this.min = min;
    }

    @Override
    public Vector getNativeMaximum() {
        return min;
    }

    @Override
    public void setNativeMaximum(Vector max) {
        this.min = max;
    }

    /**
     * @return The scales in X, Y and Z direction as a vector.
     */
    public Vector getScale() {
        return scale;
    }

    /**
     * Set the scales in X, Y and Z directions.
     *
     * @param scale The scales in X, Y and Z directions as a vector.
     */
    public void setScale(Vector scale) {
        this.scale = scale;
    }

    /**
     * @return The type of shape.
     */
    public ShapeType getType() {
        return type;
    }

    @Override
    public org.bukkit.util.Vector getBukkitMinimum() {
        return new org.bukkit.util.Vector(min.getBlockX(), min.getBlockY(), min.getBlockZ());
    }

    @Override
    public org.bukkit.util.Vector getBukkitMaximum() {
        return new org.bukkit.util.Vector(min.getBlockX(), min.getBlockY(), min.getBlockZ());
    }

    @Override
    public int getBlocks() {
        return getVectors(true).size();
    }

    @Override
    public int getWidth() {
        return (scale.getBlockX() * 2) + 1;
    }

    @Override
    public int getHeight() {
        return (scale.getBlockY() * 2) + 1;
    }

    @Override
    public int getLength() {
        return (scale.getBlockZ() * 2) + 1;
    }

    @Override
    public boolean contains(Vector vector) {
        return getVectors(true).contains(vector);
    }

    @Override
    public ArrayList<Vector> getVectors(boolean filled) {
        if (type == ShapeType.SPHERE || type == ShapeType.ELLIPSOID) {
            return VectorUtils.getEllipsoidFilled(new VectorSelection(min), scale);
        } else if (type == ShapeType.CYLINDER) {
            return VectorUtils.getCylinderFilled(new VectorSelection(min), scale);
        }

        return null;
    }

    @Override
    public SelectionType getSelectionType() {
        return SelectionType.SHAPE;
    }
}
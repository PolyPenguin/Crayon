package com.polypenguin.pencil.engine.utils.miscellaneous;

/**
 * @author Matthias Kovacic
 *
 * Types of shapes used in Pencil.
 */
public enum ShapeType {

    CUBE("Cube"),
    CUBOID("Cube"),
    PYRAMID("Cube"),
    PRISM("Cube"),
    SPHERE("Cube"),
    ELLIPSOID("Cube"),
    CYLINDER("Cube");

    private String name;

    ShapeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.polypenguin.crayon.engine.utils.miscellaneous;

/**
 * @author Matthias Kovacic
 *
 * A Crayon Parameter is used to store 3-Dimensional
 * values. Think of it as some sort of OpenGL VBO.
 */
public class CrayonParameter {

    private int x, y, z;

    public CrayonParameter() { }

    public int getParamOne() {
        return x;
    }

    public void setParamOne(int x) {
        this.x = x;
    }

    public int getParamTwo() {
        return y;
    }

    public void setParamTwo(int y) {
        this.y = y;
    }

    public int getParamThree() {
        return z;
    }

    public void setParamThree(int z) {
        this.z = z;
    }
}

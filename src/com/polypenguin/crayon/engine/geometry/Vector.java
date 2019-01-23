package com.polypenguin.crayon.engine.geometry;

import org.bukkit.Location;

/**
 * @author Matthias Kovacic
 *
 * 3D-Vector on which actions can be executed
 * directly and indirectly.
 */
public class Vector implements Comparable, Cloneable {

    public static final Vector ZERO = new Vector();
    public static final Vector UNIT_X = new Vector(1, 0, 0);
    public static final Vector UNIT_Y = new Vector(0, 1, 0);
    public static final Vector UNIT_Z = new Vector(0, 0, 1);
    public static final Vector UNIT = new Vector(1, 1, 1);
    public static final Vector MAX = new Vector(Integer.MAX_VALUE, 256, Integer.MAX_VALUE);
    public static final Vector MIN = new Vector(Integer.MIN_VALUE, 256, Integer.MIN_VALUE);
    protected double x, y, z;

    public Vector() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(int unit) {
        this.x = unit;
        this.y = unit;
        this.z = unit;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double unit) {
        this.x = unit;
        this.y = unit;
        this.z = unit;
    }

    public Vector(Location location, boolean round) {
        if (round) {
            this.x = location.getBlockX();
            this.y = location.getBlockY();
            this.z = location.getBlockZ();
        } else {
            this.x = location.getX();
            this.y = location.getY();
            this.z = location.getZ();
        }

    }

    /**
     * @return The X value of the Vector.
     */
    public double getX() {
        return x;
    }

    /**
     * @return The Y value of the Vector.
     */
    public double getY() {
        return y;
    }

    /**
     * @return The Z value of the Vector.
     */
    public double getZ() {
        return z;
    }

    /**
     * @param x Set the X value of the Vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y Set the Y value of the Vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param z Set the Z value of the Vector.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @// FIXME: 23/01/2019 Finish this class!
     */
    
    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be added to the vectors X value.
     * @param y The Y value that should be added to the vectors Y value.
     * @param z The Z value that should be added to the vectors Z value.
     */
    public void add(int x, int y, int z) {
       
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be added to the vectors X value.
     * @param y The Y value that should be added to the vectors Y value.
     * @param z The Z value that should be added to the vectors Z value.
     */
    public void add(double x, double y, double z) {
       
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be added to the vectors X value.
     * @param y The Y value that should be added to the vectors Y value.
     * @param z The Z value that should be added to the vectors Z value.
     */
    public void add(float x, float y, float z) {
       
    }

    /**
     * Add X, Y and Z values from all the given vectors
     * to the X, Y and Z values of the vector respectively.
     *
     * @param vectors The vectors that have to be added.
     */
    public void add(Vector... vectors) {
        
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be subtracted to the vectors X value.
     * @param y The Y value that should be subtracted to the vectors Y value.
     * @param z The Z value that should be subtracted to the vectors Z value.
     */
    public void subtract(int x, int y, int z) {
        
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be subtracted to the vectors X value.
     * @param y The Y value that should be subtracted to the vectors Y value.
     * @param z The Z value that should be subtracted to the vectors Z value.
     */
    public void subtract(double x, double y, double z) {
        
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be subtracted to the vectors X value.
     * @param y The Y value that should be subtracted to the vectors Y value.
     * @param z The Z value that should be subtracted to the vectors Z value.
     */
    public void subtract(float x, float y, float z) {
        
    }

    /**
     * Add X, Y and Z values from all the given vectors
     * to the X, Y and Z values of the vector respectively.
     *
     * @param vectors The vectors that have to be subtracted.
     */
    public void subtract(Vector... vectors) {
       
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be multiplied to the vectors X value.
     * @param y The Y value that should be multiplied to the vectors Y value.
     * @param z The Z value that should be multiplied to the vectors Z value.
     */
    public void multiply(int x, int y, int z) {
        
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be multiplied to the vectors X value.
     * @param y The Y value that should be multiplied to the vectors Y value.
     * @param z The Z value that should be multiplied to the vectors Z value.
     */
    public void multiply(double x, double y, double z) {
       
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be multiplied to the vectors X value.
     * @param y The Y value that should be multiplied to the vectors Y value.
     * @param z The Z value that should be multiplied to the vectors Z value.
     */
    public void multiply(float x, float y, float z) {
        
    }

    /**
     * Add X, Y and Z values from all the given vectors
     * to the X, Y and Z values of the vector respectively.
     *
     * @param vectors The vectors that have to be multiplied.
     */
    public void multiply(Vector... vectors) {
        
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be divided to the vectors X value.
     * @param y The Y value that should be divided to the vectors Y value.
     * @param z The Z value that should be divided to the vectors Z value.
     */
    public void divide(int x, int y, int z) {
        
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be divided to the vectors X value.
     * @param y The Y value that should be divided to the vectors Y value.
     * @param z The Z value that should be divided to the vectors Z value.
     */
    public void divide(double x, double y, double z) {
       
    }

    /**
     * Add X, Y and Z values to the X, Y and Z values of
     * the vector respectively.
     *
     * @param x The X value that should be divided to the vectors X value.
     * @param y The Y value that should be divided to the vectors Y value.
     * @param z The Z value that should be divided to the vectors Z value.
     */
    public void divide(float x, float y, float z) {
        
    }

    /**
     * Add X, Y and Z values from all the given vectors
     * to the X, Y and Z values of the vector respectively.
     *
     * @param vectors The vectors that have to be divided.
     */
    public Vector divide(Vector... vectors) {
        
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

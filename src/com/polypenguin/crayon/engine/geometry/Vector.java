package com.polypenguin.crayon.engine.geometry;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Matthias Kovacic
 *
 * 3D-Vector on which actions can be executed
 * directly and indirectly.
 */
public class Vector {

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

    public Vector(Vector vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
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
     * @return The X value of the vector.
     */
    public double getX() {
        return x;
    }

    /**
     * @return The rounded X value of the vector.
     */
    public int getBlockX() {
        return (int) Math.round(this.x);
    }

    /**
     * @return The Y value of the vector.
     */
    public double getY() {
        return y;
    }

    /**
     * @return The rounded Y value of the vector.
     */
    public int getBlockY() {
        return (int) Math.round(this.y);
    }

    /**
     * @return The Z value of the vector.
     */
    public double getZ() {
        return z;
    }

    /**
     * @return The rounded Z value of the vector.
     */
    public int getBlockZ() {
        return (int) Math.round(this.z);
    }

    /**
     * @param x Set the X value of the vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y Set the Y value of the vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @param z Set the Z value of the vector.
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @param x Set the X value of the vector.
     */
    public Vector setVectorX(double x) {
        return new Vector((this.x += x), y, z);
    }

    /**
     * @param y Set the Y value of the vector.
     */
    public Vector setVectorY(double y) {
        return new Vector(x, (this.y += y), z);
    }

    /**
     * @param z Set the Z value of the vector.
     */
    public Vector setVectorZ(double z) {
        return new Vector(x, y, (this.z += z));
    }

    /**
     * Add the given X, Y and Z values.
     *
     * @param x The X value to be added to the current X value.
     * @param y The Y value to be added to the current Y value.
     * @param z The Z value to be added to the current Z value.
     * @return Vector with added X, Y and Z values.
     */
    public Vector add(double x, double y, double z) {
        return new Vector(
                this.x += x,
                this.y += y,
                this.z += z
        );
    }

    /**
     * Add the given unit value.
     *
     * @param unit The value to be added to current X, Y and Z values.
     * @return Vector with added unit values.
     */
    public Vector add(double unit) {
        return new Vector(
                this.x += unit,
                this.y += unit,
                this.z += unit
        );
    }

    /**
     * Add the X, Y and Z values of the given vectors.
     *
     * @param vectors The vectors which X, Y and Z values have to be added to current X, Y and Z values.
     * @return Vector with all added X, Y and Z values.
     */
    public Vector add(Vector... vectors) {
        for (Vector v : vectors) {
            this.x += v.x;
            this.y += v.y;
            this.z += v.z;
        }

        return new Vector(x, y, z);
    }

    /**
     * Subtract the given X, Y and Z values.
     *
     * @param x The X value to be subtracted from the current X value.
     * @param y The Y value to be subtracted from the current Y value.
     * @param z The Z value to be subtracted from the current Z value.
     * @return Vector with subtracted X, Y and Z values.
     */
    public Vector subtract(double x, double y, double z) {
        return new Vector(
                this.x -= x,
                this.y -= y,
                this.z -= z
        );
    }

    /**
     * Subtract the given unit value.
     *
     * @param unit The value to be subtracted from current X, Y and Z values.
     * @return Vector with subtracted unit values.
     */
    public Vector subtract(double unit) {
        return new Vector(
                this.x -= unit,
                this.y -= unit,
                this.z -= unit
        );
    }

    /**
     * Subtract the X, Y and Z values of the given vectors.
     *
     * @param vectors The vectors which X, Y and Z values have to be subtracted from current X, Y and Z values.
     * @return Vector with all subtracted X, Y and Z values.
     */
    public Vector subtract(Vector... vectors) {
        for (Vector v : vectors) {
            this.x -= v.x;
            this.y -= v.y;
            this.z -= v.z;
        }

        return new Vector(x, y, z);
    }

    /**
     * Multiply the given X, Y and Z values.
     *
     * @param x The X value to be multiplied by the current X value.
     * @param y The Y value to be multiplied by the current Y value.
     * @param z The Z value to be multiplied by the current Z value.
     * @return Vector with multiplied X, Y and Z values.
     */
    public Vector multiply(double x, double y, double z) {
        return new Vector(
                this.x *= x,
                this.y *= y,
                this.z *= z
        );
    }

    /**
     * Multiply the given unit value.
     *
     * @param unit The value to be multiplied by current X, Y and Z values.
     * @return Vector with multiplied unit values.
     */
    public Vector multiply(double unit) {
        return new Vector(
                this.x *= unit,
                this.y *= unit,
                this.z *= unit
        );
    }

    /**
     * Multiply the X, Y and Z values of the given vectors.
     *
     * @param vectors The vectors which X, Y and Z values have to be multiplied by current X, Y and Z values.
     * @return Vector with all multiplied X, Y and Z values.
     */
    public Vector multiply(Vector... vectors) {
        for (Vector v : vectors) {
            this.x *= v.x;
            this.y *= v.y;
            this.z *= v.z;
        }

        return new Vector(x, y, z);
    }

    /**
     * Divide the given X, Y and Z values.
     *
     * @param x The X value to be divided by the current X value.
     * @param y The Y value to be divided by the current Y value.
     * @param z The Z value to be divided by the current Z value.
     * @return Vector with divided X, Y and Z values.
     */
    public Vector divide(double x, double y, double z) {
        return new Vector(
                this.x /= x,
                this.y /= y,
                this.z /= z
        );
    }

    /**
     * Divide the given unit value.
     *
     * @param unit The value to be divided by current X, Y and Z values.
     * @return Vector with divided unit values.
     */
    public Vector divide(double unit) {
        return new Vector(
                this.x /= unit,
                this.y /= unit,
                this.z /= unit
        );
    }

    /**
     * Divide the X, Y and Z values of the given vectors.
     *
     * @param vectors The vectors which X, Y and Z values have to be divided by current X, Y and Z values.
     * @return Vector with all divided X, Y and Z values.
     */
    public Vector divide(Vector... vectors) {
        for (Vector v : vectors) {
            this.x /= v.x;
            this.y /= v.y;
            this.z /= v.z;
        }

        return new Vector(x, y, z);
    }

    /**
     * Get the length of the vector.
     *
     * @return The length of the vector.
     */
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Get the length, squared, of the vector.
     *
     * @return The length, squared of the vector.
     */
    public double lengthSq() {
        return x * x + y * y + z * z;
    }

    /**
     * Get the distance between this vector and another vector.
     *
     * @param other The other vector.
     * @return The distance between the vectors.
     */
    public double distance(Vector other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2.0D) +
                Math.pow(other.y - this.y, 2.0D) +
                Math.pow(other.z - this.z, 2.0D));
    }

    /**
     * Get the distance, squared, between this vector and another vector.
     *
     * @param other The other vector.
     * @return The distance, squared, between the vectors.
     */
    public double distanceSq(Vector other) {
        return Math.pow(other.x - this.x, 2.0D) + Math.pow(other.y - this.y, 2.0D) + Math.pow(other.z - this.z, 2.0D);
    }

    /**
     * Get the normalized vector, which is the vector divided by its
     * length, as a new vector.
     *
     * @return The normalized vector.
     */
    public Vector normalize() {
        return new Vector(divide(length()));
    }

    /**
     * Gets the dot product of this vector and another vector.
     *
     * @param other The other vector.
     * @return The dot product of the vectors.
     */
    public double dot(Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Gets the cross product of this vector and another vector.
     *
     * @param other The other vector.
     * @return The cross product of this vector and the other vector
     */
    public Vector cross(Vector other) {
        return new Vector(y * other.z - z * other.y, z * x - x * other.z, x * other.y - y * other.x);
    }

    /**
     * Checks to see if a vector is contained with another.
     *
     * @param min The minimum point (X, Y, and Z are the lowest).
     * @param max The maximum point (X, Y, and Z are the lowest).
     * @return True if the vector is contained within.
     */
    public boolean containedWithin(Vector min, Vector max) {
        return (x >= min.x) && (x <= max.x) && (y >= min.y) && (y <= max.y) && (z >= min.z) && (z <= max.z);
    }

    /**
     * Checks to see if a vector is contained with another.
     *
     * @param min The minimum point (X, Y, and Z are the lowest).
     * @param max The maximum point (X, Y, and Z are the lowest).
     * @return True if the vector is contained within.
     */
    public boolean containedWithinBlock(Vector min, Vector max) {
        return (getBlockX() >= min.getBlockX()) && (getBlockX() <= max.getBlockX()) &&
                (getBlockY() >= min.getBlockY()) && (getBlockY() <= max.getBlockY()) &&
                (getBlockZ() >= min.getBlockZ()) && (getBlockZ() <= max.getBlockZ());
    }

    /**
     * Clamp the Y component.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @return a new vector with the clamped Y value.
     */
    public Vector clampY(int min, int max) {
        return new Vector(x, Math.max(min, Math.min(max, y)), z);
    }

    /**
     * Floors the values of all components.
     *
     * @return a new vector with floored X, Y and Z values.
     */
    public Vector floor() {
        return new Vector(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    /**
     * Rounds all components up.
     *
     * @return a new vector with ceiled X, Y and Z values.
     */
    public Vector ceil() {
        return new Vector(Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    /**
     * Rounds all components to the closest integer.
     * Components < 0.5 are rounded down, otherwise up.
     *
     * @return a new vector with rounded X, Y and Z values.
     */
    public Vector round() {
        return new Vector(Math.floor(x + 0.5D), Math.floor(y + 0.5D), Math.floor(z + 0.5D));
    }

    /**
     * Returns a vector with the absolute values of the components of
     * this vector.
     *
     * @return a new vector with absolute X, Y and Z values.
     */
    public Vector positive() {
        return new Vector(Math.abs(this.x), Math.abs(this.y), Math.abs(this.z));
    }

    /**
     * Perform a 2D transformation on this vector and return a new one.
     *
     * @param angle Angle in degrees.
     * @param aboutX About which x coordinate to rotate.
     * @param aboutZ About which z coordinate to rotate.
     * @param translateX What to add after rotation.
     * @param translateZ What to add after rotation.
     * @return a new vector that has been transformed.
     */
    public Vector transform2D(double angle, double aboutX, double aboutZ, double translateX, double translateZ)
    {
        angle = Math.toRadians(angle);
        double x = this.x - aboutX;
        double z = this.z - aboutZ;
        double x2 = x * Math.cos(angle) - z * Math.sin(angle);
        double z2 = x * Math.sin(angle) + z * Math.cos(angle);

        return new Vector(x2 + aboutX + translateX, this.y, z2 + aboutZ + translateZ);
    }

    /**
     * Get this vector's pitch as used within the game.
     *
     * @return Pitch in radians.
     */
    public float toPitch() {
        if ((x == 0.0D) && (z == 0.0D)) {
            return getY() > 0.0D ? -90.0F : 90.0F;
        }

        double x2 = x * x;
        double z2 = z * z;
        double xz = Math.sqrt(x2 + z2);

        return (float) Math.toDegrees(Math.atan(-getY() / xz));
    }

    /**
     * Get this vector's yaw as used within the game.
     *
     * @return Yaw in radians.
     */
    public float toYaw() {
        double theta = Math.atan2(-x, z);
        double _2pi = 6.283185307179586D;

        return (float)Math.toDegrees((theta + _2pi) % _2pi);
    }

    public Location toLocation(World world) {
        return new Location(world, x, y, z);
    }

}

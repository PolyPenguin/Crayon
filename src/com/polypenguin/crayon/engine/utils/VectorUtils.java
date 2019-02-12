package com.polypenguin.crayon.engine.utils;

import com.polypenguin.crayon.engine.geometry.Vector;
import com.polypenguin.crayon.engine.geometry.selection.CuboidSelection;
import com.polypenguin.crayon.engine.geometry.selection.Selection;

import com.polypenguin.crayon.engine.geometry.selection.VectorSelection;
import org.bukkit.ChatColor;

import java.util.ArrayList;

/**
 * @author Matthias Kovacic
 *
 * Miscellaneous utilities that handle vectors related stuff.
 */
public class VectorUtils {

    /**
     * Create a custom String version of a vector.
     *
     * @param vector The used vector.
     * @return A string which contains X, Y and Z values of the vector.
     */
    public static String toString(Vector vector) {
        return ChatColor.DARK_GRAY + "[" + ChatColor.RED +
                vector.getBlockX() + ChatColor.WHITE + ", " + ChatColor.GREEN +
                vector.getBlockY() + ChatColor.WHITE + ", " + ChatColor.BLUE +
                vector.getBlockZ() + ChatColor.DARK_GRAY + "]";
    }

    /**
     * Calculate the offset of one vector to another.
     *
     * @param vector The vector the offset needs to be calculated to.
     * @param target The vector the offset needs to be calculated off.
     * @return A vector containing the offset coordinates.
     */
    public static Vector getOffset(Vector vector, Vector target) {
        return target.subtract(vector);
    }

    /**
     * Get the vectors for a cuboid created by two given vectors.
     *
     * @param selection Contains the two vectors which should be used.
     * @return An ArrayList of vectors that make up the cuboid.
     */
    public static ArrayList<Vector> getCuboid(CuboidSelection selection) {
        return getCuboidFilled(selection);
    }

    /**
     * Get the vectors of a list of selections.
     * TODO: This would be extremely helpful when doing multiple operations at once!
     *
     * @param selections The selections which vectors should be added.
     * @return An ArrayList of vectors that make up the selections.
     */
    public static ArrayList<Vector> getTrueVectors(ArrayList<Selection> selections) {
        ArrayList<Vector> vectors = new ArrayList<>();

        for (Selection selection : selections) {
            vectors.addAll(selection.getVectors(true));
        }

        return vectors;
    }

    /**
     * Algorithm for a filled cuboid.
     *
     * @param selection Contains the two vectors which should be used as a reference.
     * @return An ArrayList of vectors that make up the cuboid.
     *
     */
    private static ArrayList<Vector> getCuboidFilled(CuboidSelection selection) {
        Vector min = selection.getNativeMinimum();
        Vector max = selection.getNativeMaximum();

        ArrayList<Vector> vectors = new ArrayList<>();

        for(int x = Math.max(max.getBlockX(), min.getBlockX()); x >= Math.min(min.getBlockX(), max.getBlockX()); --x) {
            for(int y = Math.max(max.getBlockY(), min.getBlockY()); y >= Math.min(min.getBlockY(), max.getBlockY()); --y) {
                for(int z = Math.max(max.getBlockZ(), min.getBlockZ()); z >= Math.min(min.getBlockZ(), max.getBlockZ()); --z) {
                    vectors.add(new Vector(x, y, z));
                }
            }
        }

        return vectors;
    }

    /**
     * Algorithm for an unfilled cuboid.
     *
     * @param selection Contains the two vectors which should be used as a reference.
     * @return An ArrayList of vectors that make up the cuboid.
     *
     */
    public static ArrayList<Vector> getCuboidUnfilled(CuboidSelection selection) {
        ArrayList<Selection> selections = getCuboidWalls(selection);
        ArrayList<Vector> vectors = new ArrayList<>();

        for (Selection subSelection : selections) {
            vectors.addAll(subSelection.getVectors(true));
        }

        return vectors;
    }

    /**
     * Get the walls of a cuboid selection in different selections.
     *
     * @param selection The cuboid selection walls need to be made for.
     * @return The walls in an ArrayList.
     */
    public static ArrayList<Selection> getCuboidWalls(CuboidSelection selection) {
        Vector min = selection.getNativeMinimum();
        Vector max = selection.getNativeMaximum();

        ArrayList<Selection> selections = new ArrayList<>();

        selections.add(new CuboidSelection(min.setVectorX(min.getX()), max.setVectorX(min.getX())));
        selections.add(new CuboidSelection(min.setVectorX(max.getX()), max.setVectorX(max.getX())));
        selections.add(new CuboidSelection(min.setVectorZ(min.getZ()), max.setVectorZ(min.getZ())));
        selections.add(new CuboidSelection(min.setVectorZ(max.getZ()), max.setVectorZ(max.getZ())));
        selections.add(new CuboidSelection(min.setVectorY(min.getY()), max.setVectorY(min.getY())));
        selections.add(new CuboidSelection(min.setVectorY(max.getY()), max.setVectorY(max.getY())));

        return selections;
    }

    /**
     * Algorithm for a filled sphere/ellipsoid.
     *
     * @param selection Contains the origin vector which should be used as a reference.
     * @param scale Contains the scales in X, Y and Z directions.
     * @return An ArrayList of vectors that make up the sphere/ellipsoid.
     *
     */
    public static ArrayList<Vector> getEllipsoidFilled(VectorSelection selection, Vector scale) {
        ArrayList<Vector> vectors = new ArrayList<>();
        Vector origin = selection.getNativeMinimum();

        double radiusX = scale.getBlockX();
        double radiusY = scale.getBlockY();
        double radiusZ = scale.getBlockZ();

        final double invRadiusX = 1 / radiusX;
        final double invRadiusY = 1 / radiusY;
        final double invRadiusZ = 1 / radiusZ;

        final int ceilRadiusX = (int) Math.ceil(radiusX);
        final int ceilRadiusY = (int) Math.ceil(radiusY);
        final int ceilRadiusZ = (int) Math.ceil(radiusZ);

        double nextXn = 0;
        forX:
        for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;

            forY:
            for (int y = 0; y <= ceilRadiusY; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;

                forZ:
                for (int z = 0; z <= ceilRadiusZ; ++z) {
                    final double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;

                    double distanceSq = (xn * xn) + (yn * yn) + (zn * zn);
                    if (distanceSq > 1) {
                        if (z == 0) {
                            if (y == 0) {
                                break forX;
                            }
                            break forY;
                        }
                        break forZ;
                    }

                    /*
                    if (!filled) {
                        if ((nextXn * nextXn) + (yn * yn) + (zn * zn) <= 1 && (xn * xn) + (nextYn * nextYn) + (zn * zn) <= 1 && (xn * xn) + (yn * yn) + (nextZn * nextZn) <= 1) {
                            continue;
                        }
                    }
                    */

                    vectors.add(new Vector(origin.add(x, y, z)));
                    vectors.add(new Vector(origin.add(-x, y, z)));
                    vectors.add(new Vector(origin.add(x, -y, z)));
                    vectors.add(new Vector(origin.add(x, y, -z)));
                    vectors.add(new Vector(origin.add(-x, -y, z)));
                    vectors.add(new Vector(origin.add(x, -y, -z)));
                    vectors.add(new Vector(origin.add(-x, y, -z)));
                    vectors.add(new Vector(origin.add(-x, -y, -z)));
                }
            }
        }

        vectors.add(origin);

        return vectors;
    }

    /**
     * Algorithm for a filled cylinder.
     *
     * @param selection Contains the origin vector which should be used as a reference.
     * @param scale Contains the scales in X, Y and Z directions.
     * @return An ArrayList of vectors that make up the cylinder.
     *
     */
    public static ArrayList<Vector> getCylinderFilled(VectorSelection selection, Vector scale) {
        ArrayList<Vector> vectors = new ArrayList<>();
        Vector origin = selection.getNativeMinimum();

        double radiusX = scale.getBlockX();
        double height = scale.getBlockY();
        double radiusZ = scale.getBlockZ();

        radiusX += 0.5;
        radiusZ += 0.5;

        if (height == 0) {
            return null;
        } else if (height < 0) {
            height = -height;
            origin = origin.subtract(0, height, 0);
        }

        if (origin.getBlockY() < 0) {
            origin.setY(0);
        } else if (origin.getBlockY() + height - 1 > 256) {
            height = 256 - origin.getBlockY() + 1;
        }

        final double invRadiusX = 1 / radiusX;
        final double invRadiusZ = 1 / radiusZ;

        final int ceilRadiusX = (int) Math.ceil(radiusX);
        final int ceilRadiusZ = (int) Math.ceil(radiusZ);

        double nextXn = 0;
        forX: for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextZn = 0;
            forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
                final double zn = nextZn;
                nextZn = (z + 1) * invRadiusZ;

                double distanceSq = (xn * xn) + (zn * zn);
                if (distanceSq > 1) {
                    if (z == 0) {
                        break forX;
                    }
                    break forZ;
                }

                /*
                if (!filled) {
                    if ((nextXn * nextXn) + (z * z) <= 1 && (x * x) + (nextZn * nextZn) <= 1) {
                        continue;
                    }
                }
                */

                for (int y = 0; y < height; ++y) {
                    vectors.add(new Vector(origin.add(x, y, z)));
                    vectors.add(new Vector(origin.add(-x, y, z)));
                    vectors.add(new Vector(origin.add(x, y, -z)));
                    vectors.add(new Vector(origin.add(-x, y, -z)));
                }
            }
        }

        vectors.add(origin);

        return vectors;
    }

    /*
    public static ArrayList<Vector> flip(ArrayList<Vector> vectors) {

    }

    public static ArrayList<Vector> rotate(ArrayList<Vector> vectors) {

    }
    */

    public static Vector getCenter(CuboidSelection selection) {
        Vector min = selection.getNativeMinimum();
        Vector max = selection.getNativeMaximum();

        return new Vector(
                (min.getX() + max.getX()) / 2,
                (min.getY() + max.getY()) / 2,
                (min.getZ() + max.getZ()) / 2
        );
    }

    public static CuboidSelection rotate(CuboidSelection selection, double rotX, double rotY, double rotZ) {
        Vector center = getCenter(selection);

        for (Vector vector : selection.getVectors(true)) {
            vector = rotate(vector, center, rotX, rotY, rotZ);
        }

        //TODO: Return an array selection!
        return null;
    }

    private static Vector rotate(Vector vector, Vector origin, double rotX, double rotY, double rotZ) {
        vector = getRotatedVectorX(vector, origin, rotX);
        vector = getRotatedVectorY(vector, origin, rotY);
        vector = getRotatedVectorZ(vector, origin, rotZ);

        return vector;
    }

    //TODO: Optimize & Shorten
    private static Vector getRotatedVectorX(Vector vector, Vector origin, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        //translate point back to origin
        vector.setY(vector.getY() - origin.getY());
        vector.setZ(vector.getZ() - origin.getZ());

        // rotate point
        double yNew = vector.getY() * cos - vector.getZ() * sin;
        double zNew = vector.getY() * sin + vector.getZ() * cos;

        //translate point back
        vector.setY(yNew + origin.getY());
        vector.setZ(zNew + origin.getZ());

        return vector;
    }

    //TODO: Optimize & Shorten
    private static Vector getRotatedVectorY(Vector vector, Vector origin, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        //translate point back to origin
        vector.setX(vector.getX() - origin.getX());
        vector.setZ(vector.getZ() - origin.getZ());

        // rotate point
        double xNew = vector.getX() * cos - vector.getZ() * sin;
        double zNew = vector.getX() * sin + vector.getZ() * cos;

        //translate point back
        vector.setX(xNew + origin.getX());
        vector.setZ(zNew + origin.getZ());

        return vector;
    }

    //TODO: Optimize & Shorten
    private static Vector getRotatedVectorZ(Vector vector, Vector origin, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        //translate point back to origin
        vector.setX(vector.getX() - origin.getX());
        vector.setY(vector.getY() - origin.getY());

        // rotate point
        double xNew = vector.getX() * cos - vector.getY() * sin;
        double yNew = vector.getX() * sin + vector.getY() * cos;

        //translate point back
        vector.setX(xNew + origin.getX());
        vector.setY(yNew + origin.getY());

        return vector;
    }

}

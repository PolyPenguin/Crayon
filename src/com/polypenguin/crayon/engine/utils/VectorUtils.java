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

        boolean filled = true;

        double radiusX = scale.getX();
        double radiusY = scale.getY();
        double radiusZ = scale.getZ();

        radiusX += 0.5D;
        radiusY += 0.5D;
        radiusZ += 0.5D;

        double invRadiusX = 1.0D / radiusX;
        double invRadiusY = 1.0D / radiusY;
        double invRadiusZ = 1.0D / radiusZ;

        int ceilRadiusX = (int) Math.ceil(radiusX);
        int ceilRadiusY = (int) Math.ceil(radiusY);
        int ceilRadiusZ = (int) Math.ceil(radiusZ);
        double nextXn = 0.0D;

        ForX:
        for (int x = 0; x <= ceilRadiusX; ++x) {
            double xn = nextXn;
            nextXn = (double)(x + 1) * invRadiusX;
            double nextYn = 0.0D;

            for (int y = 0; y <= ceilRadiusY; ++y) {
                double yn = nextYn;
                nextYn = (double)(y + 1) * invRadiusY;
                double nextZn = 0.0D;

                for (int z = 0; z <= ceilRadiusZ; ++z) {
                    double zn = nextZn;
                    nextZn = (double)(z + 1) * invRadiusZ;
                    double distanceSq = lengthSq(xn, yn, zn);

                    if (distanceSq > 1.0D) {
                        if (z == 0) {
                            if (y == 0) {
                                break;
                            }

                            continue ForX;
                        }

                        break;
                    }

                    if (filled || lengthSq(nextXn, yn, zn) > 1.0D || lengthSq(xn, nextYn, zn) > 1.0D || lengthSq(xn, yn, nextZn) > 1.0D) {
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() + y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() + y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() - y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() + y), (origin.getBlockZ() - z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() - y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() - y), (origin.getBlockZ() - z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() + y), (origin.getBlockZ() - z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() - y), (origin.getBlockZ() - z)));
                    }
                }
            }
        }

        vectors.add(origin);

        return vectors;
    }

    /**
     * Algorithm for a unfilled sphere/ellipsoid.
     *
     * @param selection Contains the origin vector which should be used as a reference.
     * @param scale Contains the scales in X, Y and Z directions.
     * @return An ArrayList of vectors that make up the sphere/ellipsoid.
     *
     */
    public static ArrayList<Vector> getEllipsoidUnfilled(VectorSelection selection, Vector scale) {
        ArrayList<Vector> vectors = new ArrayList<>();
        Vector origin = selection.getNativeMinimum();

        double radiusX = scale.getX();
        double radiusY = scale.getY();
        double radiusZ = scale.getZ();

        radiusX += 0.5D;
        radiusY += 0.5D;
        radiusZ += 0.5D;

        double invRadiusX = 1.0D / radiusX;
        double invRadiusY = 1.0D / radiusY;
        double invRadiusZ = 1.0D / radiusZ;

        int ceilRadiusX = (int) Math.ceil(radiusX);
        int ceilRadiusY = (int) Math.ceil(radiusY);
        int ceilRadiusZ = (int) Math.ceil(radiusZ);
        double nextXn = 0.0D;

        ForX:
        for (int x = 0; x <= ceilRadiusX; ++x) {
            double xn = nextXn;
            nextXn = (double)(x + 1) * invRadiusX;
            double nextYn = 0.0D;

            for (int y = 0; y <= ceilRadiusY; ++y) {
                double yn = nextYn;
                nextYn = (double)(y + 1) * invRadiusY;
                double nextZn = 0.0D;

                for (int z = 0; z <= ceilRadiusZ; ++z) {
                    double zn = nextZn;
                    nextZn = (double)(z + 1) * invRadiusZ;
                    double distanceSq = lengthSq(xn, yn, zn);

                    if (distanceSq > 1.0D) {
                        if (z == 0) {
                            if (y == 0) {
                                break;
                            }

                            continue ForX;
                        }

                        break;
                    }

                    if (lengthSq(nextXn, yn, zn) > 1.0D || lengthSq(xn, nextYn, zn) > 1.0D || lengthSq(xn, yn, nextZn) > 1.0D) {
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() + y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() + y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() - y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() + y), (origin.getBlockZ() - z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() - y), (origin.getBlockZ() + z)));
                        vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() - y), (origin.getBlockZ() - z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() + y), (origin.getBlockZ() - z)));
                        vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() - y), (origin.getBlockZ() - z)));
                    }
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

        double height = scale.getBlockY();
        double radiusX = scale.getX();
        double radiusZ = scale.getZ();

        radiusX += 0.5;
        radiusZ += 0.5;

        if (height == 0) {
            return vectors;
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
        forX:
        for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextZn = 0;

            forZ:
            for (int z = 0; z <= ceilRadiusZ; ++z) {
                final double zn = nextZn;
                nextZn = (z + 1) * invRadiusZ;

                double distanceSq = lengthSq(xn, zn);
                if (distanceSq > 1) {
                    if (z == 0) {
                        break forX;
                    }

                    break forZ;
                }

                /*
                if (!filled) {
                    if (lengthSq(nextXn, zn) <= 1 && lengthSq(xn, nextZn) <= 1) {
                        continue;
                    }
                }
                */

                for (int y = 0; y < height; ++y) {
                    vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() + y), (origin.getBlockZ() + z)));
                    vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() + y), (origin.getBlockZ() + z)));
                    vectors.add(new Vector((origin.getBlockX() + x), (origin.getBlockY() + y), (origin.getBlockZ() - z)));
                    vectors.add(new Vector((origin.getBlockX() - x), (origin.getBlockY() + y), (origin.getBlockZ() - z)));
                }
            }
        }

        vectors.add(origin);

        return vectors;
    }

    private static double lengthSq(double x, double z) {
        return x * x + z * z;
    }

    private static double lengthSq(double x, double y, double z) {
        return x * x + y * y + z * z;
    }

}

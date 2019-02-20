package com.polypenguin.crayon.engine.utils;

/**
 * @author Matthias Kovacic
 */
public class StringUtils {

    /**
     * Extract and return the first spaced number
     * within a string.
     *
     * @param str The string to be checked.
     * @return The first number found.
     */
    public static int extractNumber(final String str) {
        if (str == null || str.isEmpty())  {
            return -1;
        }

        StringBuilder builder = new StringBuilder();

        boolean found = false;

        for (char c : str.toCharArray()) {
            if(Character.isDigit(c)){
                builder.append(c);
                found = true;
            } else if(found){
                // If we already found a digit before and this char is not a digit, stop looping
                break;
            }
        }

        int i = -1;

        try {
            i = Integer.parseInt(builder.toString());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return i;
    }

}

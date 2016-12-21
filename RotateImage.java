/**
 * @author Simon Dvorak
 */
public class RotateImage {

    /**
     * "Awesome" - prints an multidimensional array.
     * @param array - array that I want to print.
     */
    public void printMultiArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Prints a coordinates.
     * Only for development.
     * @param message - meaning of the coordinates.
     * @param x - coordinate "x".
     * @param y - coordinate "y".
     */
    private void printCoord(String message, int x, int y) {
        System.out.println(message + " - x: " + x + ", " + "y: " + y);
    }

    /**
     * Compute how many squares are in the main square included the main square.
     * @param length - length of the square.
     * @return number of inner squares.
     */
    public int getNumOfSquares(int length) {
        int totalSquares = 1;
        // Check if there are at least two more squares.
        for (int k = length-2; k >= 1; k -= 2) {
            // Compute the perimeter of the actual square.
            int o = 4 * k - 4;

            if (o != 0)
                totalSquares++;
        }

        return totalSquares;
    }
    
    /**
     * Check if image is square.
     * @param obj
     * @return true -> image is square, false -> image is not square.
     */
    public boolean isSquare(int[][] obj) {
        int x = obj.length;
        for (int[] ch : obj) {
            if (x != ch.length)
                return false;
        }
        return true;
    }    

    /**
     * Main function of this program.
     * Rotate multidimensional array 90 degrees right.
     * @param image - abstract "image" divided into "lines" of the multidimensional array.
     * @return 90 degrees rotated multidimensional array.
     */
    public int[][] rotateImage(int[][] image) {
        if (!isSquare(image)) {
            System.out.println("Image is not squared.");
            return image;
        }        
        
        // Number of inner
        int pc = getNumOfSquares(image.length);

        int offset = 0;

        for (int i = 0; i < pc; i++) {
            for (int j = i; j < image[i].length - offset - 1; j++) {
                // top -> right
                int prev1 = image[j][image[i].length - 1 - i];
                image[j][image[i].length - 1 - i] = image[i][j];
                // right -> bottom
                int prev2 = image[image[i].length - 1 - i][image.length - 1 - j];
                image[image[i].length - 1 - i][image.length - 1 - j] = prev1;
                // bottom -> left
                int prev3 = image[image.length - 1 - j][i];
                image[image.length - 1 - j][i] = prev2;
                // left -> top
                image[i][j] = prev3;
            }
            offset += 1;
        }

        System.out.println();
        return image;
    }

    public static void main(String[] args) {
        // Test cases:
        RotateImage rotateImage = new RotateImage();

        int[][] image = new int[][] {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 24},
                {25, 26, 27, 28, 29, 30, 31, 32},
                {33, 34, 35, 36, 37, 38, 39, 40},
                {41, 42, 43, 44, 45, 46, 47, 48},
                {49, 50, 51, 52, 53, 54, 55, 56},
                {56, 57, 58, 59, 60, 61, 62, 63}
        };

        System.out.println("--------------0°--------------");
        rotateImage.printMultiArray(image);

        image = rotateImage.rotateImage(image);

        System.out.println("--------------90°-------------");
        rotateImage.printMultiArray(image);

        image = rotateImage.rotateImage(image);

        System.out.println("-------------180°-------------");
        rotateImage.printMultiArray(image);

        image = rotateImage.rotateImage(image);

        System.out.println("-------------270°-------------");
        rotateImage.printMultiArray(image);

        image = rotateImage.rotateImage(image);

        System.out.println("----------360° -> 0°----------");
        rotateImage.printMultiArray(image);
    }

}

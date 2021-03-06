package tgits;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int lightX = in.nextInt(); // the X position of the light of power
        int lightY = in.nextInt(); // the Y position of the light of power
        int initialTX = in.nextInt(); // Thor's starting X position
        int initialTY = in.nextInt(); // Thor's starting Y position

        int dx = initialTX - lightX;
        int dy = initialTY - lightY;
        String horizontalMove = "";
        String verticalMove = "";

        // game loop
        while (true) {
            int remainingTurns = in.nextInt(); // The remaining amount of turns Thor can move. Do not remove this line.

            if (dx > 0) {
                dx = dx + 1;
                horizontalMove = "W";
            } else if (dx < 0) {
                dx = dx - 1;
                horizontalMove = "E";
            } else {
                horizontalMove = "";
            }

            if (dy > 0) {
                dy = dy - 1;
                verticalMove = "N";
            } else if (dy < 0) {
                dy = dy + 1;
                verticalMove = "S";
            } else {
                verticalMove = "";
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            // A single line providing the move to be made: N NE E SE S SW W or NW
            System.out.println(verticalMove + horizontalMove);
        }
    }
}

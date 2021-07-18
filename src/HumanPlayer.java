/**
 * This class is subclass of Player class.
 */

import java.util.Scanner;

public class HumanPlayer extends Player {

    /**
     * Create a new player
     * @param color player color
     * @param name name of player
     */
    public HumanPlayer(String color, String name) {
        super(color, name);
    }

    /**
     * move a disc on player's turn.
     * @param board game board
     * @param player game player
     */
    public void move (Board board, HumanPlayer player) {
        Scanner scanner = new Scanner(System.in);
        String playerMovement;
        do {
            playerMovement = scanner.nextLine();
            if (board.validMove(playerMovement)) {
                board.move(player, playerMovement,board.getMatrix());
                break;
            } else {
                System.out.println("Invalid movement!");
                System.out.println("try again");
            }
        } while (!board.validMove(playerMovement));
        board.printMatrix();
    }
}

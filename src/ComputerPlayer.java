/**
 * This class is subclass of Player.
 */
public class ComputerPlayer extends Player {
    private int level;
    /**
     * Create a new player
     * @param level game level
     */
    public ComputerPlayer(int level) {
        super("white","computer");
        this.level = level;
    }

    /**
     * get level of the game
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * move a disc on computer's turn
     * @param board game board
     * @param player computer
     */
    public void move(Board board, ComputerPlayer player) {
        board.computerMove(player);
        board.printMatrix();
    }
}

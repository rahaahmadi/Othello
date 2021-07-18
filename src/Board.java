/**
 * This class represents a two person game of Othello.
 * The game starts on a 2D board with two black and two white discs on diagonals in the
 * centre of the board.
 * The game ends when the board is filled with discs.
 * @author Raha Ahmadi
 * @version 0.0
 */

import java.util.HashSet;
import java.util.Random;

public class Board {
    private String[][] matrix;
    private HashSet<String> validMoves;

    /**
     * Constructor to create a new board
     */
    public Board() {
        matrix = new String[18][18];
    }

    public String[][] getMatrix() {
        return matrix;
    }

    /**
     * Initialize 8*8 matrix to show the board.
     * Puts 2 BLACK Disks and 2 WHITE Disks in center of Board.
     */
    public void initialMatrix() {
        String k = "1";
        char ch  =k.charAt(0);
        for(int i  = 0; i < 18; i++) {
            if((i != 0 ) && (i % 2 == 0)) {
                matrix[i][0] = k;
                ch++;
                k = k.replace(k.charAt(0), ch);

            }
            else
                matrix[i][0] =" ";
        }
        String str = "A";
        ch = str.charAt(0);
        for(int j = 0; j < 18; j++) {
            if((j != 0 ) && (j % 2 == 0)) {
                matrix[0][j] = str;
                ch++;
                str = str.replace(str.charAt(0), ch);
            }
            else
                matrix[0][j]= " ";
        }
        for(int i = 1; i < 18; i++) {
            for(int j = 1; j < 18; j++) {
                if((i % 2 != 0) && (j % 2 != 0))
                    matrix[i][j] = "*";
                if((i %2 == 0)&&(j % 2) == 0)
                    matrix[i][j]= " ";
                if((i % 2 != 0) && (j % 2 == 0))
                    matrix[i][j]= "-";
                if((i %2 == 0) && (j %2 !=0))
                    matrix[i][j]="|";
            }
        }
        // "\u25EF" is black disc and "\u25CF" is white disc.
        matrix[8][8] = "\u25CB";
        matrix[8][10] = "\u25CF";
        matrix[10][8] = "\u25CF";
        matrix[10][10] = "\u25CB";
    }

    /**
     * convert x and y coordinates to string
     * @param x x of coordinate
     * @param y y coordinate
     * @return string
     */
    public String changeToString(int x, int y) {
        String row = matrix[x][0];
        String column = matrix[0][y];
        return row + " " + column;
    }

    /**
     * get player legal moves.
     * @param player game player
     */
    public HashSet getValidMoves(Player player ) {
        validMoves = new HashSet<>();
        for (int i  = 2; i < 18; i += 2) {
            for (int j = 2; j < 18; j += 2) {
                if (matrix[i][j].equals(" ")) {
                    if ((i < 14) ) {
                        if (matrix[i+2][j].equals(player.getOppositeColor())) {
                            for (int k = i+4; k < 18 ; k += 2) {
                                if (matrix[k][j].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[k][j].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((i > 4) ) {
                        if (matrix[i-2][j].equals(player.getOppositeColor())) {
                            for (int k = i-4; k > 0; k -= 2) {
                                if (matrix[k][j].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[k][j].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((j < 14) ) {
                        if (matrix[i][j+2].equals(player.getOppositeColor())) {
                            for (int k = j+4; k < 18 ; k += 2) {
                                if (matrix[i][k].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[i][k].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((j > 4)) {
                        if (matrix[i][j-2].equals(player.getOppositeColor())) {
                            for (int k = j-4; k > 0; k -= 2) {
                                if (matrix[i][k].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[i][k].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((i < 14) && (j < 14)) {
                        if (matrix[i+2][j+2].equals(player.getOppositeColor())) {
                            for (int k = i+4, p = j+4; k < 18 && p < 18; k +=2, p +=2) {
                                if (matrix[k][p].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[k][p].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((i > 4) && (j > 4) ) {
                        if (matrix[i-2][j-2].equals(player.getOppositeColor())) {
                            for (int k = i-4, p = j-4; k > 0 && p > 0; k -=2, p -=2) {
                                if (matrix[k][p].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[k][p].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((i < 16) && (j > 4)) {
                        if (matrix[i+2][j-2].equals(player.getOppositeColor())) {
                            for (int k = i+4, p = j-4; k < 18 && p > 0; k += 2, p -=2) {
                                if (matrix[k][p].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[k][p].equals(" "))
                                    break;
                            }
                        }
                    }
                    if ((j < 16) && (i > 4)) {
                        if (matrix[i-2][j+2].equals(player.getOppositeColor())) {
                            for (int k = i-4, p = j+4; k > 0 && p < 18; k -=2, p +=2) {
                                if (matrix[k][p].equals(player.getColorCode())) {
                                    validMoves.add(changeToString(i,j));
                                    break;
                                }
                                else if (matrix[k][p].equals(" "))
                                    break;
                            }
                        }
                    }
                }
            }
        }
        return validMoves;
    }

    /**
     * check if disc can move or not
     * @param player game player
     * @return true or false
     */
    public boolean canMove (Player player) {
        return validMoves.size() != 0;
    }

    /**
     * check if a coordinate is valid or not.
     * @param movement coordinate of move
     * @return true or false
     */
    public boolean validMove (String movement) {
        return validMoves.contains(movement);
    }

    /**
     * Places a Disk on a Board.
     * @param player game player
     * @param move coordinate of move in string
     */
    public void move (Player player, String move, String[][] matrix) {
        int x  = 0 , y = 0;
        for (int i = 2; i < 18; i += 2)
            for (int j = 2; j < 18; j += 2)
                if (changeToString(i,j).equals(move)) {
                    x = i;
                    y = j;
                }
        if ((x < 14) ) {
            if (matrix[x+2][y].equals(player.getOppositeColor())) {
                for (int k = x+4; k < 18 ; k += 2) {
                    if (matrix[k][y].equals(player.getColorCode())) {
                        for (int j = x; j < k; j += 2)
                            matrix[j][y] = player.getColorCode();
                        break;

                    }
                    else if (matrix[k][y].equals(" "))
                        break;
                }
            }
        }
        if ((x > 4) ) {
            if (matrix[x-2][y].equals(player.getOppositeColor())) {
                for (int k = x-4; k > 0; k -= 2) {
                    if (matrix[k][y].equals(player.getColorCode())) {
                        for (int j = x; j > k; j -=2)
                            matrix[j][y] = player.getColorCode();
                        break;
                    }
                    else if (matrix[k][y].equals(" "))
                        break;
                }
            }
        }
        if ((y < 14) ) {
            if (matrix[x][y+2].equals(player.getOppositeColor())) {
                for (int k = y+4; k < 18 ; k += 2) {
                    if (matrix[x][k].equals(player.getColorCode())) {
                        for (int j = y; j < k; j += 2)
                            matrix[x][j] = player.getColorCode();
                        break;
                    }
                    else if (matrix[x][k].equals(" "))
                        break;
                }
            }
        }
        if ((y > 4)) {
            if (matrix[x][y-2].equals(player.getOppositeColor())) {
                for (int k = y-4; k > 0; k -= 2) {
                    if (matrix[x][k].equals(player.getColorCode())) {
                        for (int j = y; j > k; j -=2)
                            matrix[x][j] = player.getColorCode();
                        break;
                    }
                    else if (matrix[x][k].equals(" "))
                        break;
                }
            }
        }
        if ((x < 14) && (y < 14)) {
            if (matrix[x+2][y+2].equals(player.getOppositeColor())) {
                for (int k = x+4, p = y+4; k < 18 && p < 18; k +=2, p +=2) {
                    if (matrix[k][p].equals(player.getColorCode())) {
                        for (int i = x, j = y; i < k && j < p; i += 2, j += 2)
                            matrix[i][j] = player.getColorCode();
                        break;
                    }
                    else if (matrix[k][p].equals(" "))
                        break;
                }
            }
        }
        if ((x > 4) && (y > 4) ) {
            if (matrix[x-2][y-2].equals(player.getOppositeColor())) {
                for (int k = x-4, p = y-4; k > 0 && p > 0; k -=2, p -=2) {
                    if (matrix[k][p].equals(player.getColorCode())) {
                        for (int i = x, j = y; i > k && j >p; i -=2, j -=2)
                            matrix[i][j] = player.getColorCode();
                        break;
                    }
                    else if (matrix[k][p].equals(" "))
                        break;
                }
            }
        }
        if ((x < 16) && (y > 4)) {
            if (matrix[x+2][y-2].equals(player.getOppositeColor())) {
                for (int k = x+4, p = y-4; k < 18 && p > 0; k += 2, p -=2) {
                    if (matrix[k][p].equals(player.getColorCode())) {
                        for (int i = x, j= y; i < k && j > p; i += 2, j -=2)
                            matrix[i][j] = player.getColorCode();
                        break;
                    }
                    else if (matrix[k][p].equals(" "))
                        break;
                }
            }
        }
        if ((x > 4) && (y < 16)) {
            if (matrix[x-2][y+2].equals(player.getOppositeColor())) {
                for (int k = x-4, p = y+4; k > 0 && p < 18; k -=2, p +=2) {
                    if (matrix[k][p].equals(player.getColorCode())) {
                        for (int i = x, j = y; i > k && j < p; i -=2, j += 2)
                            matrix[i][j] = player.getColorCode();
                        break;
                    }
                    else if (matrix[k][p].equals(" "))
                        break;
                }
            }
        }
    }

    /**
     * get score of player.
     * @param player game player
     */
    public void score (Player player) {
        int count = 0;
        for (int i =2; i < 18; i +=2) {
            for (int j = 2; j < 18; j += 2) {
                if (matrix[i][j].equals(player.getColorCode()))
                    count++;
            }
        }
        player.setScore(count);
    }

    /**
     * computer movement.
     * @param computerPlayer player
     */
    public void computerMove (ComputerPlayer computerPlayer) {
        if (computerPlayer.getLevel() == 1) {
            int i = 0;
            int item = new Random().nextInt(validMoves.size());
            for (String movement : validMoves)
                if (i == item) {
                    move(computerPlayer, movement,matrix);
                    break;
                }
                else
                    i++;
        }
        if (computerPlayer.getLevel() == 2) {
            boolean flag = false;
            for (String movement : validMoves) {
                char c = movement.charAt(0);
                char ch = movement.charAt(2);
                if ((c =='1' && ch =='A') || (c =='1' && ch =='H') || (c =='8' && ch =='A') || (c =='8' && ch=='H')) {
                    move(computerPlayer,movement,matrix);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                for (String movement : validMoves) {
                    char c = movement.charAt(0);
                    char ch = movement.charAt(2);
                    if (c == '1' || ch == 'A' || c == '8' || ch == 'H') {
                        move(computerPlayer,movement,matrix);
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                int max = 0;
                String str = null;
                for (String movement : validMoves) {
                    String[][] m = new String[18][18];
                    for (int i = 0; i < 18; i++)
                        for (int j = 0; j < 18; j++)
                            m[i][j] = matrix[i][j];
                    move(computerPlayer,movement,m);
                    score(computerPlayer);
                    if (computerPlayer.getScore() > max) {
                        max = computerPlayer.getScore();
                        str = movement;
                    }
                }
                move(computerPlayer,str,matrix);
            }
        }
    }

    /**
     * make a score board to show scores of black and white disc.
     */
    public void scoreBoard() {
        int WScore = 0 , BScore = 0;
        for (int i =2; i < 18; i +=2) {
            for (int j = 2; j < 18; j += 2) {
                if (matrix[i][j].equals("\u25CB"))
                    WScore++;
                if (matrix[i][j].equals("\u25CF"))
                    BScore++;
            }
        }
        System.out.println("Black: " + BScore + " White: " + WScore);
    }

    /**
     * game of over when there is not empty spot to move.
     * @return true when the game is over.
     */
    public boolean gameIsOver () {
        int count = 0;
        for (int i = 2; i < 18; i += 2) {
            for (int j = 2; j < 18; j += 2) {
                if (matrix[i][j].equals(" "))
                    count++;
            }
        }
        return count == 0;
    }

    public void winner(Player player1, Player player2) {
        if (player1.getScore() > player2.getScore())
            System.out.println(player1.getColor() + " is winner");
        else if (player1.getScore() < player2.getScore())
            System.out.println(player2.getColor() + " is winner");
        else
            System.out.println("No one win");
    }

    /**
     * print board.
     */
    public void printMatrix() {
        scoreBoard();
        for(int i = 0; i < 18; i++) {
            for(int j = 0; j < 18; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();

        }
    }
}

import java.util.Scanner;

public class GameTest {
    static void options () {
        System.out.println("1: two players");
        System.out.println("2: play with computer");
        System.out.println("3: exit");
    }
    public static void main (String[] args) {
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        while (true) {
            options();
            int num = input.nextInt();
            if (num == 1) {
                board.initialMatrix();
                board.printMatrix();
                HumanPlayer player1 = new HumanPlayer("black","player1");
                HumanPlayer player2 = new HumanPlayer("white","player2");
                System.out.println(player1.getName() + " is black");
                System.out.println(player2.getName() + " is white");
                System.out.println("Black Moves first!");
                outer:
                while (!board.gameIsOver()) {
                    board.getValidMoves(player1);
                    if (board.canMove(player1)) {
                        System.out.println(player1.getName() + "'s turn");
                        player1.move(board,player1);
                    }
                    else {
                        board.getValidMoves(player2);
                        if (board.getValidMoves(player2).size() == 0)
                            break;
                        else {
                            System.out.println("pass");
                            System.out.println(player2.getName() + " should move");
                            player2.move(board,player2);
                            continue;
                        }

                    }
                    while (true) {
                        board.getValidMoves(player2);
                        if (board.canMove(player2)) {
                            System.out.println(player2.getName() + "'s turn");
                            player2.move(board,player2);
                            break;
                        }
                        else {
                            board.getValidMoves(player1);
                            if (board.getValidMoves(player1).size() == 0)
                                break outer;
                            else {
                                System.out.println("pass");
                                System.out.println(player1.getName() + " should move");
                                player1.move(board,player1);
                            }
                        }
                    }
                }
                board.score(player1);
                board.score(player2);
                System.out.println("Game is over");
                board.winner(player1,player2);
            }
            else if (num == 2) {
                System.out.println("Enter the level (1 or 2)");
                int level = input.nextInt();
                board.initialMatrix();
                board.printMatrix();
                if (level == 1 || level == 2) {
                    ComputerPlayer computerPlayer = new ComputerPlayer(level);
                    HumanPlayer player = new HumanPlayer("black","Human");
                    outer:
                    while (!board.gameIsOver()) {
                        board.getValidMoves(player);
                        if (board.canMove(player)) {
                            System.out.println("Your turn");
                            player.move(board, player);
                        }
                        else {
                            board.getValidMoves(computerPlayer);
                            if (board.getValidMoves(computerPlayer).size() == 0)
                                break;
                            else {
                                System.out.println("pass");
                                System.out.println("Computer should move");
                                computerPlayer.move(board, computerPlayer);
                                continue;
                            }
                        }
                        while (true) {
                            board.getValidMoves(computerPlayer);
                            if (board.canMove(computerPlayer)) {
                                System.out.println("computer's turn");
                                computerPlayer.move(board, computerPlayer);
                                break;
                            }
                            else {
                                board.getValidMoves(player);
                                if (board.getValidMoves(player).size() == 0)
                                    break outer;
                                else {
                                    System.out.println("pass");
                                    System.out.println("You should move");
                                    player.move(board, player);
                                }

                            }
                        }
                    }
                    board.score(player);
                    board.score(computerPlayer);
                    System.out.println("Game is over");
                    board.winner(computerPlayer,player);
                }
            }
            else if (num == 3)
                break;
        }
    }
}

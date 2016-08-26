/*
 * ITCS-6150 Final Project (Summer II 2016)
 * 
 * Created by Group:
 * *****************
 * Aswathi Radhakrishnan
 * Mohammed Jalal Hemidach
 * Pranathi Lethakula
 * 
 * Professor: Agnieszka Dardzinska
 * */

import javax.swing.*;
import java.util.Arrays;

public class TicTacToe {
    static int[] board = new int[9];
    static GameBoard window;
    static TicTacToe game;

    public static void main(String[] args) {
    	
    	window = new GameBoard();
		window.frmTictactoe.setVisible(true);
		
        game = new TicTacToe();
        Arrays.fill(board, 0);
    }

    public void  playerMove(int move){
    	board[move] = -1;
    }
    
    public void checkForWin(){
    	String message;
    	  switch (win(board)) {
          case 0:
        	  if (isBoardFull()){
        		  message = "It's a TIE! \nWould you like to play again?";
            	  message(message);
        	  }
              break;
          case 1:
        	  message = "Oops! You lost! \nWould you like to play again?";
              message(message);
              break;
          case 2:
        	  message = "Congrats! You won! \nWould you like to play again?";
        	  message(message);
              break;
              }
    	  }

    /**
     * Displays Exit Option dialog box
     * @param message Message to be displayed in the dialog box
     */
    private void message (String message){
    	int choice = JOptionPane.showOptionDialog(window.frmTictactoe, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
    	if(choice == 0){
    		window.restGameBoard();
            game = new TicTacToe();
            Arrays.fill(board, 0);
    	}else if(choice == 1){
    		System.exit(0);
    	}
    }

    /**
     * Checks if all the tiles have been played on
     * @return true if board is full
     *         false otherwise
     */
    private boolean isBoardFull() {
    	for(int entry : board) {
    		if (entry == 0) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Computes AI's move which in turn calls minimax
     * @return best move which would result in a win or tie for the computer
     */
    public int computerMove() {
        int move = -1;
        int score = -2;

        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = 1;
                int tempScore = -minimax(board, -1);
                board[i] = 0;
                if (tempScore > score) {
                    score = tempScore;
                    move = i;
                }
            }
        }
        board[move] = 1;
        return move;
    }

    /**
     * Minimax algorithm implementation
     * @param board Tile array
     * @param player Player whose turn is next
     * @return best position for the computer's next move
     */
    private int minimax(int[] board, int player) {
        int winner = win(board);
        if (winner != 0)
            return winner * player;

        int move = -1;
        int score = -2;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) {
                board[i] = player;
                int thisScore = -minimax(board, player * -1);
                if (thisScore > score) {
                    score = thisScore;
                    move = i;
                }
                board[i] = 0;
            }
        }
        if (move == -1) return 0;
        return score;
    }

    /**
     * Checks for a win
     * @param board Tile array
     * @return player value if there is a win
     *         0 in case of a tie
     */
    private int win(int[] board) {
        int wins[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int i = 0; i < 8; ++i) {
            if(board[wins[i][0]] != 0 &&
                    board[wins[i][0]] == board[wins[i][1]] &&
                    board[wins[i][0]] == board[wins[i][2]])
                return board[wins[i][2]];
        }
        return 0;
    }


}

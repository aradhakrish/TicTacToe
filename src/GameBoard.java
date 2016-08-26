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
import java.awt.*;

public class GameBoard extends javax.swing.JApplet {

	public JFrame frmTictactoe;
	public JButton[] tiles = new JButton[9];

	public boolean isPlayersTurn = true;
	TicTacToe ticTacToe = new TicTacToe();
	public int turn = 0;

	public GameBoard() {
		initialize();
	}

	private void initialize() {
        JButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8;

        frmTictactoe = new JFrame();
		frmTictactoe.setTitle("Welcome to Tic-Tac-Toe!");
		frmTictactoe.setBounds(500, 150, 225, 245);
		frmTictactoe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmTictactoe.getContentPane().setLayout(null);
		
		button_0 = new JButton();
        tiles[0] = button_0;
        processButton(button_0, 0);
        button_0.setBounds(0, 0, 75, 75);

        button_1 = new JButton();
        tiles[1] = button_1;
		button_1.setBounds(75, 0, 75, 75);
        processButton(button_1, 1);

        button_2 = new JButton();
        processButton(button_2, 2);
        tiles[2] = button_2;
		button_2.setBounds(150, 0, 75, 75);

		button_3 = new JButton();
        processButton(button_3, 3);
        tiles[3] = button_3;
		button_3.setBounds(0, 75, 75, 75);

		button_4 = new JButton();
        processButton(button_4, 4);
        tiles[4] = button_4;
		button_4.setBounds(75, 75, 75, 75);

		button_5 = new JButton();
        processButton(button_5, 5);
        tiles[5] = button_5;
		button_5.setBounds(150, 75, 75, 75);

		button_6 = new JButton();
        processButton(button_6, 6);
        tiles[6] = button_6;
		button_6.setBounds(0, 150, 75, 75);

		button_7 = new JButton();
        processButton(button_7, 7);
        tiles[7] = button_7;
		button_7.setBounds(75, 150, 75, 75);

		button_8 = new JButton();
        processButton(button_8, 8);
        tiles[8] = button_8;
		button_8.setBounds(150, 150, 75, 75);
	}

    private void processButton(JButton button, int id) {
        button.setFont(new Font("Tahoma", Font.BOLD, 40));
        button.addActionListener(e -> {
            turn++;
            button.setEnabled(false);
            if(isPlayersTurn){
                button.setText("X");
                button.setBackground(Color.gray);
                ticTacToe.playerMove(id);
                isPlayersTurn = false;
                compAction();
            }else{
                button.setText("O");
                button.setBackground(Color.BLACK);
            }
            ticTacToe.checkForWin();
        });
        frmTictactoe.getContentPane().add(button);
    }

	public void compAction(){
		if(turn < 9){
			int move = ticTacToe.computerMove();
			tiles[move].doClick();
			isPlayersTurn = true;
		}
	}
	
	public void restGameBoard(){
		for (JButton tile : tiles){
			turn = 0;
			isPlayersTurn = true;
			tile.setBackground(null);
			tile.setText(null);
			tile.setEnabled(true);
		}
	}
}

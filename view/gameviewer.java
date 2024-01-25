package view;

import javax.swing.*;

import model.ChessPiece;
import model.GameBoard;
import model.GameBoard.BobTheBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class gameviewer{
    public static void main(String[] args) {
        //Builder boardBuilder = new Builder();
        GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        gameBoard.zaStarter(new BobTheBuilder());
        HashMap<Integer, ChessPiece> piecePositions = gameBoard.getPiecePosition();
        System.out.println(piecePositions); 
        Color boardColor1 = new Color(231, 237, 159);
        Color boardColor2 = new Color(219, 164, 86); 
        SwingUtilities.invokeLater(() ->{
            JFrame frame = new JFrame("Tabalia chess thing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(6,7));

            ImageIcon YellowArrowUP = new ImageIcon("view/YellowArrowUP.png");
            ImageIcon YellowArrowDOWN = new ImageIcon("view/YellowArrowDOWN.png");
            ImageIcon BlueArrowUP = new ImageIcon("view/BlueArrowUP.png");
            ImageIcon BlueArrowDOWN = new ImageIcon("view/BlueArrowDOWN.png");
            //Generate the board
            String piece;
            for (int i = 0; i < 42; i++) {
                JButton button;
                if (piecePositions.containsKey(i) && piecePositions.get(i) != null) {
                    piece = piecePositions.get(i).toString();
                    button = new JButton();
                    button.addActionListener(new ButtonClickListener());
                    switch (piece) {
                        case "p":
                            button.setIcon(BlueArrowDOWN);
                            break;
                        case "P":
                            button.setIcon(YellowArrowUP);
                            break;
                        default:
                            break;
                    }
                    frame.add(button);
                }
                else{
                    button = new JButton();
                    button.addActionListener(new ButtonClickListener());
                    frame.add(button);
                }
                if (i % 2 == 0) {
                    button.setBackground(boardColor1);
                }
                else{
                    button.setBackground(boardColor2);
                }
            }

            frame.pack();
            frame.setVisible(true);
        });
        

    }


    public static class ButtonClickListener implements ActionListener{
        private Color previousColor;
        public void actionPerformed(ActionEvent e){
            JButton sourceButton = (JButton) e.getSource();
            sourceButton.setSelected(!sourceButton.isSelected());
            if (sourceButton.isSelected()) {
                previousColor = sourceButton.getBackground();
                sourceButton.setBackground(Color.BLUE);
            } else {
                sourceButton.setBackground(previousColor);
            }
        }
    }
    
}
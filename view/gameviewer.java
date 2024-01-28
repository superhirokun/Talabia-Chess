package view;
import javax.swing.*;

import control.gamecontroller;
import model.ChessPiece;
import model.GameBoard;
import model.GameBoard.BobTheBuilder;
import model.BoardLogic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class gameviewer{
    private GameBoard gameBoard;
    private gamecontroller gameController;
    JFrame frame = new JFrame("Tabalia chess");
        public static void main(String[] args) {
        gameviewer gameView = new gameviewer();
        int turnBruh = 0;
        gameView.gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        gameView.displayGame(gameView.gameBoard, turnBruh);
        boolean sunPieceCaptured = false;  
while (!gameView.gameBoard.isSunPieceCaptured()) {
            BoardLogic.turnCounter(turnBruh); //increments counter 
            sunPieceCaptured = gameView.gameBoard.isSunPieceCaptured(); //check if any sunpiece is captured (not sure if this works)
            BoardLogic.zaSwitcher(); //check if next turn requires switching between plus and time piece
            if (sunPieceCaptured == true) {
            //logic for when the sunPiece is captured
            JOptionPane.showMessageDialog(null,"Game Over loosah","Game Over",JOptionPane.INFORMATION_MESSAGE);
            break;
            }
            else{
                //logic for when its not captured(game con)
            }
        }    
    }

    public void displayGame(GameBoard gameBoard, int turnCheck){
                if (turnCheck == 0) {
                    gameBoard.zaStarter(new BobTheBuilder()); //prints starter board
                }
//gameBoard.
                HashMap<Integer, ChessPiece> piecePositions = gameBoard.getPiecePosition();
                System.out.println(piecePositions); 
                //set colour of the board to look like a chess board
                Color boardColor1 = new Color(231, 237, 159);
                Color boardColor2 = new Color(219, 164, 86); 
                SwingUtilities.invokeLater(() ->{
                    //JFrame frame = new JFrame("Tabalia chess"); //main application frame i put this on top but commenting it just incase it breaks later
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                    JPanel options = new JPanel(); //panel for the game options
                    options.setBackground(Color.LIGHT_GRAY);
                    options.setPreferredSize(new Dimension(frame.getWidth(),30));
                    JButton save = new JButton("Save Game");
                    JButton load = new JButton("Load Previous Game");
                    JButton quit = new JButton("Quit Game");
                    
                    quit.addActionListener(new WindowCloseButton());
                    options.add(save);
                    options.add(load);
                    options.add(quit);
                    frame.add(options, BorderLayout.NORTH);
                    
                    //panel with the chess board
                    JPanel board = new JPanel();
                    board.setLayout(new GridLayout(6,7));
        
                    //yellow pieces
                    ImageIcon yellowPoint = new ImageIcon("view/yellowPoint.png");
                    ImageIcon rotatedYPoint = rotateImage(yellowPoint, Math.PI);
                    ImageIcon yellowHourglass = new ImageIcon("view/yellowHourglass.png");
                    ImageIcon yellowTime = new ImageIcon("view/yellowTime.png");
                    ImageIcon yellowPlus = new ImageIcon("view/yellowPlus.png");
                    ImageIcon yellowSun = new ImageIcon("view/yellowSun.png");
                    //blue pieces
                    ImageIcon bluePoint = new ImageIcon("view/bluePoint.png");
                    ImageIcon rotatedBPoint = rotateImage(bluePoint, Math.PI);
                    ImageIcon blueHourglass= new ImageIcon("view/blueHourglass.png");
                    ImageIcon blueTime = new ImageIcon("view/blueTime.png");
                    ImageIcon bluePlus = new ImageIcon("view/bluePlus.png");
                    ImageIcon blueSun = new ImageIcon("view/blueSun.png");

                    //set the app icon
                    Image appImage = yellowSun.getImage();
                    frame.setIconImage(appImage);
                    
                    //Generate the board
                    String piece;
                    for (int i = 0; i < 42; i++) {
                        JButton button;
                        if (piecePositions.containsKey(i) && piecePositions.get(i) != null) {
                            piece = piecePositions.get(i).toString();
                            button = new JButton();
                            button.setActionCommand(String.valueOf(i));
                            ButtonClickListener buttonClickListener = new ButtonClickListener(this);
                            button.addActionListener(buttonClickListener);
                            //place piece icon if there is a specific piece in that spot
                            switch (piece) {
                                case "p":
                                    button.setIcon(rotatedBPoint);
                                    break;
                                case "P":
                                    button.setIcon(yellowPoint);
                                    break;
                                case "l":
                                    button.setIcon(bluePlus);
                                    break;
                                case "L":
                                    button.setIcon(yellowPlus);
                                    break;
                                case "h":
                                    button.setIcon(blueHourglass);
                                    break;
                                case "H":
                                    button.setIcon(yellowHourglass);
                                    break;
                                case "t":
                                    button.setIcon(blueTime);
                                    break;
                                case "T":
                                    button.setIcon(yellowTime);
                                    break;
                                case "s":
                                    button.setIcon(blueSun);
                                    break;
                                case "S":
                                    button.setIcon(yellowSun);
                                    break;
                                default:
                                    break;
                            }
                            board.add(button);
                        }
                        else{
                            button = new JButton();
                            button.addActionListener(new ButtonClickListener(this));
                            button.setActionCommand(String.valueOf(i));
                            board.add(button);
                        }
                        if (i % 2 == 0) {
                            button.setBackground(boardColor1);
                        }
                        else{
                            button.setBackground(boardColor2);
                        }
                    }
                    frame.add(board);
                    frame.pack(); //automatically resize the elements in the app to a certain suitable size
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frame.setVisible(true);

                });
                
        
    }


    public static class ButtonClickListener implements ActionListener {
    private gameviewer gameView;
    private ChessPiece selectedPiece;
    private int selectedPosition;
    private Color previousColor;
    private boolean isFirstClick = true;
    ArrayList <Integer> allValidMoves = new ArrayList<>();

    public ButtonClickListener(gameviewer gameView) {
        this.gameView = gameView;
    }

    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        JButton secondButton = (JButton) e.getSource();

        if (isFirstClick == true) {
            handleFirstClick(sourceButton);
        } else {
            handleSecondClick(secondButton);
        }
    }

    private void handleFirstClick(JButton sourceButton) {
        previousColor = sourceButton.getBackground();
        sourceButton.setBackground(Color.GREEN);

        // Fetch the key (position) and value (ChessPiece) of the selected button
        selectedPosition = getButtonPosition(sourceButton);
        selectedPiece = gameView.gameBoard.getPiecePosition().get(selectedPosition);

        System.out.println("Selected Position: " + selectedPosition);
        System.out.println("Selected ChessPiece: " + selectedPiece);
        // check if valid (if valid, returns an arraylist of integers containing all valid positions to move)
        
        allValidMoves = gamecontroller.isValidMove(selectedPiece, selectedPosition, gameView.gameBoard);
        System.out.println("all valid moves : " + allValidMoves);
        

        isFirstClick = false;
    }

    private void handleSecondClick(JButton secondButton) {
        secondButton.setBackground(Color.BLUE);
        
        // Fetch the destination position
        int destinationPosition = getButtonPosition(secondButton);
        
        // check if destination == validmoves, if valid, use makeMoveBoardLogic to edit the hashmaps and generate a new board.
        for (int i = 0; i < allValidMoves.size(); i++) {
            if (destinationPosition == allValidMoves.get(i)) {
                GameBoard test = gamecontroller.makeMoveBoardLogic(selectedPiece, destinationPosition, gameView.gameBoard);
                System.out.println(test);
                break;
            }
            else {
                //try again?
            }
        }
        
        System.out.println("Destination Position: " + destinationPosition);

        // Reset state for the next move kys
        selectedPiece = null;
        selectedPosition = -1;
        isFirstClick = true;
    }

    private int getButtonPosition(JButton button) {
        // You can set the position as an action command when creating the button
        return Integer.parseInt(button.getActionCommand());
    }
}
    
    public static class WindowCloseButton implements ActionListener{ //pop up for closing the game
        private JFrame appFrame;

        public void ActionListenerClass(JFrame frame){
            this.appFrame = frame;
        }

        public void actionPerformed(ActionEvent e){
            int confirmation = JOptionPane.showConfirmDialog(appFrame, "It is recommended that you save before quitting. \nAre you sure you want to quit the game?", "Confirm Close", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private static ImageIcon rotateImage(ImageIcon icon, double angle) { //create rotated images of the point pieces 
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
        BufferedImage rotatedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedImage.createGraphics();
        g.setTransform(transform);
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();

        return new ImageIcon(rotatedImage);
    }
}
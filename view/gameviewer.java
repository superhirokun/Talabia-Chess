package view;

import javax.swing.*;

import model.ChessPiece;
import model.GameBoard;
import model.GameBoard.BobTheBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class gameviewer{
    public static void main(String[] args) {
        gameviewer gameDisplay = new gameviewer();
        gameDisplay.displayGame();
    }

    public void displayGame(){ //main method for board generation
                GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
                gameBoard.zaStarter(new BobTheBuilder());
                HashMap<Integer, ChessPiece> piecePositions = gameBoard.getPiecePosition();
                System.out.println(piecePositions); 
                //set colour of the board to look like a chess board
                Color boardColor1 = new Color(231, 237, 159);
                Color boardColor2 = new Color(219, 164, 86); 
                SwingUtilities.invokeLater(() ->{
                    JFrame frame = new JFrame("Tabalia chess"); //main application frame
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
                            button.addActionListener(new ButtonClickListener());
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
                            button.addActionListener(new ButtonClickListener());
                            board.add(button);
                        }
                        //set the colour of the board background
                        if (i % 2 == 0) {
                            button.setBackground(boardColor1);
                        }
                        else{
                            button.setBackground(boardColor2);
                        }
                    }
                    frame.add(board);
                    frame.pack(); //automatically resize the elements in the app to a certain suitable size
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
                sourceButton.setBackground(Color.GREEN);
            } else {
                sourceButton.setBackground(previousColor);
            }
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
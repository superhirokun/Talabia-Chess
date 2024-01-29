package view;
import javax.swing.*;

import control.gamecontroller;
import javafx.scene.layout.Border;
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
import java.util.concurrent.CountDownLatch;

public class gameviewer {
    private GameBoard gameBoard;
    private static ArrayList<Integer> allValidMoves = new ArrayList<>();
    private boolean moveMade;
    private ArrayList<ChessPiece> saveFirstSelect;
    public GameBoard updatedGameboard;
    public int turnBruh;
    boolean isFirstClick = true;
    ImageIcon previousImageIcon;
    int previousLocation = 0;
    CountDownLatch latch = new CountDownLatch(1);
    
    public gameviewer() {
        this.saveFirstSelect = new ArrayList<>();
        this.moveMade = false;
        this.turnBruh = 0;
    }
    

    public ArrayList<Integer> getAllValidMoves() {
        return allValidMoves;
    }

    public void setAllValidMoves(ArrayList<Integer> allValidMoves) {
        this.allValidMoves = allValidMoves;
    }

    public boolean isMoveMade() {
        return moveMade;
    }

    public void setMoveMade(boolean moveMade) {
        this.moveMade = moveMade;
    }
    JFrame frame = new JFrame("Tilapia Chess");
    JPanel boardPanel = new JPanel();
    JPanel options;

    public static void main(String[] args) {
        gameviewer gameView = new gameviewer();
        gameView.gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        
        gameView.displayGame(gameView.gameBoard, gameView.turnBruh);
        //gamecontroller.saveGame(gameView.gameBoard, gameView.turnBruh);
        //gamecontroller.loadGame();

        //Options pane creation, unchanging throughout the game
        JPanel options = new JPanel(); // panel for the game options
            options.setBackground(Color.LIGHT_GRAY);
            options.setPreferredSize(new Dimension(gameView.frame.getWidth(), 30));
            JButton save = new JButton("Save Game");
            JButton load = new JButton("Load Previous Game");
            JButton quit = new JButton("Quit Game");

            save.addActionListener(new SaveButton(gameView));
            load.addActionListener(new LoadButton());
            quit.addActionListener(new WindowCloseButton());
            options.add(save);
            options.add(load);
            options.add(quit);
            gameView.frame.add(options, BorderLayout.NORTH);
            boolean sunPieceCaptured = false;
        
        while (!gameView.gameBoard.isSunPieceCaptured()) {
            try {
                // Wait until the countdown latch reaches 0
                gameView.latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gameView.isMoveMade() == true && !gameView.isFirstClick() && gameView.saveFirstSelect.isEmpty()) {
                if (!gameView.isMoveMade() || gameView.isFirstClick() || !gameView.saveFirstSelect.isEmpty()) {   
                }
                int turn = BoardLogic.turnCounter(gameView.turnBruh); // increments counter
                System.out.println("turn counter : " + turn);
                BoardLogic.zaSwitcher(gameView.turnBruh); // check if the next turn requires switching between plus and time piece
                gameView.turnBruh++;
                gameView.refreshBoard(gameView.updatedGameboard, turn); //still doesnt display correctly
                gameView.setMoveMade(false);  // Reset the flag after updating the game board
                gameView.setFirstClick(true);
                gameView.latch = new CountDownLatch(1);
            }
            
            sunPieceCaptured = gameView.gameBoard.isSunPieceCaptured(); // check if any sunpiece is captured
            

             if (sunPieceCaptured == true) {
                 JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                 break;
             }      
        }
    }

    public void displayGame(GameBoard gameBoard, int turnCheck) {
        if (turnCheck == 0) {
            gameBoard.zaStarter(new BobTheBuilder()); // prints starter board
        }
        HashMap<Integer, ChessPiece> piecePositions = gameBoard.getPiecePosition();
        System.out.println(piecePositions);
        // set the color of the board to look like a chessboard
        Color yellow = new Color(231, 237, 159);
        Color blue = new Color(219, 164, 86);
        
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon appIcon = new ImageIcon("view/yellowSun.png");
            
        //Options pane creation, unchanging throughout the game
        options = new JPanel(); // panel for the game options
        options.setBackground(Color.LIGHT_GRAY);
        options.setPreferredSize(new Dimension(frame.getWidth(), 30));
        JButton save = new JButton("Save Game");
        JButton load = new JButton("Load Previous Game");
        JButton quit = new JButton("Quit Game");

        quit.addActionListener(new WindowCloseButton());
        options.add(save);
        options.add(load);
        options.add(quit);
        frame.add(options, BorderLayout.NORTH);
        frame.add(boardPanel);
            frame.remove(boardPanel);
            // Create a new panel with the chess board
            JPanel boardPanel = createBoardPanel(piecePositions, yellow, blue);

            frame.setIconImage(appIcon.getImage());
            // Add the new boardPanel to the frame's content pane
            frame.add(boardPanel, BorderLayout.CENTER);
            frame.pack();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }

    private JPanel createBoardPanel(HashMap<Integer, ChessPiece> piecePositions, Color yellow, Color blue) {
        // Create a new panel with the chess board
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6, 7));
    
        // yellow pieces
        ImageIcon yellowPoint = new ImageIcon("view/yellowPoint.png");
        ImageIcon rotatedYPoint = rotateImage(yellowPoint, Math.PI);
        ImageIcon yellowHourglass = new ImageIcon("view/yellowHourglass.png");
        ImageIcon yellowTime = new ImageIcon("view/yellowTime.png");
        ImageIcon yellowPlus = new ImageIcon("view/yellowPlus.png");
        ImageIcon yellowSun = new ImageIcon("view/yellowSun.png");
        // blue pieces
        ImageIcon bluePoint = new ImageIcon("view/bluePoint.png");
        ImageIcon rotatedBPoint = rotateImage(bluePoint, Math.PI);
        ImageIcon blueHourglass = new ImageIcon("view/blueHourglass.png");
        ImageIcon blueTime = new ImageIcon("view/blueTime.png");
        ImageIcon bluePlus = new ImageIcon("view/bluePlus.png");
        ImageIcon blueSun = new ImageIcon("view/blueSun.png");
    
        // Generate the board
        String piece;
        for (int i = 0; i < 42; i++) {
            JButton button;
            if (piecePositions.containsKey(i) && piecePositions.get(i) != null) {
                piece = piecePositions.get(i).toString();
                button = new JButton();
                button.setActionCommand(String.valueOf(i));
                ButtonClickListener buttonClickListener = new ButtonClickListener(this);
                button.addActionListener(buttonClickListener);
                // place piece icon if there is a specific piece in that spot
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
                boardPanel.add(button);
            } else {
                button = new JButton("");
                button.addActionListener(new ButtonClickListener(this));
                button.setActionCommand(String.valueOf(i));
                boardPanel.add(button);
            }
            if (i % 2 == 0) {
                button.setBackground(yellow);
            } else {
                button.setBackground(blue);
            }
        }
        return boardPanel;
    }

    private JPanel refreshBoard(GameBoard gameBoard, int turnCheck){
        boardPanel.removeAll();
        options.removeAll();
        displayGame(gameBoard, turnCheck);
        return boardPanel;
    }

    //clicks handling

    public boolean isFirstClick() {
        return isFirstClick;
    }

    public void setFirstClick(boolean isFirstClick) {
        this.isFirstClick = isFirstClick;
    }

    public static class ButtonClickListener implements ActionListener {
        private gameviewer gameView;
        private ChessPiece selectedPiece;
        private int selectedPosition;
        private Color previousColor;
        private CountDownLatch latch = new CountDownLatch(1);

        public ButtonClickListener(gameviewer gameView) {
            this.gameView = gameView;
        }

        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();

            if (gameView.isFirstClick()) {
                handleFirstClick(sourceButton);
            } else {
                handleSecondClick(sourceButton); 
            }
        }

        private void handleFirstClick(JButton sourceButton) {
            previousColor = sourceButton.getBackground();

            int selectedPosition = getButtonPosition(sourceButton);
            ChessPiece selectedPiece = gameView.gameBoard.getPiecePosition().get(selectedPosition);
            gameView.saveFirstSelect.add(selectedPiece);
            
            System.out.println("Selected Position: " + selectedPosition);
            System.out.println("Selected ChessPiece: " + selectedPiece);
            
            if (selectedPiece != null) {
                gameView.setAllValidMoves(gamecontroller.isValidMove(selectedPiece, selectedPosition, gameView.gameBoard));
                System.out.println(allValidMoves);
                sourceButton.setBackground(Color.GREEN);
                gameView.setFirstClick(false);
                }
            else{
                gameView.setFirstClick(true);
            }
        }

        private void handleSecondClick(JButton sourceButton) {
            int destinationPosition = getButtonPosition(sourceButton);
            System.out.println(gameView.saveFirstSelect);
            ArrayList<Integer> allValidMoves = gameView.getAllValidMoves();
            System.out.println("preparing make move");
            GameBoard previousGameBoard = gameView.gameBoard; // Save current state
            for (int i = 0; i < allValidMoves.size(); i++) {
                if (destinationPosition == allValidMoves.get(i)) {
                    sourceButton.setBackground(Color.BLUE);
                    gameView.updatedGameboard = gamecontroller.makeMoveBoardLogic(gameView.saveFirstSelect.get(0), destinationPosition, gameView.gameBoard);
                    System.out.println("move made");
                    System.out.println(gameView.updatedGameboard); 
                    gameView.setMoveMade(true); 
                    gameView.latch.countDown();
                    gameView.saveFirstSelect.remove(0);
                    selectedPiece = null;
                    selectedPosition = -1;
                    break;
                }
            }
            if (!gameView.moveMade) {
                gameView.gameBoard = previousGameBoard;
                JOptionPane.showMessageDialog(null, "Invalid Move", "Move error" , JOptionPane.ERROR_MESSAGE);
            }   
        }
        
        private int getButtonPosition(JButton button) {
            return Integer.parseInt(button.getActionCommand());
        }
    }

    public static class WindowCloseButton implements ActionListener {
        private JFrame appFrame;

        public void ActionListenerClass(JFrame frame) {
            this.appFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            int confirmation = JOptionPane.showConfirmDialog(appFrame,
                    "It is recommended that you save before quitting. \nAre you sure you want to quit the game?",
                    "Confirm Close", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        

    }

    public static class SaveButton implements ActionListener {
        private JFrame appFrame;
        private gameviewer gameView;
        
        public SaveButton(gameviewer gameView) {
            this.gameView = gameView;
        }
        public void ActionListenerClass(JFrame frame) {
            this.appFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            int confirmation = JOptionPane.showConfirmDialog(appFrame, 
            "Save Ongoing Game?",
            "saveGame" , JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            gamecontroller.saveGame(gameView.gameBoard, gameView.turnBruh);
            System.exit(0);
        }
        }
    }

    public static class LoadButton implements ActionListener {
        private JFrame appFrame;

        public void ActionListenerClass(JFrame frame) {
            this.appFrame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            int confirmation = JOptionPane.showConfirmDialog(appFrame, 
            "Load Previous Game?",
            "loadGame" , JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            String decodedFEN = gamecontroller.loadGame();
            
        }
        }
    }

    private static ImageIcon rotateImage(ImageIcon icon, double angle) {
        BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, bufferedImage.getWidth() / 2, bufferedImage.getHeight() / 2);
        BufferedImage rotatedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = rotatedImage.createGraphics();
        g.setTransform(transform);
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();

        return new ImageIcon(rotatedImage);
    }
}

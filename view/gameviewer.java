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

    public static void main(String[] args) {
        gameviewer gameView = new gameviewer();
        
        gameView.gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        gameView.displayGame(gameView.gameBoard, gameView.turnBruh);
        boolean sunPieceCaptured = false;
        while (!gameView.gameBoard.isSunPieceCaptured()) {

            if (gameView.isMoveMade() == true && !gameView.isFirstClick() && gameView.saveFirstSelect.isEmpty()) {
                int turn = BoardLogic.turnCounter(gameView.turnBruh); // increments counter
                System.out.println("turn counter : " + turn);
                BoardLogic.zaSwitcher(); // check if the next turn requires switching between plus and time piece
                gameView.displayGame(gameView.updatedGameboard, gameView.turnBruh); //still doesnt display correctly
                gameView.setMoveMade(false);  // Reset the flag after updating the game board
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
        Color boardColor1 = new Color(231, 237, 159);
        Color boardColor2 = new Color(219, 164, 86);
        
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon appIcon = new ImageIcon("view/yellowSun.png");
    
            JPanel options = new JPanel(); // panel for the game options
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
    
            // Create a new panel with the chess board
            JPanel boardPanel = createBoardPanel(piecePositions, boardColor1, boardColor2);
            
            // Remove the existing boardPanel from the frame's content pane
            Container contentPane = frame.getContentPane();
            Component[] components = contentPane.getComponents();
            for (Component component : components) {
                if (component instanceof JPanel) {
                    contentPane.remove(component);
                    break; 
                }
            }

            frame.setIconImage(appIcon.getImage());
            // Add the new boardPanel to the frame's content pane
            frame.add(boardPanel);
            frame.pack();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        });
    }

    private JPanel createBoardPanel(HashMap<Integer, ChessPiece> piecePositions, Color boardColor1, Color boardColor2) {
        // Create a new panel with the chess board
        JPanel boardPanel = new JPanel();
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
                button.setBackground(boardColor1);
            } else {
                button.setBackground(boardColor2);
            }
        }
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
        }

        private void handleSecondClick(JButton sourceButton) {
            sourceButton.setBackground(Color.BLUE);

            int destinationPosition = getButtonPosition(sourceButton);
            System.out.println(gameView.saveFirstSelect);
            ArrayList<Integer> allValidMoves = gameView.getAllValidMoves();
            System.out.println("preparing make move");
            for (int i = 0; i < allValidMoves.size(); i++) {
                if (destinationPosition == allValidMoves.get(i)) {
                    gameView.updatedGameboard = gamecontroller.makeMoveBoardLogic(gameView.saveFirstSelect.get(0), destinationPosition, gameView.gameBoard);
                    System.out.println("move made");
                    System.out.println(gameView.updatedGameboard); 
                    gameView.setMoveMade(true); 
                    
                }
            }
            gameView.saveFirstSelect.remove(0);
            //System.out.println("Destination Position: " + destinationPosition);

            selectedPiece = null;
            selectedPosition = -1;
            gameView.setFirstClick(true);
            //System.out.println("FinalFirstClick (2nd) : " + gameView.isFirstClick);
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

package control;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.*;
import model.GameBoard.BobTheBuilder;

public class gamecontrollerTest {

    @Test
    public void testMakeMoveBoardLogic() {
        // Create a game board
        BobTheBuilder builder = new BobTheBuilder();
        GameBoard gameBoard = new GameBoard(builder);
        gameBoard.zaStarter(builder);
        
        // Create a piece to move
        ChessPiece piece = new PlusPiece(Color.Yellow, 14);
        
        // Set the initial position of the piece
        
        // Add the piece to the game board
        builder.placePiece(gameBoard, piece, 2);
        
        // Make the move
        int destination = 13;
        GameBoard newGameBoard = gamecontroller.makeMoveBoardLogic(piece, destination, gameBoard);
        
        // Assert that the piece has moved to the destination
        //assertNull(newGameBoard.getPiece(2)); // The piece should not be at the initial position
        assertEquals(piece, newGameBoard.getPiece(destination)); // The piece should be at the destination
    }
    
    // Add more test cases here...
}
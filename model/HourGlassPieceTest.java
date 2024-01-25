package model;

import org.junit.jupiter.api.Test;

import model.GameBoard.BobTheBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


public class HourGlassPieceTest {

    @Test
    public void testValidMoves() {
        // Create a game board
        BobTheBuilder builder = new BobTheBuilder();
        GameBoard gameBoard = new GameBoard(builder);
        
        // Create a white HourGlassPiece at position 28
        HourGlassPiece HourGlassPiece = new HourGlassPiece(Color.Yellow, 14);
        
        // Add the white HourGlassPiece to the game board
        builder.placePiece(gameBoard, HourGlassPiece, 28);
        
        // Calculate the valid moves for the white HourGlassPiece
        ArrayList<Integer> validMoves = HourGlassPiece.ValidMoves(gameBoard);
        
        // Assert that the valid moves are as expected
        ArrayList<Integer> expectedValidMoves = new ArrayList<>();
        expectedValidMoves.add(1);
        expectedValidMoves.add(9);
        expectedValidMoves.add(23);
        expectedValidMoves.add(29);
        assertEquals(expectedValidMoves, validMoves);
    }
    
    // Add more test cases here...
}
package model;

import org.junit.jupiter.api.Test;

import model.GameBoard.BobTheBuilder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PlusPieceTest {

    @Test
    public void testValidMoves() {
        // Create a game board
        BobTheBuilder builder = new BobTheBuilder();
        GameBoard gameBoard = new GameBoard(builder);
        
        // Create a white PlusPiece at position 28
        PlusPiece plusPiece = new PlusPiece(Color.Yellow, 14);
        
        // Add the white PlusPiece to the game board
        builder.placePiece(plusPiece, 28);
        
        // Calculate the valid moves for the white PlusPiece
        ArrayList<Integer> validMoves = plusPiece.ValidMoves(gameBoard);
        
        // Assert that the valid moves are as expected
        ArrayList<Integer> expectedValidMoves = new ArrayList<>();
        expectedValidMoves.add(13);
        expectedValidMoves.add(6);
        expectedValidMoves.add(19);
        expectedValidMoves.add(18);
        expectedValidMoves.add(17);
        expectedValidMoves.add(16);
        expectedValidMoves.add(15);
        expectedValidMoves.add(14);
        expectedValidMoves.add(34);
        expectedValidMoves.add(41);
        assertEquals(expectedValidMoves, validMoves);
    }
    
    // Add more test cases here...
}
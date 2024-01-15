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
        PlusPiece plusPiece = new PlusPiece(Color.Yellow, 28);
        
        // Add the white PlusPiece to the game board
        builder.placePiece(plusPiece, 28);
        
        // Calculate the valid moves for the white PlusPiece
        ArrayList<Integer> validMoves = plusPiece.ValidMoves(gameBoard);
        
        // Assert that the valid moves are as expected
        ArrayList<Integer> expectedValidMoves = new ArrayList<>();
        expectedValidMoves.add(0);
        expectedValidMoves.add(7);
        expectedValidMoves.add(14);
        expectedValidMoves.add(21);
        expectedValidMoves.add(29);
        expectedValidMoves.add(30);
        expectedValidMoves.add(31);
        expectedValidMoves.add(32);
        expectedValidMoves.add(33);
        expectedValidMoves.add(34);
        expectedValidMoves.add(35);
        assertEquals(expectedValidMoves, validMoves);
    }
    
    // Add more test cases here...
}
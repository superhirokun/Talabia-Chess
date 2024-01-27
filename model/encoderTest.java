package model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class encoderTest {

    @Test
    public void testZaEncoder() {
        // Create a HashMap representing the chessboard
        HashMap<Integer, ChessPiece> zaHash = new HashMap<>();
        zaHash.put(1, new PlusPiece(Color.Blue, 1));
        zaHash.put(3, new HourGlassPiece(Color.Blue, 3));
        zaHash.put(12, new TimePiece(Color.Blue, 12));
        zaHash.put(19, new SunPiece(Color.Blue, 19));
        zaHash.put(15, new PointPiece(Color.Blue, 15));
        zaHash.put(9, new PlusPiece(Color.Yellow, 9));
        zaHash.put(6, new HourGlassPiece(Color.Yellow, 6));
        zaHash.put(32, new TimePiece(Color.Yellow, 32));
        zaHash.put(22, new SunPiece(Color.Yellow, 22));
        zaHash.put(29, new PointPiece(Color.Yellow, 29));
        // Add more chess pieces to the HashMap
        
        // Call the zaEncoder method
        String encodedString = encoder.zaEncoder(zaHash);
        
        // Assert that the encoded string is as expected
        String expectedEncodedString = "RNBQKBNR/PPPPPPPP/8/8/8/8/pppppppp/rnbqkbnr";
        assertEquals(expectedEncodedString, encodedString);
    }
    
    // Add more test cases here...
}
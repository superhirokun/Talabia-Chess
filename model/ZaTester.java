package model;
import java.util.*;
import control.*;
import model.GameBoard.BobTheBuilder;


public class ZaTester{
    public static void main(String[] args) {
        int turnBruh = 0;
        GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        BobTheBuilder builder = new BobTheBuilder();
        HashMap <Integer, ChessPiece> pieces = gameBoard.piecePosition;
        gameBoard.zaStarter(builder);
        
        //System.out.println(Arrays.toString(gamecontroller.zaFENDecoder(GameBoard.StartingFEN)));
        int count = 0;
        
        while (count<100) {
            //display board every move 
            for (ChessPiece piece : pieces.values()) {
                System.out.print(piece);
                count++;
                /*
                
                if (selectedTile.get(ChessPiece) == piece) { ()
                    piece = selectedTile(ChessPiece) ie : piece = selectedTile(P);
                    gamecontroller.makeMoveBoardLogic(piece, 0, gameBoard); // make move (this shit dont work)(idk what to place null with)
                }
                */
                // replace null with piece
                
                
            }
            // user select a piece  
            

            BoardLogic.turnCounter(turnBruh); //increments counter 
            BoardLogic.zaSwitcher(); //check if next turn requires switching between plus and time piece
            
        }
        
    }

    
}


//GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
//        BobTheBuilder builder = new BobTheBuilder();
//        gameBoard.zaStarter(builder);
//        System.out.println(Arrays.toString(gamecontroller.zaFENDecoder(StartingFEN)));
//        System.out.println(builder.piecePosition);
//        System.out.println(gameBoard.getAllPiecePosition());
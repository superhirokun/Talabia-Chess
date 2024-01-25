package control;
import java.util.*;
import model.*;
import model.GameBoard.BobTheBuilder;
import model.BoardLogic;
import model.ChessPiece;
import model.Color;
import model.GameBoard;
import model.PlusPiece;
import model.TimePiece;
public class gamecontroller extends BoardLogic{
    
    public static GameBoard makeMoveBoardLogic(ChessPiece piece, int destination, GameBoard gamer){   //make the move
        BobTheBuilder builder = new BobTheBuilder();
        int initialPosition = piece.getPosition();
        HashMap<Integer, ChessPiece> prevPiecePosition = new HashMap<>();
        prevPiecePosition = gamer.getAllThePiece();  //get the previous position of the piece
        int totalSquare = 42;
        for(int i = 0; i < totalSquare; i++){
           if(prevPiecePosition.get(i) != null){    //check if the piece is not null
               if(prevPiecePosition.get(i).getPosition() == initialPosition){
                   prevPiecePosition.get(i).setPosition(destination);   //set the position of the piece to the new position which is the destination
                   builder.placePiece(prevPiecePosition.get(i), destination);
               }else if(prevPiecePosition.get(i).getPosition() == destination && prevPiecePosition.get(i).getCaptured() == true){   //check if the piece is captured
                   continue;
               }else if(canSwitch == true){     //switch the time piece with the plus piece when the turn is even
                    if(prevPiecePosition.get(i) == gamer.plusPiece.get(i)){
                        builder.placePiece(new TimePiece(gamer.plusPiece.get(i).getColor(), i), i);
                    }else if(prevPiecePosition.get(i) == gamer.timePiece.get(i)){
                        builder.placePiece(new PlusPiece(gamer.timePiece.get(i).getColor(), i), i);
                    }
               }
               else{
                   builder.placePiece(prevPiecePosition.get(i), i);   //if the piece is not the piece that is moving then place the piece at the same position
               }
            }else{
                builder.placePiece(null, i);    //if the piece is null then place a null piece
            }
        }
        
        return builder.build();
    }
}    

    

   
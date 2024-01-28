package control;
import java.util.*;
import model.*;
import model.GameBoard.BobTheBuilder;

public class gamecontroller extends BoardLogic{
    
    /*make a move by generating a new board */
    public static GameBoard makeMoveBoardLogic(ChessPiece piece, int destination, GameBoard gamer){   //make the move
        BobTheBuilder builder = new BobTheBuilder();
        int initialPosition = piece.getPosition();
        HashMap<Integer, ChessPiece> prevPiecePosition = new HashMap<>();
        prevPiecePosition = gamer.getAllThePiece();  //get the previous position of the piece
        for(int i = 0; i < BoardLogic.totalSquare; i++){
           if(prevPiecePosition.get(i) != null){    //check if the piece is not null
               if(prevPiecePosition.get(i).getPosition() == initialPosition){
                   prevPiecePosition.get(i).setPosition(destination);   //set the position of the piece to the new position which is the destination
                   builder.placePiece(gamer,prevPiecePosition.get(i), destination);
               }else if(prevPiecePosition.get(i).getPosition() == destination && prevPiecePosition.get(i).getCaptured() == true){   //check if the piece is captured
                   continue;
               }else if(canSwitch == true){     //switch the time piece with the plus piece when the turn is even
                    if(prevPiecePosition.get(i) == gamer.plusPiece.get(i)){
                        builder.placePiece(gamer,TimePiece.createTimePiece(gamer.plusPiece.get(i).getColor(), i), i);
                    }else if(prevPiecePosition.get(i) == gamer.timePiece.get(i)){
                        builder.placePiece(gamer,PlusPiece.createPlusPiece(gamer.plusPiece.get(i).getColor(), i), i);
                    }
               }
               else{
                   builder.placePiece(gamer,prevPiecePosition.get(i), i);   //if the piece is not the piece that is moving then place the piece at the same position
               }
            }else{
                builder.placePiece(gamer,null, i);    //if the piece is null then place a null piece
            }
        }
        
        return builder.build();
    }

    /*decode the fen string into  */
    public static String[] zaFENDecoder(String zaFEN){
        String zaFENString = zaFEN.split(" ")[0];
        int zalength = zaFENString.length();
        String[] zaBoardPiece = new String[BoardLogic.totalSquare];
        int zaCounter = 0;
        for(int i = 0; i < zalength; i++){
            char zaCurrentChar = zaFENString.charAt(i);
            if(zaCurrentChar == '/'){
                continue;
            }
            if(Character.isDigit(zaCurrentChar)){
                for(int j = 0; j < Character.getNumericValue(zaCurrentChar); j++){
                    zaBoardPiece[zaCounter] = "-";
                    zaCounter++;
                }
                
            }else{
                zaBoardPiece[zaCounter] = String.valueOf(zaCurrentChar);
                zaCounter++;
            }
        }
        return zaBoardPiece;   
    }

    /**
     * Decodes the color from the given FEN string.
     */
    public static Color zaColorDecoder(String zaFEN){
        String zaMover = zaFEN.split(" ")[1];
        if(zaMover.equals("y")){
            return Color.Yellow;
        }else{
            return Color.Blue;
        }
    }

    /**
     * Decodes the turn number from the given FEN string.
     * 
     * @param zaFEN the FEN string representing the chess position
     * @return the turn number decoded from the FEN string
     */
    public static int zaTurnDecoder(String zaFEN){
        String zaTurn = zaFEN.split(" ")[2];
        return Integer.parseInt(zaTurn);
    }

    
    /**
     * Encodes a HashMap of ChessPieces into a String representation.
     * 
     * @param zaHash the HashMap containing the ChessPieces to be encoded
     * @return the encoded String representation of the ChessPieces
     */
    public static String zaEncoder(HashMap<Integer, ChessPiece> zaHash, Integer turn) {
        StringBuilder sb = new StringBuilder();
        int emptySquareCount = 0;
    
        for (int i = 0; i < BoardLogic.totalSquare; i++) {
            if (i != 0 && i % BoardLogic.numOfSquarePerRow == 0) {
                if (emptySquareCount != 0) {
                    sb.append(emptySquareCount);
                    emptySquareCount = 0;
                }
                sb.append("/");
            }
    
            if (zaHash.get(i) != null) {
                if (emptySquareCount != 0) {
                    sb.append(emptySquareCount);
                    emptySquareCount = 0;
                }
                sb.append(zaHash.get(i).toString());
            } else {
                emptySquareCount++;
            }
        }
    
        if (emptySquareCount != 0) {
            sb.append(emptySquareCount);
        }

        String playerColor = BoardLogic.isYellowTurn(turn) ? "y" : "b";
        sb.append(" " + playerColor + " " + turn);
    
        return sb.toString();
    }

    public static void startGame(){
        System.out.println("Game is starting...");

    
        }

    public static void resetGame(){
        System.out.println("Reseti(french for reset)");
    }

    public static void saveGame() {
           
    }

    public static void loadGame() {
        
    }
    
}    

    

   
package model;
import java.util.*;

import model.GameBoard.BobTheBuilder;

public class BoardLogic {

    public static final int totalSquare = 42;   //the total number of square on the board
    public static final int numOfSquarePerRow = 7;       //the total number of square per row on the board

    public static boolean[] FirstColumn = intializedColumn(0);    //the first column of the board
    public static boolean[] SecondColumn = intializedColumn(1);   //the second column of the board
    public static boolean[] SixthColumn = intializedColumn(5);    //the sixth column of the board
    public static boolean[] SeventhColumn = intializedColumn(6);  //the seventh column of the board

    public static boolean[] FirstRow = intializedRow(0);
    public static boolean[] SeventhRow = intializedRow(35);

    protected static HashMap<Integer,ChessPiece> targetPiece;
    protected static int turn;
    protected static boolean canSwitch;

    //create a boolean array that represent a column on the board and set the column to true and the rest to false
    public static boolean[] intializedColumn(int Column){  
        boolean[] column = new boolean[totalSquare];
        do{
            column[Column] = true;
            Column += numOfSquarePerRow;
        }while(Column < totalSquare);
        return column;
    }

    public static boolean[] intializedRow(int Row){
        boolean[] row = new boolean[totalSquare];
        do{
            row[Row] = true;
            Row++;
        }while(Row % numOfSquarePerRow != 0);
        return row;
    }

    public static boolean isValidSquareCoordinate(int coordinate){    //check if the coordinate given is out of bound
        return coordinate >= 0 && coordinate < totalSquare;
    }

    public static boolean isValidMove(int destination, ArrayList<Integer> validMoves){   //check if the move is valid
        return validMoves.contains(destination);
    }

    public static int turnCounter(int turn){    //count the turn
        turn++;
        setTurn(turn);
        return turn;
    }

    public static void zaSwitcher(){
        if(turn%2 == 0){
            setCanSwitch(true);
        }
    }

    /*Capture the piece
     * TODO: need testing
    */
    public static void capturedZAPiece(BobTheBuilder builder, int destination){
        if(builder.piecePosition.get(destination) == getTargetPiece().get(destination)){
            builder.piecePosition.get(destination).setCaptured(true);
        }
    }

    /*TODO: do a for loop for every piece in the allThePiece and compare the position of the piece that being moved
     to it original position, if the position is the same then set the piece to the new position or get the position from the piece
     TODO: need to set the position to the new selected position in the prev method that will call this method also and if the destination position
     have an enemy piece set the enemy piece getCapture to false*/
    public static GameBoard makeMove(ChessPiece piece, int intialPosition, BobTheBuilder builder, GameBoard gamer){   //make the move
        int currentPosition = piece.getPosition();
        HashMap<Integer, ChessPiece> newPiecePosition = new HashMap<>();
        newPiecePosition = gamer.getAllThePiece();
        for(int i = 0; i < totalSquare; i++){
            if(newPiecePosition.get(i) != null){
                if(newPiecePosition.get(i).getPosition() == currentPosition){
                    builder.piecePosition.get(i).setPosition(intialPosition);
                    builder.piecePosition.put(intialPosition, newPiecePosition.get(i));
                }else{
                    builder.piecePosition.put(i, newPiecePosition.get(i));
                }
            }
        }
        
        return builder.build();
    }

    /**
     * Decodes the given zaFEN string and returns an array of board pieces.
     * 
     * @param zaFEN the zaFEN string to be decoded
     * @return an array of board pieces representing the decoded zaFEN string
     */
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

    //Getter
    public static HashMap<Integer, ChessPiece> getTargetPiece(){
        return targetPiece;
    }

    public static int getTurn(){
        return turn;
    }

    public static boolean getCanSwitch(){
        return canSwitch;
    }

    //Setter
    public static void setTargetPiece(ChessPiece piece, int position){
        targetPiece.put(position, piece);
    }

    public static void setTurn(int turn){
        BoardLogic.turn = turn;
    }

    public static void setCanSwitch(boolean canSwitch){
        BoardLogic.canSwitch = canSwitch;
    }
    
}

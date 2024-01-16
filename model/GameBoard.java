package model;
import java.util.*;

public class GameBoard {
    private final String[] board = new String[BoardLogic.totalSquare];
    protected HashMap<Integer, ChessPiece> piecePosition;
    //the starting FEN for the game
    protected final String StartingFEN = "lhtsthl/ppppppp/7/7/PPPPPPP/LHTSTHL y 1";    /*the first part b4 the space is the position of the piece on the board, 
                                                                                            the y is the yellow player's turn, the 1 is the number of turns*/
    private HashMap<Integer, ChessPiece> bluePiece;
    private HashMap<Integer, ChessPiece> yellowPiece;
    private HashMap<Integer, ChessPiece> allThePiece;
    
    
    public GameBoard(BobTheBuilder builder){
        this.piecePosition = builder.piecePosition;
        this.bluePiece = new HashMap<Integer, ChessPiece>();
        this.yellowPiece = new HashMap<Integer, ChessPiece>();
        this.allThePiece = new HashMap<Integer, ChessPiece>();
        this.bluePiece = colorPieceOnBoard(Color.Blue, builder);
        this.yellowPiece = colorPieceOnBoard(Color.Yellow, builder);
        this.allThePiece = allPiecesOnBoard(builder);
    }

    public void zaStarter(BobTheBuilder builder){
        boardSetting(builder,BoardLogic.zaFENDecoder(StartingFEN));
        boardCreator(builder);
        displayBoard();   
    }

    //Method
    public static GameBoard boardSetting(BobTheBuilder builder, String[] zaFEN){
        for(int i = 0; i < BoardLogic.totalSquare; i++){
            switch (zaFEN[i]) {
                case "l":
                builder.placePiece(new PlusPiece(Color.Blue, i), i);
                break;
            case "h":
                builder.placePiece(new HourGlassPiece(Color.Blue, i), i);
                break;
            case "t":
                builder.placePiece(new TimePiece(Color.Blue, i), i);
                break;
            case "s":
                builder.placePiece(new SunPiece(Color.Blue, i), i);
                break;
            case "p":
                builder.placePiece(new PointPiece(Color.Blue, i), i);
                break;
            case "L":
                builder.placePiece(new PlusPiece(Color.Yellow, i), i);
                break;
            case "H":
                builder.placePiece(new HourGlassPiece(Color.Yellow, i), i);
                break;
            case "T":
                builder.placePiece(new TimePiece(Color.Yellow, i), i);
                break;
            case "S":
                builder.placePiece(new SunPiece(Color.Yellow, i), i);
                break;
            case "P":   
                builder.placePiece(new PointPiece(Color.Yellow, i), i);
                break;
            case "-":
                builder.placePiece(null, i);
                break;
            default:
                break;
            }
        }
        return builder.build();
    }

    private void boardCreator(BobTheBuilder builder){
        for(int i = 0; i < BoardLogic.totalSquare; i++){
            this.board[i] = builder.bobsBoard[i];
        }
    }

    private HashMap<Integer, ChessPiece> colorPieceOnBoard(Color color, BobTheBuilder builder){
        HashMap<Integer, ChessPiece> pieceOnBoard = new HashMap<Integer, ChessPiece>();
        for(int i = 0; i < BoardLogic.totalSquare; i++){
            if(builder.piecePosition.get(i) != null){
                if(builder.piecePosition.get(i).getColor() == color){
                    pieceOnBoard.put(i, builder.piecePosition.get(i));
                }
            }
        }
        return pieceOnBoard;
    }

    private HashMap<Integer, ChessPiece> allPiecesOnBoard(BobTheBuilder builder){
        HashMap<Integer, ChessPiece> pieceOnBoard = new HashMap<Integer, ChessPiece>();
        for(int i = 0; i < BoardLogic.totalSquare; i++){
            if(builder.piecePosition.get(i) != null){
                pieceOnBoard.put(i, builder.piecePosition.get(i));
            }
        }
        return pieceOnBoard;
    }
    
    //Getter
    public String[] getBoard(){
        return this.board;
    }

    public String getBoardCoordinates(int position){
        return this.board[position];
    }

    public ChessPiece getPiece(int position){
        return this.piecePosition.get(position);
    }

    public HashMap<Integer, ChessPiece> getBluePiece(){
        return this.bluePiece;
    }

    public HashMap<Integer, ChessPiece> getYellowPiece(){
        return this.yellowPiece;
    }

    public HashMap<Integer, ChessPiece> getAllThePiece(){
        return this.allThePiece;
    }
    
    //for testing

    public void displayBoard(){ // Display ZA BOARDO
        for(int i = 0; i < BoardLogic.totalSquare; i++){
            if(i % BoardLogic.numOfSquarePerRow == 0){
                System.out.println();
            }
            System.out.print(this.board[i] + " ");
        }
        System.out.println();
    }

    //Builder
    public static class BobTheBuilder{
        String[] bobsBoard;
        HashMap<Integer, ChessPiece> piecePosition;
        public BobTheBuilder(){
            this.bobsBoard = new String[BoardLogic.totalSquare];
            this.piecePosition = new HashMap<Integer, ChessPiece>();         
        }

        public BobTheBuilder placePiece(ChessPiece piece, int position){
            if (piece != null) {
                this.bobsBoard[piece.getPosition()] = piece.toString();
                this.piecePosition.put(piece.getPosition(), piece);
            } else {
                this.bobsBoard[position] = "-";

            }
            return this;
        }

        public GameBoard build(){
            return new GameBoard(this);
        }

    }
}



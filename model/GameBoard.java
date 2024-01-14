package model;
import java.util.*;

public class GameBoard {
    private final String[] board = new String[BoardLogic.totalSquare];
    //the starting FEN for the game
    protected final String StartingFEN = "lhtsthl/ppppppp/7/7/PPPPPPP/LHTSTHL y 1";    /*the first part b4 the space is the position of the piece on the board, 
                                                                                            the y is the yellow player's turn, the 1 is the number of turns*/
    private ArrayList<ChessPiece> zaBluePiecesOnZaBoard;
    private ArrayList<ChessPiece> zaYellowPiecesOnZaBoard;
    
    
    public GameBoard(BobTheBuilder builder){
        this.zaBluePiecesOnZaBoard = new ArrayList<ChessPiece>();
        this.zaYellowPiecesOnZaBoard = new ArrayList<ChessPiece>();
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
    
    //Getter
    public String[] getBoard(){
        return this.board;
    }
    
    //for testing

    public void displayBoard(){ // Display ZA BOARDO
        for(int i = 0; i < BoardLogic.totalSquare; i++){
            if(i % BoardLogic.totalColumn == 0){
                System.out.println();
            }
            System.out.print(this.board[i] + " ");
        }
        System.out.println();
    }

    //Builder
    public static class BobTheBuilder{
        String[] bobsBoard;
        public BobTheBuilder(){
            this.bobsBoard = new String[BoardLogic.totalSquare];
            
        }

        public BobTheBuilder placePiece(ChessPiece piece, int position){
            if (piece != null) {
                this.bobsBoard[piece.getPosition()] = piece.toString();
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



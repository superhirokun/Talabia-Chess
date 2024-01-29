package model;
import java.util.*;
import control.*;
import model.GameBoard.BobTheBuilder;


public class ZaTester{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int turnBruh = gamecontroller.zaTurnDecoder(GameBoard.StartingFEN);
        GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        BobTheBuilder builder = new BobTheBuilder();
        HashMap <Integer, ChessPiece> pieces = gameBoard.piecePosition;
        gameBoard.zaStarter(builder);
        GameBoard newBoard= new GameBoard(new GameBoard.BobTheBuilder());
        BobTheBuilder newBuilder = new BobTheBuilder();
        
        //System.out.println(Arrays.toString(gamecontroller.zaFENDecoder(GameBoard.StartingFEN)));
        int count = 0;
        
        while (!gameBoard.isSunPieceCaptured()) {
            System.out.println("Turn: " + turnBruh);
            System.out.println("Enter a piece to move: ");
            Integer pieceToMove = sc.nextInt();
            System.out.println("Enter a destination: ");
            Integer destination = sc.nextInt();
            if(turnBruh ==1){
                ChessPiece piece = pieces.get(pieceToMove);
                ArrayList<Integer> validMoves=gamecontroller.isValidMove(piece, piece.getPosition(), gameBoard);
                System.out.println("works");
                for (Integer validMove : validMoves) {
                    if(validMove == destination){
                        System.out.println("Valid move");
                        newBoard = gamecontroller.makeMoveBoardLogic(piece, destination, gameBoard);
                        break;
                    }else{
                        System.out.println("Invalid move");
                        break;
                    }
                }
                System.out.println(gameBoard.plusPiece);
                newBoard.plusPiece = gameBoard.plusPiece;
                newBoard.timePiece = gameBoard.timePiece;
                System.out.println(newBoard.plusPiece);
                
            }else{
                HashMap <Integer, ChessPiece> newPieces = newBoard.piecePosition;
                ChessPiece piece = newPieces.get(pieceToMove);
                System.out.println(newBoard.plusPiece);
                System.out.println(newBoard.getAllPiecePosition());
                ArrayList<Integer> validMoves=gamecontroller.isValidMove(piece, piece.getPosition(), newBoard);
                System.out.println("works");
                for (Integer validMove : validMoves) {
                    if(validMove == destination){
                        System.out.println("Valid move");
                        newBoard = gamecontroller.makeMoveBoardLogic(piece, destination, newBoard);
                        break;
                    }else{
                        System.out.println("Invalid move");
                        break;
                    }
                }
               
                newBoard.plusPiece = newBoard.setPlusPiece(newBuilder.bobsBoard);
                newBoard.timePiece = newBoard.setTimePiece(newBuilder.bobsBoard);
                System.out.println(newBoard.plusPiece);
            }
            //BoardLogic.capturedZAPiece(builder, destination);
            newBuilder = gamecontroller.getBob();

            System.out.println(gameBoard.getAllPiecePosition());
            System.out.println(newBuilder.piecePosition);
            System.out.println(newBoard.getAllPiecePosition());
            newBoard.boardCreator(newBuilder);
            newBoard.displayBoard();

            //display board every move 
            // for (ChessPiece piece : pieces.values()) {
                
            //     /*
                
            //     if (selectedTile.get(ChessPiece) == piece) { ()
            //         piece = selectedTile(ChessPiece) ie : piece = selectedTile(P);
            //         gamecontroller.makeMoveBoardLogic(piece, 0, gameBoard); // make move (this shit dont work)(idk what to place null with)
            //     }
            //     */
            //     // replace null with piece
                
                
            // }
            // user select a piece  
            

            BoardLogic.turnCounter(turnBruh); //increments counter 
            turnBruh = BoardLogic.getTurn();
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
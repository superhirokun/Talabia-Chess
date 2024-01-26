package model;
import java.util.Arrays;
import java.util.Scanner;
import control.*;
import model.GameBoard.BobTheBuilder;
public class ZaTester{
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        BobTheBuilder builder = new BobTheBuilder();
        gameBoard.zaStarter(builder);
        int turnBruh = 0;

        System.out.println(Arrays.toString(gamecontroller.zaFENDecoder(GameBoard.StartingFEN)));
        
        
        while (!gameBoard.isSunPieceCaptured()) {
            
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter ur move ig : ");
            //Integer test = scanner.nextInt();
            //ChessPiece test1 = builder.piecePosition.get(test);
            // user select a piece 
            
            gamecontroller.makeMoveBoardLogic(null, 0, gameBoard);

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
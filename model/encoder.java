package model;

import java.util.HashMap;

import model.GameBoard.BobTheBuilder;


public class encoder {
    public static void main(String[] args) {
    GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
    BobTheBuilder builder = new BobTheBuilder();
    gameBoard.zaStarter(builder);
    System.out.println(gameBoard.getAllPiecePosition());
    System.out.println(zaEncoder(gameBoard.getAllPiecePosition()));
    }

    public static String zaEncoder(HashMap<Integer, ChessPiece> zaHash) {
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
    
        return sb.toString();
    }

}

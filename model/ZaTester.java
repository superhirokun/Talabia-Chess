package model;

import java.util.ArrayList;
import java.util.Arrays;

import model.GameBoard.BobTheBuilder;
public class ZaTester {
    protected static final String StartingFEN = "lhtsthl/ppppppp/7/7/PPPPPPP/LHTSTHL y 1";
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        BobTheBuilder builder = new BobTheBuilder();
        gameBoard.zaStarter(builder);
        System.out.println(Arrays.toString(BoardLogic.zaFENDecoder(StartingFEN)));
        System.out.println(builder.piecePosition);
        System.out.println(gameBoard.getAllPiecePosition());
    }
}

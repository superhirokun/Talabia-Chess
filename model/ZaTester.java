package model;

import model.GameBoard.BobTheBuilder;

public class ZaTester {
    protected static final String StartingFEN = "lhtsthl/ppppppp/7/7/PPPPPPP/LHTSTHL y 1";
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(new GameBoard.BobTheBuilder());
        gameBoard.zaStarter(new BobTheBuilder());
        
    }
}

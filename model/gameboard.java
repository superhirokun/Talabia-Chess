package model;

public class GameBoard{
    private ChessPiece[][] board;

    public GameBoard(){
        this.board = new ChessPiece[7][6];
    }

    public void placePiece(ChessPiece piece, int x, int y){
        this.board[x][y] = piece;
    }

    public void removePiece(int x, int y){
        this.board[x][y] = null;
    }


}

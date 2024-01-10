package model;

public class GameBoard{
    private ChessPiece[][] board;

    public GameBoard(){
        this.board = new ChessPiece[7][6];
    }

    //Setter
    public ChessPiece setPiece(ChessPiece piece, int x, int y){
        return this.board[x][y] = piece;
    }

    public void setBoard(ChessPiece[][] board){
        this.board = board;
    }

    //Getter
    public ChessPiece getPiece(int x, int y){
        return this.board[x][y];
    }

    public ChessPiece[][] getBoard(){
        return this.board;
    }

    //Place and remove piece
    public void placePiece(ChessPiece piece, int x, int y){
        this.board[x][y] = piece;
    }

    public void removePiece(int x, int y){
        this.board[x][y] = null;
    }

    public void reset(){
        //Reset the board
    }


}

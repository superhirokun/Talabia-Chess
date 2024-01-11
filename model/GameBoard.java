package model;

import java.util.*;

public class GameBoard{
    private String[][] board;                                                  //The board                                 
    private HashMap<String, Integer>pieceMap = new HashMap<String, Integer>(); //Map the char to the int for checking
    private char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};              //Letters for the board

    public GameBoard(){
        this.board = new String[6][7];
        initializeBoard();
        hashSetter();
    }

    //Setter
    public void setBoard(String[][] board){
        this.board = board;
    }

    private void initializeBoard(){
        //Initialize the board
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                this.board[i][j] = letters[j] + Integer.toString(6-i);
            }
        }
        //Testing
        System.out.println("Board initialized");
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void hashSetter(){
        //Set the hashmap
        for(int i = 0; i < 7; i++){
            this.pieceMap.put(Character.toString(letters[i]), (i+1));
        }
        //Testing
        System.out.println("Hashmap initialized");
        System.out.println(this.pieceMap);
    }

    //Getter
    public String[][] getBoard(){
        return this.board;
    }

    public char[] lettersGetter(){
        return this.letters;
    }

    //Place and remove piece
    public void placePiece(String piece, int x, int y){
        this.board[x][y] = piece;
    }

    public void removePiece(int x, int y){
        this.board[x][y] = null;
    }

    public void reset(){
        //Reset the board
    }

    public static void main(String[] args){
        GameBoard board = new GameBoard();
        System.out.println(board.getBoard());
    }


}

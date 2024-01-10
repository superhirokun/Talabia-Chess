package model;

public class GameBoard{
    private String[][] board;
    private char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};

    public GameBoard(){
        this.board = new String[7][6];
        initializeBoard();
    }

    //Setter
    public void setBoard(String[][] board){
        this.board = board;
    }

    private void initializeBoard(){
        //Initialize the board
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                this.board[i][j] = letters[j] + Integer.toString(7-i);
            }
        }
        //Testing
        System.out.println("Board initialized");
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 6; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }

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

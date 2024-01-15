package model;

import java.util.ArrayList;

public class TimePiece extends ChessPiece{

    protected final static int[] offsetValues = {-8, -6, 6, 8};

    TimePiece( Color color, int position) {
        super(PieceType.TIME, color, position, false);
        
    }
    
    /**
     * Calculates the valid moves for the TimePiece on the given game board.
     * 
     * @param gameBoard the game board on which the TimePiece is placed
     * @return an ArrayList of integers representing the valid move positions
     */
    public ArrayList<Integer> ValidMoves(GameBoard gameBoard){
        int destination;
        ArrayList<Integer> validMoves = new ArrayList<Integer>();

        for (int offset : offsetValues) {
            destination = this.position;
            while(BoardLogic.isValidSquareCoordinate(destination)){
                
                destination = destination + offset;

                if (FirstColumnOffsetKiller(this.position, offset) ||
                SeventhColumnOffsetKiller(this.position, offset)) {
                    break;
                }

                if (BoardLogic.isValidSquareCoordinate(destination)) {
                    if (gameBoard.getPiece(destination) == null) {
                        validMoves.add(destination);
                    } else if (gameBoard.getPiece(destination).getColor() != this.color) {
                        validMoves.add(destination);
                        break;
                    }else if (gameBoard.getPiece(destination).getColor() == this.color) {
                        break;
                    }
                }
            }
        }
        return validMoves;
    };

    //the invalid of offset of the pieces at the specific column
    private static boolean FirstColumnOffsetKiller(int currentPosition, int TimePieceOffset){
        return BoardLogic.FirstColumn[currentPosition] && (TimePieceOffset == -8 || TimePieceOffset == 6);
    }

    private static boolean SeventhColumnOffsetKiller(int currentPosition, int TimePieceOffset){
        return BoardLogic.SeventhColumn[currentPosition] && (TimePieceOffset == -6 || TimePieceOffset == 8);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}

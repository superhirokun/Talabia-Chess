package model;

import java.util.ArrayList;

public class PlusPiece extends ChessPiece{
    protected final static int[] offsetValues = {-7, -1, 1, 7};
    protected int destination;

    PlusPiece( Color color, int position) {
        super(PieceType.PLUS, color, position, false);
    }

    /**
     * Calculates the valid moves for the PlusPiece on the given game board.
     * 
     * @param gameBoard the game board on which the PlusPiece is placed
     * @return an ArrayList of integers representing the valid move positions
     */
    public ArrayList<Integer> ValidMoves(GameBoard gameBoard){
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int offset : offsetValues) {
            destination = this.position;
            while(BoardLogic.isValidSquareCoordinate(destination)){
                destination = destination + offset;
                if (FirstColumnOffsetKiller(this.position, offset) || 
                SeventhColumnOffsetKiller(this.position, offset)) {
                    break;
                }
                if((offset == 1 || offset == -1) && destination%7 == 0){
                    break;
                }
                if (BoardLogic.isValidSquareCoordinate(destination)) {
                    if (gameBoard.getPiece(destination) == null) {
                        validMoves.add(destination);
                    } else if (gameBoard.getPiece(destination).getColor() != this.color) {
                        validMoves.add(destination);
                        BoardLogic.setTargetPiece(gameBoard.getPiece(destination), destination);
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
    private static boolean FirstColumnOffsetKiller(int currentPosition, int PlusPieceOffset){
        return BoardLogic.FirstColumn[currentPosition] && (PlusPieceOffset == -1);
    }

    private static boolean SeventhColumnOffsetKiller(int currentPosition, int PlusPieceOffset){
        return BoardLogic.SeventhColumn[currentPosition] && (PlusPieceOffset == 1);
    }

    @Override
    public String toString(){
        return super.toString();
    }
    
}

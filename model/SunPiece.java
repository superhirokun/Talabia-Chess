package model;

import java.util.ArrayList;

public class SunPiece extends ChessPiece{

    protected final static int[] offsetValues = {-8, -7, -6, -1, 1, 6, 7, 8};
    protected int destination;

    SunPiece( Color color, int position) {
        super(PieceType.SUN, color, position, false);
    }
    public static SunPiece createSunPiece(Color color, int position) {
        return new SunPiece(color, position);
    }
    /**
     * Calculates the valid moves for the SunPiece on the given game board.
     * 
     * @param gameBoard the game board on which the SunPiece is placed
     * @return an ArrayList of integers representing the valid move positions
     */
    public ArrayList<Integer> ValidMoves(GameBoard gameBoard){
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int offset : offsetValues) {
            destination = this.position + offset;
            if (FirstColumnOffsetKiller(this.position, offset) || 
            SeventhColumnOffsetKiller(this.position, offset)) {
                continue;
            }
            if (BoardLogic.isValidSquareCoordinate(destination)) {
                if (gameBoard.getPiece(destination) == null) {
                    validMoves.add(destination);
                } else if (gameBoard.getPiece(destination).getColor() != this.color) {
                    validMoves.add(destination);
                    //BoardLogic.setTargetPiece(gameBoard.getPiece(destination), destination);
                }
            }
        }
        return validMoves;
    };

    //the invalid of offset of the pieces at the specific column
    private static boolean FirstColumnOffsetKiller(int currentPosition, int SunOffset){
        return BoardLogic.FirstColumn[currentPosition] && (SunOffset == -8 || SunOffset == -1 || SunOffset == 6);
    }

    private static boolean SeventhColumnOffsetKiller(int currentPosition, int SunOffset){
        return BoardLogic.SeventhColumn[currentPosition] && (SunOffset == -6 || SunOffset == 1 || SunOffset == 8 );
    }

    @Override
    public String toString(){
        return super.toString();
    }
}

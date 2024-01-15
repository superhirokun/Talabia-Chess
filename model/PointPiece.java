package model;

import java.util.ArrayList;

public class PointPiece extends ChessPiece{

    protected final static int[] offsetValues = {7, 14};   //the offset of the pieces 
    protected int destination;

    PointPiece(Color color, int position) {
        super(PieceType.POINT, color, position, false, false);
        
    }

    /**
     * Calculates the valid moves for the current PointPiece on the given GameBoard.
     * 
     * @param gameBoard the GameBoard on which the PointPiece is placed
     * @return an ArrayList of integers representing the valid move positions
     */
    public ArrayList<Integer> ValidMoves(GameBoard gameBoard){
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int offset : offsetValues) {
            if(isEnd==false){
                destination = this.position + (this.color.direaction()* offset);
            }else if(isEnd==true){
                destination = this.position + (this.color.oppositeDirection()* offset);
            }
            
            if (BoardLogic.isValidSquareCoordinate(destination)) {
                if (gameBoard.getPiece(destination) == null) {
                    validMoves.add(destination);
                } else if (gameBoard.getPiece(destination).getColor() != this.color) {
                    validMoves.add(destination);
                }
            }
        }
        return validMoves;
    };
    

    @Override
    public String toString(){
        return super.toString();
    }
}

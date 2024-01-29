package model;

import java.util.ArrayList;

public class PointPiece extends ChessPiece{

    protected final static int[] offsetValues = {7, 14};   //the offset of the pieces 
    protected int destination;
    protected boolean yellowIsEnd = false;
    protected boolean blueIsEnd = false;

    PointPiece(Color color, int position) {
        super(PieceType.POINT, color, position, false);     
    }
    public static PointPiece createPointPiece(Color color, int position) {
        return new PointPiece(color, position);
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
            /*Check the piece color then check if the piece reached the end of the board, then the direction of the moves accordingly
             * need to set the position of the piece to get this part of the code to work
             */
            if(this.color.isBlue()){
                bluePieceIsEndSetter(this.position);
                if(blueIsEnd == false){
                    destination = this.position + (this.color.direaction()* offset);
                }else if(blueIsEnd == true){
                    destination = this.position + (this.color.oppositeDirection()* offset);
                }
            }else if(this.color.isYellow()){
                yellowPieceIsEndSetter(this.position);
                if(yellowIsEnd == false){
                    destination = this.position + (this.color.direaction()* offset);
                }else if(yellowIsEnd == true){
                    destination = this.position + (this.color.oppositeDirection()* offset);
                }
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

    /*Check if the point piece reached the end of the board and set the boolean accordingly */
    private void yellowPieceIsEndSetter(int currentPosition){
        if(BoardLogic.FirstRow[currentPosition]){
            yellowIsEnd = true;
        }else if(BoardLogic.SeventhRow[currentPosition]){
            yellowIsEnd = false;
        }
    }

    private void bluePieceIsEndSetter(int currentPosition){
        if(BoardLogic.FirstRow[currentPosition]){
            blueIsEnd = false;
        }else if(BoardLogic.SeventhRow[currentPosition]){
            blueIsEnd = true;
        }
    }
    

    @Override
    public String toString(){
        return super.toString();
    }
}

package model;

import java.util.ArrayList;

public class HourGlassPiece extends ChessPiece {

    protected final static int[] offsetValues = { -15, -13, -9, -5, 5, 9, 13, 15 }; // all the possible valid move of
                                                                                    // the piece
    protected int destination;

    HourGlassPiece(Color color, int position) {
        super(PieceType.HOURGLASS, color, position, false);
    }

    public static HourGlassPiece createHourGlassPiece(Color color, int position) {
        return new HourGlassPiece(color, position);
    }

    // determines valid moves for hourglass piece on game board
    public ArrayList<Integer> ValidMoves(GameBoard gameBoard) {
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int offset : offsetValues) {
            destination = this.position + offset;
            if (FirstColumnOffsetKiller(this.position, offset) ||
                    SecondColumnOffsetKiller(this.position, offset) ||
                    SixthColumnOffsetKiller(this.position, offset) ||
                    SeventhColumnOffsetKiller(this.position, offset)) {
                continue;
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
    }

    // the invalid of offset of the pieces at the specific column
    private static boolean FirstColumnOffsetKiller(int currentPosition, int HourGlassOffset) {
        return BoardLogic.FirstColumn[currentPosition]
                && (HourGlassOffset == -15 || HourGlassOffset == -9 || HourGlassOffset == 5 || HourGlassOffset == 13);
    }

    private static boolean SecondColumnOffsetKiller(int currentPosition, int HourGlassOffset) {
        return BoardLogic.SecondColumn[currentPosition] && (HourGlassOffset == -9 || HourGlassOffset == 5);
    }

    private static boolean SixthColumnOffsetKiller(int currentPosition, int HourGlassOffset) {
        return BoardLogic.SixthColumn[currentPosition] && (HourGlassOffset == -5 || HourGlassOffset == 9);
    }

    private static boolean SeventhColumnOffsetKiller(int currentPosition, int HourGlassOffset) {
        return BoardLogic.SeventhColumn[currentPosition]
                && (HourGlassOffset == -13 || HourGlassOffset == -5 || HourGlassOffset == 9 || HourGlassOffset == 15);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

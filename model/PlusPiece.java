package model;

import java.util.ArrayList;

public class PlusPiece extends ChessPiece {
    protected final static int[] offsetValues = { -7, -1, 1, 7 };
    protected int destination;

    public PlusPiece(Color color, int position) {
        super(PieceType.PLUS, color, position, false);
    }

    public static PlusPiece createPlusPiece(Color color, int position) {
        return new PlusPiece(color, position);
    }

    // determines valid moves for plus piece
    public ArrayList<Integer> ValidMoves(GameBoard gameBoard) {
        ArrayList<Integer> validMoves = new ArrayList<Integer>();
        for (int offset : offsetValues) {
            destination = this.position;
            while (BoardLogic.isValidSquareCoordinate(destination)) {
                destination = destination + offset;
                if (FirstColumnOffsetKiller(this.position, offset) ||
                        SeventhColumnOffsetKiller(this.position, offset)) {
                    break;
                }
                if (offset == 1 && destination % 7 == 0) {
                    break;
                } else if (offset == -1 && (destination + 1) % 7 == 0) {
                    break;
                }
                if (BoardLogic.isValidSquareCoordinate(destination)) {
                    if (gameBoard.getPiece(destination) == null) {
                        validMoves.add(destination);
                    } else if (gameBoard.getPiece(destination).getColor() != this.color) {
                        validMoves.add(destination);
                        break;
                    } else if (gameBoard.getPiece(destination).getColor() == this.color) {
                        break;
                    }
                }
            }
        }
        return validMoves;
    };

    // the invalid of offset of the pieces at the specific column
    private static boolean FirstColumnOffsetKiller(int currentPosition, int PlusPieceOffset) {
        return BoardLogic.FirstColumn[currentPosition] && (PlusPieceOffset == -1);
    }

    private static boolean SeventhColumnOffsetKiller(int currentPosition, int PlusPieceOffset) {
        return BoardLogic.SeventhColumn[currentPosition] && (PlusPieceOffset == 1);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

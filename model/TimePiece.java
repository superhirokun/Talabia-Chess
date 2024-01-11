package model;

public class TimePiece extends ChessPiece {
    private boolean captured;
    private String position;

    public TimePiece(String row, String column, String colour, int turn, boolean pieceSwitch, String position, String pieceName) {
        super(row, column, colour, turn, pieceSwitch, pieceName);
        this.captured = false;
        this.position = position;
    }

    public void checkValidMove(String targetRow, String targetColumn) {
        // Implement the logic for CheckValidMove
    }

    public void checkBlock(String targetRow, String targetColumn) {
        // Implement the logic for CheckBlock
    }
}
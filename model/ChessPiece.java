package model;

public abstract class ChessPiece {
    private String row;
    private String column;
    private String[] colour = {"black","yellow"};
    private int turn;
    private boolean pieceSwitch;
    private boolean captured;
    private String position;

    public ChessPiece(String row, String column, String[] colour, int turn, boolean pieceSwitch) {
        this.row = row;
        this.column = column;
        this.colour = colour;
        this.turn = turn;
        this.pieceSwitch = pieceSwitch;
        this.captured = false;
    }

    public String getColour(Integer colo){
        return this.colour[colo];
    }

    public void pieceCount() {
        // Include logic for pieceCount, aka check how many pieces are there on the board left
        int pieceCount;
    }

    public void pieceSwitch() {
        // Include logic for pieceSwitch, basically switch time and plus every 2 rounds
    }
}

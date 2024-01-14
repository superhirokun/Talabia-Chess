package model;

public abstract class ChessPiece {
    protected Color color;
    protected PieceType pieceType;
    protected int position;
    protected boolean isCaptured;
    
    ChessPiece(PieceType pieceType, Color color,int position, boolean isCaptured) {
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
        this.isCaptured = isCaptured;
    }

    public int getPosition() {
        return this.position;
    }

    public Color getColor() {
        return this.color;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    @Override
    public String toString() {
        if (this.getColor().isBlue()) {
            return this.pieceType.toString().toLowerCase();
        } else {
            return this.pieceType.toString();
        }
    }

    public enum PieceType {
        PLUS("L"), 
        HOURGLASS("H"), 
        TIME("T"), 
        SUN("S"),
        POINT("P");
    
        private String pieceType;
    
        PieceType(String pieceType) {
            this.pieceType = pieceType;
        }
    
        @Override
        public String toString() {
            return this.pieceType;
        }
    }

}

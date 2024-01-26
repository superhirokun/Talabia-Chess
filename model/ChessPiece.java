package model;

import java.util.ArrayList;

public abstract class ChessPiece {
    protected Color color;
    protected PieceType pieceType;
    protected int position;
    protected boolean isCaptured;
    //TODO: add a method to set the isEnd to true when the pointPiece reach the end of the board
    //TODO: add a method to set the isCaptured to true when the pointPiece is captured
    ChessPiece(PieceType pieceType, Color color,int position, boolean isCaptured) {
        this.pieceType = pieceType;
        this.position = position;
        this.color = color;
        this.isCaptured = isCaptured;
    }

    public abstract ArrayList<Integer> ValidMoves(GameBoard gameBoard); //An arraylist of all the valid moves of the piece

    //Setter
    public void setPosition(int position) {
        this.position = position;
    }

    public void setCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }
    
    //Getter
    public int getPosition() {
        return this.position;
    }

    public Color getColor() {
        return this.color;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public ChessPiece getPiece() {
        return this;
    }

    public boolean getCaptured() {
        return this.isCaptured;
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
        PLUS("L") {
            @Override
            public boolean isSunPiece() {
                return false;
            }
        }, 
        HOURGLASS("H") {
            @Override
            public boolean isSunPiece() {
                return false;
            }
        }, 
        TIME("T") {
            @Override
            public boolean isSunPiece() {
                return false;
            }
        }, 
        SUN("S") {
            @Override
            public boolean isSunPiece() {
                return true;
            }
        },
        POINT("P") {
            @Override
            public boolean isSunPiece() {
                return false;
            }
        };
    
        private String pieceType;
    
        PieceType(String pieceType) {
            this.pieceType = pieceType;
        }

        public abstract boolean isSunPiece();
    
        @Override
        public String toString() {
            return this.pieceType;
        }
    }
}
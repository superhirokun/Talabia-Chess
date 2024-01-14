package model;

public class BoardLogic {

    public static final int totalSquare = 42;   //the total number of square on the board
    public static final int totalColumn = 7;    //the total number of column on the board

    public static boolean isValidSquareCoordinate(int coordinate){    //check if the coordinate given is out of bound
        return coordinate >= 0 && coordinate < totalSquare;
    }

    public static String[] zaFENDecoder(String zaFEN){
        String zaFENString = zaFEN.split(" ")[0];
        int zalength = zaFENString.length();
        String[] zaBoardPiece = new String[BoardLogic.totalSquare];
        int zaCounter = 0;
        for(int i = 0; i < zalength; i++){
            char zaCurrentChar = zaFENString.charAt(i);
            if(zaCurrentChar == '/'){
                continue;
            }
            if(Character.isDigit(zaCurrentChar)){
                for(int j = 0; j < Character.getNumericValue(zaCurrentChar); j++){
                    zaBoardPiece[zaCounter] = "-";
                    zaCounter++;
                }
                
            }else{
                zaBoardPiece[zaCounter] = String.valueOf(zaCurrentChar);
                zaCounter++;
            }
        }
        return zaBoardPiece;   
    }

    public static Color zaColorDecoder(String zaFEN){
        String zaMover = zaFEN.split(" ")[1];
        if(zaMover.equals("y")){
            return Color.Yellow;
        }else{
            return Color.Blue;
        }
    }

    public static int zaTurnDecoder(String zaFEN){
        String zaTurn = zaFEN.split(" ")[2];
        return Integer.parseInt(zaTurn);
    }
}

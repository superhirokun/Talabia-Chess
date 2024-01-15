package model;

public class BoardLogic {

    public static final int totalSquare = 42;   //the total number of square on the board
    public static final int totalColumn = 7;    //the total number of column on the board

    public static boolean[] FirstColumn = intializedColumn(0);    //the first column of the board
    public static boolean[] SecondColumn = intializedColumn(1);   //the second column of the board
    public static boolean[] SixthColumn = intializedColumn(5);    //the sixth column of the board
    public static boolean[] SeventhColumn = intializedColumn(6);  //the seventh column of the board

    //create a boolean array that represent a column on the board and set the column to true and the rest to false
    private static boolean[] intializedColumn(int Column){  
        boolean[] column = new boolean[totalSquare];
        do{
            column[Column] = true;
            Column += totalColumn;
        }while(Column < totalSquare);
        return column;
    }

    public static boolean isValidSquareCoordinate(int coordinate){    //check if the coordinate given is out of bound
        return coordinate >= 0 && coordinate < totalSquare;
    }

    /**
     * Decodes the given zaFEN string and returns an array of board pieces.
     * 
     * @param zaFEN the zaFEN string to be decoded
     * @return an array of board pieces representing the decoded zaFEN string
     */
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

    /**
     * Decodes the color from the given FEN string.
     */
    public static Color zaColorDecoder(String zaFEN){
        String zaMover = zaFEN.split(" ")[1];
        if(zaMover.equals("y")){
            return Color.Yellow;
        }else{
            return Color.Blue;
        }
    }

    /**
     * Decodes the turn number from the given FEN string.
     * 
     * @param zaFEN the FEN string representing the chess position
     * @return the turn number decoded from the FEN string
     */
    public static int zaTurnDecoder(String zaFEN){
        String zaTurn = zaFEN.split(" ")[2];
        return Integer.parseInt(zaTurn);
    }
}

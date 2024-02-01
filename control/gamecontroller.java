package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// push the code again homie, ur previous code didnt save the previous color so i edited it abit
import model.*;
import model.GameBoard.BobTheBuilder;
import view.gameviewer;

public class gamecontroller extends BoardLogic {
    static BobTheBuilder building = new BobTheBuilder();

    /* make a move by generating a new board */
    public static GameBoard makeMoveBoardLogic(ChessPiece piece, int destination, GameBoard gamer) { // make the move
        BobTheBuilder builder = new BobTheBuilder();
        int initialPosition = piece.getPosition();
        HashMap<Integer, ChessPiece> prevPiecePosition = new HashMap<>();
        System.out.println("can switch : " + canSwitch);
        prevPiecePosition = gamer.getAllPiecePosition(); // get the previous position of the piece
        for (int i = 0; i < BoardLogic.totalSquare; i++) {
            if (prevPiecePosition.get(i) != null) { // check if the piece is not null
                if (prevPiecePosition.get(i).getPosition() == initialPosition) {
                    prevPiecePosition.get(i).setPosition(destination); // set the position of the piece to the new
                                                                       // position which is the destination
                    builder.placePiece(gamer, prevPiecePosition.get(i), destination);
                    builder.placePiece(gamer, null, i);
                } else if (prevPiecePosition.get(i).getPosition() == destination
                        && prevPiecePosition.get(i).getCaptured() == true) { // check if the piece is captured
                    continue;
                } else if (canSwitch == true) { // switch the time piece with the plus piece when the turn is even
                    if (gamer.plusPiece.get(i) == null) {
                        if (gamer.timePiece.get(i) == null) {
                            builder.placePiece(gamer, prevPiecePosition.get(i), i);
                        } else if (prevPiecePosition.get(i).getPosition() == gamer.timePiece.get(i).getPosition()) {
                            builder.placePiece(gamer, PlusPiece.createPlusPiece(gamer.timePiece.get(i).getColor(), i),
                                    i);
                            // gamer.plusPiece.put(i,
                            // PlusPiece.createPlusPiece(gamer.timePiece.get(i).getColor(), i));

                        }
                    } else if (gamer.timePiece.get(i) == null) {
                        if (gamer.plusPiece.get(i) == null) {
                            builder.placePiece(gamer, prevPiecePosition.get(i), i);
                        } else if (prevPiecePosition.get(i).getPosition() == gamer.plusPiece.get(i).getPosition()) {
                            builder.placePiece(gamer, TimePiece.createTimePiece(gamer.plusPiece.get(i).getColor(), i),
                                    i);
                            // gamer.timePiece.put(i,
                            // TimePiece.createTimePiece(gamer.plusPiece.get(i).getColor(), i));
                        }

                    }

                } else {
                    builder.placePiece(gamer, prevPiecePosition.get(i), i); // if the piece is not the piece that is
                                                                            // moving then place the piece at the same
                                                                            // position
                }
            } else {
                builder.placePiece(gamer, null, i); // if the piece is null then place a null piece
            }
            getWinner(prevPiecePosition);
        }
        setBob(builder);
        return builder.build();
    }

    public static void setBob(BobTheBuilder builderer) {
        building = builderer;
    }

    public static BobTheBuilder getBob() {
        return building;
    }

    /* decode the fen string into */
    public static String[] zaFENDecoder(String zaFEN) {
        String zaFENString = zaFEN.split(" ")[0];
        int zalength = zaFENString.length();
        String[] zaBoardPiece = new String[BoardLogic.totalSquare];
        int zaCounter = 0;
        for (int i = 0; i < zalength; i++) {
            char zaCurrentChar = zaFENString.charAt(i);
            if (zaCurrentChar == '/') {
                continue;
            }
            if (Character.isDigit(zaCurrentChar)) {
                for (int j = 0; j < Character.getNumericValue(zaCurrentChar); j++) {
                    zaBoardPiece[zaCounter] = "-";
                    zaCounter++;
                }

            } else {
                zaBoardPiece[zaCounter] = String.valueOf(zaCurrentChar);
                zaCounter++;
            }
        }
        return zaBoardPiece;
    }

    // colour decoding from the given FEN string.

    public static Color zaColorDecoder(String zaFEN) {
        String zaMover = zaFEN.split(" ")[1];
        if (zaMover.equals("y")) {
            return Color.Yellow;
        } else {
            return Color.Blue;
        }
    }

    // decodes turn number from FEN string
    public static int zaTurnDecoder(String zaFEN) {
        String zaTurn = zaFEN.split(" ")[2];
        return Integer.parseInt(zaTurn);
    }

    // hashmap encoder of chess pieces into string
    public static String zaEncoder(HashMap<Integer, ChessPiece> zaHash, Integer turn) {
        StringBuilder sb = new StringBuilder();
        int emptySquareCount = 0;

        for (int i = 0; i < BoardLogic.totalSquare; i++) {
            if (i != 0 && i % BoardLogic.numOfSquarePerRow == 0) {
                if (emptySquareCount != 0) {
                    sb.append(emptySquareCount);
                    emptySquareCount = 0;
                }
                sb.append("/");
            }

            if (zaHash.get(i) != null) {
                if (emptySquareCount != 0) {
                    sb.append(emptySquareCount);
                    emptySquareCount = 0;
                }
                sb.append(zaHash.get(i).toString());
            } else {
                emptySquareCount++;
            }
        }

        if (emptySquareCount != 0) {
            sb.append(emptySquareCount);
        }

        String playerColor = BoardLogic.isYellowTurn(turn) ? "y" : "b";
        sb.append(" " + playerColor + " " + turn);

        return sb.toString();
    }

    public static void startGame() {
        System.out.println("Game is starting...");

    }

    public static void resetGame() {
        System.out.println("Reseti(french for reset)");
    }

    public static ArrayList<Integer> isValidMove(ChessPiece piece, int sourcePosition, GameBoard gameBoard) {
        // failed arraylist to check for failure (returns an array list with only -1)
        ArrayList<Integer> failed = new ArrayList<>();
        failed.add(-1);
        switch ((piece.toString().charAt(0))) { // placeholder but it should check the piece in a selected tile, turn to
                                                // string, and initiate switch case
            case 'L':
                // Handle PLUS piece
                PlusPiece plusY = PlusPiece.createPlusPiece(Color.Yellow, sourcePosition); // should be the same for all
                                                                                           // others with slight
                                                                                           // variations
                return plusY.ValidMoves(gameBoard);

            case 'H':
                // Handle HOURGLASS piece
                HourGlassPiece hourglassY = HourGlassPiece.createHourGlassPiece(Color.Yellow, sourcePosition);
                return hourglassY.ValidMoves(gameBoard);

            case 'T':
                // Handle TIME piece
                TimePiece timeY = TimePiece.createTimePiece(Color.Yellow, sourcePosition);
                return timeY.ValidMoves(gameBoard);

            case 'S':
                // Handle SUN piece
                SunPiece sunY = SunPiece.createSunPiece(Color.Yellow, sourcePosition);
                return sunY.ValidMoves(gameBoard);

            case 'P':
                // Handle POINT piece
                PointPiece pointY = PointPiece.createPointPiece(Color.Yellow, sourcePosition);
                return pointY.ValidMoves(gameBoard);

            case 'l':
                // Handle PLUS piece (Blue)
                PlusPiece plusB = PlusPiece.createPlusPiece(Color.Blue, sourcePosition); // should be the same for all
                                                                                         // others with slight
                                                                                         // variations
                return plusB.ValidMoves(gameBoard);

            case 'h':
                // Handle HOURGLASS piece
                HourGlassPiece hourglassB = HourGlassPiece.createHourGlassPiece(Color.Blue, sourcePosition); // should
                                                                                                             // be the
                                                                                                             // same for
                                                                                                             // all
                                                                                                             // others
                                                                                                             // with
                                                                                                             // slight
                                                                                                             // variations
                return hourglassB.ValidMoves(gameBoard);

            case 't':
                // Handle TIME piece
                TimePiece timeB = TimePiece.createTimePiece(Color.Blue, sourcePosition);
                return timeB.ValidMoves(gameBoard);

            case 's':
                // Handle SUN piece
                SunPiece sunB = SunPiece.createSunPiece(Color.Blue, sourcePosition);
                return sunB.ValidMoves(gameBoard);

            case 'p':
                // Handle POINT piece
                PointPiece pointB = PointPiece.createPointPiece(Color.Blue, sourcePosition);
                return pointB.ValidMoves(gameBoard);

            default:
                // Handle default case or return false if necessary
                break;
        }
        return failed;
    }

    public static void saveGame(GameBoard gameBoard, Integer turn, String fileName) {
        String fenString = zaEncoder(gameBoard.getPiecePosition(), turn);

        // set the filename to a specified value or use a default (if null)
        String filePath = (fileName != null && !fileName.isEmpty()) ? fileName : "saveFile.txt";

        // write FEN string to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(fenString);
            System.out.println("Game saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving the game.");
        }
    }

    public static String loadGame(String fileName) {
        // file path
        String filePath = (fileName != null && !fileName.isEmpty()) ? fileName : "saveFile.txt"; // making sure file
                                                                                                 // exist and has
                                                                                                 // content, otherwise
                                                                                                 // default file name
                                                                                                 // saveFile.txt
        String test = "-1";

        // FEN string reader
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String fenString = reader.readLine(); // reads file and puts them as a string

            if (fenString != null) {
                // decode the FEN string
                System.out.println(fenString);
                String[] decodedFEN = zaFENDecoder(fenString); // decoding the string and then put into array
                test = Arrays.toString(decodedFEN);
                return test;
            } else {
                System.out.println("The file is empty.");
                return test;
            }
        } catch (IOException e) { // catching errors
            e.printStackTrace();
            System.err.println("Error loading the game.");
            return test;
        }
    }

}

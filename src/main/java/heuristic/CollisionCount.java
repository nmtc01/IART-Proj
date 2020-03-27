package heuristic;
import java.awt.*;
import java.util.ArrayList;

public class CollisionCount implements Heuristic {

    @Override
    public int getValue(ArrayList<ArrayList<Integer>> board) {
        int totalCollisions = 0;

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(0).size(); j++) {
                if (board.get(i).get(j) > 0) {
                    totalCollisions = totalCollisions + countPieceCollisions(board, new Point(i,j));
                }
            }
        }
        return totalCollisions;
    }

    private int countPieceCollisions(ArrayList<ArrayList<Integer>> board, Point piece) {
        int numberOfCollisions = 0;

        int line = piece.x;
        int column = piece.y;
        int pieceValue = board.get(piece.x).get(piece.y);

        /*System.out.println(" Line ");
        System.out.print(line);
        System.out.println(" Column ");
        System.out.print(column);*/

        for (int i = 1; i <= pieceValue; i++) {
            int lineDown = line + i;
            int lineUp = line - i;
            int columnRight = column + i;
            int columnLeft = column - i;

            if (lineDown < board.size()) {
                /*System.out.println(" LineDown ");
                System.out.print(lineDown);
                System.out.println(" Board Size ");
                System.out.print(board.size());*/
                if (board.get(lineDown).get(column) == -1) {
                    numberOfCollisions++;
                }
            }

            if (lineUp >= 0) {
                /*System.out.println(" LineUp ");
                System.out.print(lineUp);
                System.out.println(" Board Size ");
                System.out.print(board.size());*/
                if (board.get(lineUp).get(column) == -1) {
                    numberOfCollisions++;
                }
            }

            if (columnRight < board.size()) {
                /*System.out.println(" ColumnRight ");
                System.out.print(columnRight);
                System.out.println(" Board Size ");
                System.out.print(board.size());*/
                if (board.get(line).get(columnRight) == -1){
                    numberOfCollisions++;
                }
            }

            if (columnLeft >= 0) {
                /*System.out.println(" ColumnRight ");
                System.out.print(columnLeft);
                System.out.println(" Board Size ");
                System.out.print(board.size());*/
                if (board.get(line).get(columnLeft) == -1) {
                    numberOfCollisions++;
                }
            }
        }

        return numberOfCollisions;
    }
}

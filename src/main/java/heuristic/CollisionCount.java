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
                    totalCollisions = totalCollisions + countPieceCollistions(board, new Point(i,j));
                }
            }
        }
        return totalCollisions;
    }

    private int countPieceCollistions(ArrayList<ArrayList<Integer>> board, Point piece) {
        int numberOfCollisions = 0;

        int line = piece.x;
        int column = piece.y;
        int pieceValue = board.get(piece.x).get(piece.y);

        for (int i = 1; i <= pieceValue; i++) {
            if (board.get(line + i).get(column) == -1)
                numberOfCollisions++;

            if (board.get(line - i).get(column) == -1)
                numberOfCollisions++;

            if (board.get(line).get(column + i) == -1)
                numberOfCollisions++;

            if (board.get(line + i).get(column - i) == -1)
                numberOfCollisions++;
        }

        return numberOfCollisions;
    }
}

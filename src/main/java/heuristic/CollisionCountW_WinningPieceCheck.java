package heuristic;

import java.awt.*;
import java.util.ArrayList;

public class CollisionCountW_WinningPieceCheck implements Heuristic {
    @Override
    public int getValue(ArrayList<ArrayList<Integer>> board) {
        int totalCollisions = 0;

        if (checkCandidateWinningPiece(board)) {
            for (int i = 0; i < board.size(); i++) {
                for (int j = 0; j < board.get(i).size(); j++) {
                    if (board.get(i).get(j) > 0) {
                        totalCollisions = totalCollisions + countPieceCollistions(board, new Point(i, j));
                    }
                }
            }
        }
        return totalCollisions;
    }

    private boolean checkCandidateWinningPiece(ArrayList<ArrayList<Integer>> board) {
        Point goal = new Point(-1, -1);

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == -2) {
                    goal.setLocation(i, j);
                    break;
                }
            }
        }

        if (goal.x == -1)
            return true;

        // Fixed Column
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).get(goal.y) > 0) {
                return true;
            }
        }

        // Fixed Line
        for (int j = 0; j < board.get(0).size(); j++) {
            if (board.get(goal.x).get(j) > 0) {
                return true;
            }
        }

        return false;
    }

    private int countPieceCollistions(ArrayList<ArrayList<Integer>> board, Point piece) {
        int numberOfCollisions = 0;

        int line = piece.y;
        int column = piece.x;
        int pieceValue = board.get(piece.y).get(piece.x);

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

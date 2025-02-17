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
                        totalCollisions = totalCollisions + countPieceCollisions(board, new Point(i, j));
                    }
                }
            }
        }
        else totalCollisions = -1;
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

        // Check Column
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).get(goal.y) > 0) {
                return true;
            }
        }

        // Check Line
        for (int j = 0; j < board.get(0).size(); j++) {
            if (board.get(goal.x).get(j) > 0) {
                return true;
            }
        }

        return false;
    }

    private int countPieceCollisions(ArrayList<ArrayList<Integer>> board, Point piece) {
        int numberOfCollisions = 0;

        int line = piece.x;
        int column = piece.y;
        int pieceValue = board.get(piece.x).get(piece.y);

        for (int i = 1; i <= pieceValue; i++) {
            int lineDown = line + i;
            int lineUp = line - i;
            int columnRight = column + i;
            int columnLeft = column - i;

            while(true) {
                if (lineDown < board.size()) {
                    if (board.get(lineDown).get(column) == -1) {
                        numberOfCollisions++;
                        break;
                    }
                    else if (board.get(lineDown).get(column) > 0) {
                        lineDown++;
                    }
                    else break;
                }
                else break;
            }

            while(true) {
                if (lineUp >= 0) {
                    if (board.get(lineUp).get(column) == -1) {
                        numberOfCollisions++;
                        break;
                    }
                    else if (board.get(lineUp).get(column) > 0) {
                        lineUp--;
                    }
                    else break;
                }
                else break;
            }

            while (true) {
                if (columnRight < board.size()) {
                    if (board.get(line).get(columnRight) == -1) {
                        numberOfCollisions++;
                        break;
                    }
                    else if (board.get(line).get(columnRight) > 0) {
                        columnRight++;
                    }
                    else break;
                }
                else break;
            }

            while(true) {
                if (columnLeft >= 0) {
                    if (board.get(line).get(columnLeft) == -1) {
                        numberOfCollisions++;
                        break;
                    }
                    else if (board.get(line).get(columnLeft) > 0) {
                        columnLeft++;
                    }
                    else break;
                }
                else break;
            }
        }

        return numberOfCollisions;
    }
}

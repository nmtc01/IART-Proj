import java.util.ArrayList;

public class PlayerMode extends Solver {
    //private UI UI = new UI();

    public PlayerMode(ArrayList<ArrayList<Integer>> initboard){//, UI UI) {
        super(initboard);
        //this.UI = UI;
    }

    /*public void play() {
        Integer line;
        Integer column;

        while (!isEnd(getCurrentState())) {
            //Get piece to move
            while (true) {
                Pair<Integer, Integer> piece_coords = UI.get_piece_to_move();
                line = piece_coords.getKey();
                column = piece_coords.getValue();
                if (check_if_playable(column, line, getCurrentState().getData()))
                    break;
            }

            //Get direction of the movement
            Character direction = UI.get_move_direction();

            //Move
            switch (direction) {
                case 'U':
                case 'u': {
                    moveUp(column, line, getCurrentState().getData());
                    break;
                }
                case 'D':
                case 'd': {
                    moveDown(column, line, getCurrentState().getData());
                    break;
                }
                case 'L':
                case 'l': {
                    moveLeft(column, line, getCurrentState().getData());
                    break;
                }
                case 'R':
                case 'r': {
                    moveRight(column, line, getCurrentState().getData());
                    break;
                }
            }

            //Show puzzle
            UI.draw_puzzle(getCurrentState().getData());
        }
    }*/
}

/*package com.iart;

        import java.awt.*;
        import java.util.ArrayList;

public class PlayerMode extends Solver {
    private UI UI = new UI();

    public PlayerMode(ArrayList<ArrayList<Integer>> initboard, UI UI) {
        super(initboard);
        this.UI = UI;
    }

    public void play() {
        Integer line;
        Integer column;

        while (!isEnd(getCurrentState())) {
            // Get piece to move
            while (true) {
                Point piece_coords = UI.get_piece_to_move();

                /** TODO: Check compatibility */
                /*
                 * line = piece_coords.getKey(); column = piece_coords.getValue();
                 */

                /*line = piece_coords.y;
                column = piece_coords.x;

                if (check_if_playable(line, column, getCurrentState().getData()))
                    break;
            }

            // Get direction of the movement
            Character direction = UI.get_move_direction();

            // Move
            switch (direction) {
                case 'U':
                case 'u': {
                    moveUp(column, line, getCurrentState().getData());
                    break;
                }
                case 'D':
                case 'd': {
                    moveDown(column, line, getCurrentState().getData());
                    break;
                }
                case 'L':
                case 'l': {
                    moveLeft(column, line, getCurrentState().getData());
                    break;
                }
                case 'R':
                case 'r': {
                    moveRight(column, line, getCurrentState().getData());
                    break;
                }
            }

            // Show puzzle
            UI.draw_puzzle(getCurrentState().getData());
        }
    }
}
*/

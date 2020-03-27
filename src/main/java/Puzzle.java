import com.googlecode.lanterna.screen.Screen;
import heuristic.CollisionCount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Puzzle {
    public enum keyEvent{
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT
    }

    private String filename;
    private ArrayList<ArrayList<Integer>> board;
    private Cursor cursor = new Cursor();
    private Event event;
    private GameView gameView;
    private Window window;
    private keyEvent keyEvent;

    public Puzzle(String filename)  {
       this.filename = filename;
       this.board = this.read_puzzle();
       this.window = new Window(board.size());
       this.gameView = new GameView(this.window.getScreen(), board, cursor);
       this.event = new Event(window.getScreen(), keyEvent);
    }

    /* ---- Utils  ---- */
    public ArrayList<ArrayList<Integer>> read_puzzle(){
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>(); //todo rever String a ser usada
        //open file
        try{
            final File puzzle_file  = new File("res/puzzle/"+this.filename+".csv");
            final Scanner reader = new Scanner(puzzle_file);

            while (reader.hasNextLine()) {
                //increment line counter
                final String data = reader.nextLine();
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                //split commas
                final String[] line = data.split("\\,");
                for(int i =0; i<line.length; i++){
                    if(line[i].contains("B")){
                        tmp.add(0);
                    }else if (line[i].contains("G")){
                        tmp.add(-2);
                    }else {
                        tmp.add(Integer.valueOf(line[i]));
                    }
                }
                //push line to the matrix
                matrix.add(tmp);
            }
            reader.close();
            
        }catch (final FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return matrix;
    }

    public ArrayList<ArrayList<Integer>> getBoard(){
        return this.board;
    }

    public void run() throws IOException {
        /** Algorithm Testing*/
        Greedy algorithm = new Greedy(this.getBoard());

        draw(this.getBoard());
        System.out.println("");

        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = algorithm.perform();

        ArrayList<ArrayList<Integer>> finalBoard = s.getData();

        draw(finalBoard);

        /*while (true) {
            this.gameView.run();
            event.processKey();
            cursor.processKeyEvent(this.keyEvent);
        }*/
    }

    public void draw(ArrayList<ArrayList<Integer>> matrix){
        System.out.print('\n');
        int mat_size = matrix.get(0).size();
        System.out.print(' ');
        System.out.print(' ');
        for(int top = 0;top<mat_size; top++){

            System.out.print(' ');
            System.out.print(' ');
            System.out.print(' ');
            System.out.print(top);
            System.out.print(' ');
            System.out.print(' ');
        }
        System.out.print("\n");

        //draw first line on the console
        System.out.print(' ');
        System.out.print(' ');
        System.out.print('╔');
        for(int top = 0;top<matrix.get(0).size(); top++){

            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            if(top < mat_size -1)
                System.out.print('╦');
            else
                System.out.print('╗');

        }
        System.out.print("\n");

        //draw other lines
        for(int line=0;line<matrix.size(); line++){
            System.out.print(line);
            System.out.print(' ');
            for(int mid = 0;mid<matrix.get(line).size(); mid++){
                System.out.print('║');

                if(matrix.get(line).get(mid) == 0){
                    System.out.print(' ');
                    System.out.print(' ');
                    System.out.print(' ');
                    System.out.print(' ');
                    System.out.print(' ');
                }else if(matrix.get(line).get(mid) == -1){
                    System.out.print('▒');
                    System.out.print('▒');
                    System.out.print('▒');
                    System.out.print('▒');
                    System.out.print('▒');
                } else if(matrix.get(line).get(mid) == -2) {
                    System.out.print(' ');
                    System.out.print(' ');
                    System.out.print('G');
                    System.out.print(' ');
                    System.out.print(' ');
                } else  {
                    System.out.print('░');
                    System.out.print('░');
                    System.out.print(matrix.get(line).get(mid));
                    System.out.print('░');
                    System.out.print('░');
                }
                if(mid == mat_size -1 )
                    System.out.print('║');
            }
            System.out.print("\n");
            if(line < mat_size -1) {
                for (int top = 0; top < matrix.get(line).size(); top++) {
                    if (top == 0){
                        System.out.print(' ');
                        System.out.print(' ');
                        System.out.print('╠');
                    }
                    System.out.print('═');
                    System.out.print('═');
                    System.out.print('═');
                    System.out.print('═');
                    System.out.print('═');
                    if (top < mat_size - 1)
                        System.out.print('╬');
                    else System.out.print('╣');
                }
                System.out.print("\n");
            }
        }

        //draw last line on the console
        System.out.print(' ');
        System.out.print(' ');
        System.out.print('╚');
        for(int bot = 0;bot<matrix.get(0).size(); bot++){
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            if(bot < mat_size -1)
                System.out.print('╩');
            else System.out.print('╝');
        }
        System.out.print("\n");
    }
}

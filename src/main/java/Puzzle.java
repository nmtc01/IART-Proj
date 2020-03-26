import com.googlecode.lanterna.screen.Screen;

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
        while (true) {
            this.gameView.run();
            event.processKey();
            cursor.processKeyEvent(this.keyEvent);
        }
    }
}

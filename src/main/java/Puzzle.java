import com.googlecode.lanterna.screen.Screen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Puzzle {
    public enum keyEvent{
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        SELECT,
        UNSELECT,
        UNDO,
        STOP
    }

    private String filename;
    private ArrayList<ArrayList<Integer>> board;
    private Cursor cursor;
    private Event event;
    private GameView gameView;
    private Window window;
    private keyEvent keyEvent;

    public Puzzle(String filename)  {
       this.filename = filename;
       this.board = this.read_puzzle();
       this.window = new Window(board.size());
       this.cursor = new Cursor(board.size());
       this.gameView = new GameView(this.window.getScreen(), board, cursor);
       this.event = new Event(window.getScreen(), this.keyEvent);
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
            System.exit(1); //todo change evetually to ask for a new input later if time
        }

        return matrix;
    }

    public ArrayList<ArrayList<Integer>> getBoard(){
        return this.board;
    }

    public int getTileValue(int x, int y){
        return this.board.get(y).get(x);
    }

    public void run() throws IOException {
        while (true) {
            this.gameView.run();
            this.keyEvent = event.processKey();
            cursor.processKeyEvent(this.keyEvent);
        }
    }
}

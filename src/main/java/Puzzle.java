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
    private ArrayList<ArrayList<ArrayList<Integer>>> previousBoards; //todo
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
       this.cursor = new Cursor(this);
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

    //game calculation;
    public int[][] getPossibleMoves(int x, int y){ //always returns 4 moves no matter what
        int[][] possibleMoves = new int[4][1];
        int tileVal = this.getTileValue(x,y);
        int size = this.board.get(0).size();

        int i = tileVal;
        int carry = 0;

        //top moves
        carry = y;
        while (i != 0){
            if( carry <= 0 || carry+1 >= size)
                break;
            if(this.board.get(carry-1).get(x) == 0)
                i--;
            carry--;

        }
        possibleMoves[0] = new int[]{x, carry};
        i = 0;

        //bottom moves
        carry = y;
        while (i < tileVal){
            if( carry <= 0 || carry+1 >= size)
                break;
            if(this.board.get(carry+1).get(x) == 0)
                i++;
            carry++;
        }
        i = 0;
        possibleMoves[1] = new int[]{x, carry};

        //left moves
        carry = x;
        while (i < tileVal){
            if( carry <= 0 || carry+1 >= size)
                break;
            if(this.board.get(y).get(carry-1) == 0)
                i++;
            carry--;
        }
        i = 0;
        possibleMoves[2] = new int[]{carry, y};

        //right moves
        carry = x;
        while (i < tileVal){
            if( carry <= 0 || carry+1 >= size)
                break;
            if(this.board.get(y).get(carry+1) == 0)
                i++;
            carry++;
        }
        possibleMoves[3] = new int[]{carry, y};

        return possibleMoves;
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

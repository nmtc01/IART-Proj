import com.googlecode.lanterna.screen.Screen;
import solver.*;
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
        UNDO,
        STOP,
        QUIT
    }

    private String filename;
    private ArrayList<ArrayList<ArrayList<Integer>>> previousBoards;
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
       this.gameView = new GameView(this.window.getScreen(), this.board, this.cursor);
       this.event = new Event(window.getScreen(), this.keyEvent);
       this.previousBoards = new ArrayList<ArrayList<ArrayList<Integer>>>();
    }

    public void run() throws IOException {
        /** Algorithm Testing*/
        /*Greedy algorithm = new Greedy(this.getBoard());
        draw(this.getBoard());
        System.out.println("");
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = algorithm.perform();
        ArrayList<ArrayList<Integer>> finalBoard = s.getData();
        draw(finalBoard);

        DFS dfs = new DFS((this.getBoard()));
        draw(this.getBoard());
        System.out.println("");
        ExpansionTree.Node<ArrayList<ArrayList<Integer>>> s = dfs.perform();
        ArrayList<ArrayList<Integer>> finalBoard = s.getData();
        draw(finalBoard);
        */
        while (true) {
            this.gameView.run();
            this.keyEvent = event.processKey();
            cursor.processKeyEvent(this.keyEvent);
        }
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
        if(tileVal == 0){
            int[][] noMoves = new int[0][]; 
            return noMoves; 
        }
        
        int size = this.board.get(0).size();

        int i = tileVal;
        int carry = 0;

        //top moves
        carry = y;
        while (i != 0){
            if( carry <= 0 || carry+1 >= size)
                break;
            if(this.board.get(carry-1).get(x) != -1)
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
            if(this.board.get(carry+1).get(x) != -1)
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
            if(this.board.get(y).get(carry-1) != -1)
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
            if(this.board.get(y).get(carry+1) != -1)
                i++;
            carry++;
        }
        possibleMoves[3] = new int[]{carry, y};

        return possibleMoves;
    }
    public void moveTileUp(int x, int y ){
        ArrayList<ArrayList<Integer>> prev = new ArrayList<>();
        for (int i = 0; i < this.board.size(); i++) {
            ArrayList<Integer> p = (ArrayList<Integer>) this.board.get(i).clone();
            prev.add(p);
        }
        this.previousBoards.add(prev);

        int val = this.getTileValue(x,y);
        this.board.get(y).set(x,-1);
        int i = y;
        while(val > 0){
            i--;
            if(this.board.get(i).get(x) == 0 || this.board.get(i).get(x) == -2){
                this.board.get(i).set(x,-1);
                val--;
            }
            if(i == 0){
                break;
            }
        }
    }
    public void moveTileDown(int x, int y ){
        ArrayList<ArrayList<Integer>> prev = new ArrayList<>();
        for (int i = 0; i < this.board.size(); i++) {
            ArrayList<Integer> p = (ArrayList<Integer>) this.board.get(i).clone();
            prev.add(p);
        }
        previousBoards.add(prev);

        int val = this.getTileValue(x,y);
            this.board.get(y).set(x,-1);
            int i = y;
            while(val > 0){
                i++;
                if(i == this.board.get(0).size()){
                    break;
                }
                if(this.board.get(i).get(x) == 0 || this.board.get(i).get(x) == -2) {
                    this.board.get(i).set(x, -1);
                    val--;
                }
            }

    }
    public void moveTileLeft(int x, int y ){
        ArrayList<ArrayList<Integer>> prev = new ArrayList<>();
        for (int i = 0; i < this.board.size(); i++) {
            ArrayList<Integer> p = (ArrayList<Integer>) this.board.get(i).clone();
            prev.add(p);
        }
        previousBoards.add(prev);

        int val = this.getTileValue(x,y);
        this.board.get(y).set(x,-1);
            int i = x;
            while(val > 0){
                i--;
                if(this.board.get(y).get(i) == 0 || this.board.get(y).get(i) == -2){
                    this.board.get(y).set(i,-1);
                    val--;
                }
                if(i == 0){
                    break;
                }
        }
    }
    public void moveTileRight(int x, int y ){
        ArrayList<ArrayList<Integer>> prev = new ArrayList<>();
        for (int i = 0; i < this.board.size(); i++) {
            ArrayList<Integer> p = (ArrayList<Integer>) this.board.get(i).clone();
            prev.add(p);
        }
        this.previousBoards.add(prev);

        int val = this.getTileValue(x,y);
            this.board.get(y).set(x,-1);
            int i = x;
            while(val > 0){
                i++;
                if(i == this.board.get(0).size()){
                    break;
                }
                if(this.board.get(y).get(i) == 0 || this.board.get(y).get(i) == -2){
                    this.board.get(y).set(i,-1);
                    val--;
                }
            }

    }
    public void undoMove() {
        if (previousBoards.size() > 0) {
            this.board = previousBoards.get(previousBoards.size() - 1);
            this.gameView.setBoard(board);
            previousBoards.remove(previousBoards.size() - 1);
        }
    }

    public ArrayList<ArrayList<Integer>> getBoard(){
        return this.board;
    }

    public int getTileValue(int x, int y){
        return this.board.get(y).get(x);
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

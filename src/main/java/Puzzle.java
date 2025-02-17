import com.googlecode.lanterna.screen.Screen;
import menu.states.Mode;
import solver.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class Puzzle {
    public enum keyEvent{
        MOVE_UP,
        MOVE_DOWN,
        MOVE_LEFT,
        MOVE_RIGHT,
        HINT,
        SELECT,
        UNDO,
        STOP,
        QUIT
    }

    private String filename;
    private String gameMode;
    private String algorithm;
    private ArrayList<ArrayList<ArrayList<Integer>>> previousBoards;
    private ArrayList<ArrayList<Integer>> board;
    private ArrayList<ArrayList<ArrayList<Integer>>> solution;
    private int hintCounter;
    private Cursor cursor;
    private Event event;
    private GameView gameView;
    private Window window;
    private keyEvent keyEvent;

    public Puzzle(String filename,String gameMode,String algorithm, Window window)  {
       this.filename = filename;
       this.gameMode = gameMode;
       this.algorithm = algorithm;
       this.board = this.read_puzzle();
       this.window = window; //new Window(board.size());
       this.cursor = new Cursor(this);
        this.gameView = new GameView(this.window.getScreen(), this.board, this.cursor);
        this.event = new Event(window.getScreen(), this.keyEvent);
       this.previousBoards = new ArrayList<ArrayList<ArrayList<Integer>>>();
    }

    public void run() throws IOException, InterruptedException {

        System.out.println(this.algorithm);
        System.out.println(this.gameMode);

        //Calculate Algo
        cursor.hide();
        this.gameView.run();

        /**
         * Time Start
         * */
        long start = System.currentTimeMillis();

        //Get algo type
        switch(this.algorithm){
            case "BFS":
                BFS bfs = new BFS(this.getBoard());
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> bfs_root = bfs.perform();
                this.solution = bfs.getSolution(bfs_root);
                break;
            case "DFS":
                DFS dfs = new DFS(this.getBoard());
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> dfs_root = dfs.perform();
                this.solution = dfs.getSolution(dfs_root);
                break;
            case "Uniform Cost":
                UniformCost uniformCost = new UniformCost(this.getBoard());
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> uniformCost_root = uniformCost.perform();
                this.solution = uniformCost.getSolution(uniformCost_root);
                break;
            case "A*":
                AStar aStar = new AStar(this.getBoard());
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> aStar_root = aStar.perform(); //TODO
                this.solution = aStar.getSolution(aStar_root);
                break;
            default:
                Greedy greedy = new Greedy(this.getBoard());
                ExpansionTree.Node<ArrayList<ArrayList<Integer>>> greedy_root = greedy.perform();
                this.solution = greedy.getSolution(greedy_root);
                break;


        }

        /**
         * Time Finish
         * */
        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;

        System.out.println("Time Elapsed (ms): " + timeElapsed);


        //Draw puzzle based on game mode
        switch (this.gameMode){
            case "play":
                // If Human PLayer - draw with handler
                Solver solver = new Solver(this.board);
                cursor.show();

                while (true) {
                    this.gameView.run();
                    this.keyEvent = event.processKey();
                    cursor.processKeyEvent(this.keyEvent);
                    if (solver.isEnd(this.board)) {
                        this.cursor.hide();
                    }
                }
                //break; //todo check for the winning condition end
            case "solve":
                // If Computer Playing - draw like this
                for(int i=0; i< solution.size(); i++){
                    TimeUnit.SECONDS.sleep(1); //todo remove maybe?? testing purposes
                    this.gameView.setBoard(solution.get(i));
                    this.gameView.run();
                }

                break;
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

    public void getHint(){
        this.hintCounter++;
        this.board = solution.get(this.hintCounter);
        this.previousBoards.clear();
        this.gameView.setBoard(this.board);
    }

    public ArrayList<ArrayList<Integer>> getBoard(){
        return this.board;
    }

    public int getTileValue(int x, int y){
        return this.board.get(y).get(x);
    }
}

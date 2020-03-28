import java.util.Arrays;

public class Cursor {


    private int x;
    private int y;
    private int size;
    private  boolean cursorSelect;
    private  boolean hidden;
    private Puzzle puzzle;

    public Cursor(Puzzle puzzle) {
        this.x = 7;
        this.y = 4;
        this.size = puzzle.getBoard().size();
        this.cursorSelect = false;
        this.hidden = false;
        this.puzzle = puzzle;
    }

    public void processKeyEvent(Puzzle.keyEvent keyEvent) {
        if (keyEvent == Puzzle.keyEvent.MOVE_DOWN)
            moveDown();
        else if (keyEvent == Puzzle.keyEvent.MOVE_UP)
            moveUp();
        else if (keyEvent == Puzzle.keyEvent.MOVE_LEFT)
            moveLeft();
        else if (keyEvent == Puzzle.keyEvent.MOVE_RIGHT)
            moveRight();
        else if(keyEvent == Puzzle.keyEvent.HINT)
            getHint();
        else if(keyEvent == Puzzle.keyEvent.SELECT)
            showMoves();
        else if(keyEvent == Puzzle.keyEvent.UNDO)
            undoMove();
        else if (keyEvent == Puzzle.keyEvent.QUIT)
            System.exit(0);
    }

    public void hide(){
        this.hidden = true;
    }
    public void show(){
        this.hidden = false;
    }
    public boolean getHidden(){
        return this.hidden;
    }

    public void moveDown(){
        int tmp = this.y+3;
        if(this.cursorSelect) {
            this.puzzle.moveTileDown(this.getGameX(), this.getGameY());
            this.cursorSelect = false;
        }else  if (canMove(this.x,tmp))
            this.y = tmp;
    }

    public void moveUp(){
        int tmp = this.y-3;
        if(this.cursorSelect ) {
            this.puzzle.moveTileUp(this.getGameX(), this.getGameY());
            this.cursorSelect = false;
        }else if (canMove(this.x, tmp))
            this.y = tmp;
    }

    public void moveRight(){
        int tmp = this.x+4;
        if(this.cursorSelect){
            this.puzzle.moveTileRight(this.getGameX(),this.getGameY());
            this.cursorSelect = false;
        }else if(canMove(tmp, this.y))
            this.x = tmp;
    }

    public void moveLeft(){
        int tmp = this.x-4;
        if(this.cursorSelect){
            this.puzzle.moveTileLeft(this.getGameX(),this.getGameY());
            this.cursorSelect = false;
        }else if (canMove(tmp, this.y))
            this.x = tmp;
    }

    public void showMoves(){
        //set state to true
        if(this.cursorSelect || this.puzzle.getPossibleMoves(this.getGameX(),this.getGameY()).length == 0)
            this.cursorSelect = false;
        else this.cursorSelect = true;

    }

    public void undoMove(){
        this.puzzle.undoMove();
    }

    public void getHint(){
        this.puzzle.getHint();
    }
    //todo delete if not needed
    public int[] getGameCoords(){
        return new int[] {(this.getX() - 7) / 4, (this.getY() - 4) / 3};

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGameX(){
        return ((this.getX()-7)/4);
    }

    public int getGameY(){
        return ((this.getY()-4)/3);
    }


    public boolean canMove(int x, int y) {
        return (x >= 4 && y >=4 && x < size*4+4 && y < size*3+2);
    }

    public int[][] getPossibleMoves(){
        int[][] error = {{-1}};
        if(this.puzzle.getTileValue(this.getGameX(),this.getGameY()) > 0)
            return this.puzzle.getPossibleMoves(this.getGameX(),this.getGameY());
        else return error;
    }

    //todo delete if not needed
    public boolean getCursorSelectState() {
        return this.cursorSelect;
    }
}

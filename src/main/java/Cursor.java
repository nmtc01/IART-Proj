import sun.jvmstat.perfdata.monitor.MonitorVersionException;

public class Cursor {


    private int x;
    private int y;
    private int size;
    private  boolean cursorSelect;

    public Cursor(int size) {
        this.x = 7;
        this.y = 4;
        this.size = size;
        this.cursorSelect = false;
    }

    public void processKeyEvent(Puzzle.keyEvent keyEvent) {
        System.out.println(keyEvent);
        if (keyEvent == Puzzle.keyEvent.MOVE_DOWN)
            moveDown();
        else if (keyEvent == Puzzle.keyEvent.MOVE_UP)
            moveUp();
        else if (keyEvent == Puzzle.keyEvent.MOVE_LEFT)
            moveLeft();
        else if (keyEvent == Puzzle.keyEvent.MOVE_RIGHT)
            moveRight();
        else if(keyEvent == Puzzle.keyEvent.SELECT)
            showMoves();
        else if(keyEvent == Puzzle.keyEvent.UNDO)
            System.out.println("Undo (todo)");
        else if (keyEvent == Puzzle.keyEvent.STOP)
            System.exit(0);
    }

    public void moveDown(){
        int tmp = this.y+3;
        if (canMove(this.x,tmp))
            this.y = tmp;
    }

    public void moveUp(){
        int tmp = this.y-3;
        if (canMove(this.x, tmp))
            this.y = tmp;
    }

    public void moveRight(){
        int tmp = this.x+4;
        if (canMove(tmp, this.y))
            this.x = tmp;
    }

    public void moveLeft(){
        int tmp = this.x-4;
        if (canMove(tmp, this.y))
            this.x = tmp;
    }

    public void showMoves(){
        //set state to true
        if(this.cursorSelect)
            this.cursorSelect = false;
        else this.cursorSelect = true;
        //real coords
        System.out.println("Real coords: ("+this.getX()+ ","+this.getY()+")");
        //game coords
        int x =  (this.getX()-7)/4;
        int y = (this.getY()-4)/3;
        System.out.println("Real coords: ("+(this.getX()-7)/4+ ","+(this.getY()-4)/3+")"); //[(x,y) - (7,4)]/4
        //get board info
        //System.out.println("Value: " + Puzzle.getTileValue(x,y));
    }

    public int[] getGameCoords(){
        return new int[] {(this.getX() - 7) / 4, (this.getY() - 4) / 3};

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canMove(int x, int y) {
        return (x >= 4 && y >=4 && x < size*4+4 && y < size*3+2);
    }

    public boolean getCursorSelectState() {
        return this.cursorSelect;
    }
}

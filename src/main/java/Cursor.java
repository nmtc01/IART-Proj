public class Cursor {
    private int x;
    private int y;
    private int size;

    public Cursor(int size) {
        this.x = 7;
        this.y = 4;
        this.size = size;
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
        else if(keyEvent == Puzzle.keyEvent.SELECT){
            //real coords
            System.out.println("Real coords: ("+this.getX()+ ","+this.getY()+")");
            //game coords
            System.out.println("Real coords: ("+(this.getX()-7)/4+ ","+(this.getY()-4)/3+")"); //[(x,y) - (7,4)]/4
        }
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean canMove(int x, int y) {
        return (x >= 4 && y >=4 && x < size*4+4 && y < size*3+2);
    }
}

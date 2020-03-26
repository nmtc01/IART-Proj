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
        if (keyEvent == Puzzle.keyEvent.MOVE_DOWN)
            moveDown();
        else if (keyEvent == Puzzle.keyEvent.MOVE_UP)
            moveUp();
        else if (keyEvent == Puzzle.keyEvent.MOVE_LEFT)
            moveLeft();
        else if (keyEvent == Puzzle.keyEvent.MOVE_RIGHT)
            moveRight();
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

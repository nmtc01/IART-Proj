public class Cursor {
    private int x;
    private int y;

    public Cursor() {
        this.x = 7;
        this.y = 4;
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
        this.y++;
    }

    public void moveUp(){
        this.y--;
    }

    public void moveRight(){
        this.x++;
    }

    public void moveLeft(){
        this.x--;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

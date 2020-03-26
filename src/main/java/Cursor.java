public class Cursor {
    private int x;
    private int y;

    public Cursor() {
        this.x = 7;
        this.y = 4;
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
        this.y+=3;
    }

    public void moveUp(){
        this.y-=3;
    }

    public void moveRight(){
        this.x+=4;
    }

    public void moveLeft(){
        this.x-=4;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

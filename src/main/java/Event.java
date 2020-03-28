import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Event {
    private KeyStroke key;
    private Screen screen;
    Puzzle.keyEvent event;

    public Event(Screen screen, Puzzle.keyEvent event) {
        this.screen = screen;
        this.event = event;
    }

    public Puzzle.keyEvent processKey() throws IOException {
        key = screen.readInput();

        if (key.getKeyType() == KeyType.ArrowDown)
            this.event = Puzzle.keyEvent.MOVE_DOWN;
        else if (key.getKeyType() == KeyType.ArrowUp)
            this.event = Puzzle.keyEvent.MOVE_UP;
        else if (key.getKeyType() == KeyType.ArrowLeft)
            this.event = Puzzle.keyEvent.MOVE_LEFT;
        else if (key.getKeyType() == KeyType.ArrowRight)
            this.event = Puzzle.keyEvent.MOVE_RIGHT;
        else if (key.getKeyType() == KeyType.Enter){
            if(this.event != Puzzle.keyEvent.SELECT){
                this.event = Puzzle.keyEvent.SELECT;
            }
           // else this.event = Puzzle.keyEvent.UNSELECT;
        }  else if(key.getKeyType() == KeyType.Escape) {
            System.out.println(this.event);
            this.event = Puzzle.keyEvent.QUIT;
            System.out.println(this.event);
        }
        else if(key.getCharacter() == 'z' || key.getCharacter() == 'Z')
            this.event = Puzzle.keyEvent.UNDO;

        return this.event;
    }
}

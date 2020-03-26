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

    public void processKey() throws IOException {
        key = screen.readInput();

        if (key.getKeyType() == KeyType.ArrowDown) {
            this.event = Puzzle.keyEvent.MOVE_DOWN;
            System.out.println("ola");
        }
        if (key.getKeyType() == KeyType.ArrowUp)
            this.event = Puzzle.keyEvent.MOVE_UP;
        if (key.getKeyType() == KeyType.ArrowLeft)
            this.event = Puzzle.keyEvent.MOVE_LEFT;
        if (key.getKeyType() == KeyType.ArrowRight)
            this.event = Puzzle.keyEvent.MOVE_RIGHT;
    }
}

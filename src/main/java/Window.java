import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Window {
    Terminal terminal;
    Screen screen;

    public Window(int size) {
        //Initiate Screen
        try {
            DefaultTerminalFactory f = new DefaultTerminalFactory();
            f.setInitialTerminalSize(new TerminalSize(size*5,size*4));
            this.terminal = f.createTerminal();
            this.screen = new TerminalScreen(terminal);


            this.screen.setCursorPosition(null);   // we don't need a cursor
            this.screen.startScreen();             // screens must be started
            this.screen.doResizeIfNecessary();     // resize screen if necessary
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public Screen getScreen() {
        return screen;
    }
}

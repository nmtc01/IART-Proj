package menu.states;


import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class MainState implements MenuState {
    private Screen screen;
    private int overButton;
    public Mode mode;

    public MainState(Screen screen) {
        this.screen = screen;
        overButton = 0;
        this.mode = new Mode();
    }

    @Override
    public Mode getMode() {
        return mode;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        drawPlayButton(textGraphics);
        drawSolvePuzzleButton(textGraphics);
        drawQuitButton(textGraphics);
    }

    @Override
    public MenuState processKey() throws IOException {
        KeyStroke key;
        key = screen.readInput();

        if (key.getKeyType() == KeyType.EOF) {
            screen.close();
        }

        if (key.getKeyType() == KeyType.ArrowUp)
            moveUp();

        if (key.getKeyType() == KeyType.ArrowDown)
            moveDown();

        if (key.getKeyType() == KeyType.Enter){
            switch (overButton) {
                case 0:
                    ChooseLevelState play = new ChooseLevelState(screen);
                    play.mode.setMode("play");
                    return play;
                case 1:
                    ChooseAlgorithmState solve = new ChooseAlgorithmState(screen);
                    solve.mode.setMode("solve");
                    return new ChooseAlgorithmState(screen);
                case 2:
                    screen.close();
                    System.exit(0);
            }
        }
        return this;
    }

    private void drawPlayButton(TextGraphics textGraphics){
        if (overButton == 0){
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FBDE44"));
        }
        else textGraphics.setBackgroundColor(TextColor.Factory.fromString("#28334A"));

        textGraphics.fillRectangle(new TerminalPosition(15, 16), new TerminalSize(32, 4), ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(29, 18), "PLAY!");
    }

    private void drawSolvePuzzleButton(TextGraphics textGraphics){
        if (overButton == 1){
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FBDE44"));
        }
        else textGraphics.setBackgroundColor(TextColor.Factory.fromString("#28334A"));

        textGraphics.fillRectangle(new TerminalPosition(15, 22), new TerminalSize(32, 4), ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(25, 24), "SOLVE PUZZLE");
    }

    private void drawQuitButton(TextGraphics textGraphics){
        if (overButton == 2){
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FBDE44"));
        }
        else textGraphics.setBackgroundColor(TextColor.Factory.fromString("#28334A"));

        textGraphics.fillRectangle(new TerminalPosition(15, 28), new TerminalSize(32, 4), ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(29, 30), "QUIT");
    }

    private void moveUp() {
        overButton--;
        if (overButton < 0)
            overButton = 2;
    }

    private void moveDown() {
        overButton++;
        if (overButton > 2)
            overButton = 0;
    }
}

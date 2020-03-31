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

public class ChooseAlgorithmState implements MenuState {
    private final Screen screen;
    private int overButton;
    public Mode mode;

    public ChooseAlgorithmState(Screen screen) {
        this.screen = screen;
        overButton = 0;
        mode = new Mode();
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        drawAlgorithmButton(textGraphics, "BFS", 0);
        drawAlgorithmButton(textGraphics, "DFS", 1);
        drawAlgorithmButton(textGraphics, "Uniform Cost", 2);
        drawAlgorithmButton(textGraphics, "Greedy", 3);
        drawAlgorithmButton(textGraphics, "A*", 4);

    }

    @Override
    public MenuState processKey() throws IOException {
        KeyStroke key;
        key = screen.readInput();

        if (key.getKeyType() == KeyType.EOF) {
            screen.close();
            System.exit(0);
        }

        if (key.getKeyType() == KeyType.Backspace) {
            return new MainState(screen);
        }

        if (key.getKeyType() == KeyType.ArrowUp)
            moveUp();

        if (key.getKeyType() == KeyType.ArrowDown)
            moveDown();

        if (key.getKeyType() == KeyType.Enter){
            ChooseLevelState level = new ChooseLevelState(screen);
            switch (overButton) {
                case 0:
                    level.mode.setAlgorithm("BFS");
                    break;
                case 1:
                    level.mode.setAlgorithm("DFS");
                    break;
                case 2:
                    level.mode.setAlgorithm("UniformCost");
                    break;
                case 3:
                    level.mode.setAlgorithm("Greedy");
                    break;
                case 4:
                    level.mode.setAlgorithm("A*");
                    break;
            }
            level.mode.setMode("solve");
            return level;
        }
        return this;
    }

    private void drawAlgorithmButton(TextGraphics textGraphics, String algorithm, int order){
        if (overButton == order){
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FBDE44"));
        }
        else textGraphics.setBackgroundColor(TextColor.Factory.fromString("#28334A"));

        textGraphics.fillRectangle(new TerminalPosition(18, 14 + order * 4), new TerminalSize(20, 4), ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(20, 16 + order * 4), algorithm);
    }

    private void moveUp() {
        overButton--;
        if (overButton < 0)
            overButton = 4;
    }

    private void moveDown() {
        overButton++;
        if (overButton > 4)
            overButton = 0;
    }
}

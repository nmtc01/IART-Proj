package menu.states;

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
            return new ChooseLevelState(screen);
        }
        return this;
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

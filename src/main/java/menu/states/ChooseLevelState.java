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

@SuppressWarnings("Duplicates")
public class ChooseLevelState implements MenuState {
    private Screen screen;
    private int overButton;
    public Mode mode;

    public ChooseLevelState(Screen screen){
        this.screen = screen;
        this.overButton = 0;
        mode = new Mode();
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        drawLevelButton(textGraphics, "lvl1");
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
            switch (overButton) {
                case 0:
                    mode.setLevel("lvl1");
                    break;
                case 1:
                    mode.setLevel("lvl2");
                    break;
                case 2:
                    mode.setLevel("lvl3");
                    break;
                case 3:
                    mode.setLevel("lvl4");
                    break;
                case 4:
                    mode.setLevel("lvl5");
                    break;
                case 5:
                    mode.setLevel("lvl6");
                    break;
                case 6:
                    mode.setLevel("lvl7");
                    break;
                case 7:
                    mode.setLevel("lvl8");
                    break;
                case 8:
                    mode.setLevel("lvl9");
                    break;
                case 9:
                    mode.setLevel("lvl10");
                    break;
                case 10:
                    mode.setLevel("lvl11");
                    break;
                case 11:
                    mode.setLevel("lvl12");
                    break;
                case 12:
                    mode.setLevel("lvl13");
                    break;
                case 13:
                    mode.setLevel("lvl14");
                    break;
                case 14:
                    mode.setLevel("lvl15");
                    break;
                case 15:
                    mode.setLevel("lvl16");
                    break;
            }
        }
        return this;
    }

    private void drawLevelButton(TextGraphics textGraphics, String level){
        if (overButton == 0){
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#00FF00"));
        }
        else textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));

        textGraphics.fillRectangle(new TerminalPosition(16, 8), new TerminalSize(32, 4), ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(28, 10), level);
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

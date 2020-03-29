package menu.states;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class ChooseLevelState implements MenuState {
    private Screen screen;
    private int overButton;
    public Mode mode;
    int numberOfLevels;

    public ChooseLevelState(Screen screen){
        this.screen = screen;
        this.overButton = 0;
        mode = new Mode();


        String root = System.getProperty("user.dir");
        String filepathWin = "\\src\\main\\resources\\puzzle"; // in case of Windows: "\\path \\to\\yourfile.txt
        String filepathUnix = "/src/main/resources/puzzle";
        String path = root+filepathWin;

        File folder = new File(path);

        if (folder == null) {
            path = root + filepathUnix;
            folder = new File(path);
        }

        File[] listOfFiles = folder.listFiles();
        numberOfLevels = listOfFiles.length;
    }

    public Mode getMode() {
        return mode;
    }

    @Override
    public void draw(TextGraphics textGraphics) {
        for (int i = 1; i <= numberOfLevels; i++) {
            drawLevelButton(textGraphics, "lvl" + i, i - 1);
        }
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

        if (key.getKeyType() == KeyType.ArrowLeft)
            moveLeft();

        if (key.getKeyType() == KeyType.ArrowRight)
            moveRight();

        if (key.getKeyType() == KeyType.Enter){
            mode.setLevel("lvl"+(overButton+1));
        }
        return this;
    }

    private void drawLevelButton(TextGraphics textGraphics, String level, int order){
        if (overButton == order){
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#FBDE44"));
        }
        else textGraphics.setBackgroundColor(TextColor.Factory.fromString("#28334A"));

        /*
        * Constants:
        * 10 levels per column
        * 50 window width
        * */

        int columnsOfLevels = numberOfLevels / 10;
        if (numberOfLevels % 10 > 0)
            columnsOfLevels++;
        int columnFactor = (int)((float) 50 / columnsOfLevels);
        int column = order / 10;

        textGraphics.fillRectangle(new TerminalPosition(column * columnFactor + 5, 8 + order % 10 * 3), new TerminalSize(8, 2), ' ');
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(column * columnFactor + 7, 9 + order % 10 * 3), level);
    }


    private void moveUp() {
        overButton--;
        if (overButton < 0)
            overButton = numberOfLevels - 1;
    }

    private void moveDown() {
        overButton++;
        if (overButton >= numberOfLevels)
            overButton = 0;
    }

    private void moveLeft() {
        overButton -= 10;
        if (overButton < 0)
            overButton = numberOfLevels - 1;
    }

    private void moveRight() {
        overButton += 10;
        if (overButton >= numberOfLevels)
            overButton = 0;
    }

}

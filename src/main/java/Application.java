import menu.Menu;
import menu.states.Mode;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        Window window = new Window(12);
        Menu menu = new Menu(window.getScreen());
        Mode mode = new Mode();

        while (mode.getLevel() == "") {
            try {
                mode = menu.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String gameMode = mode.getMode();
        String level = mode.getLevel();
        String algorithm = mode.getAlgorithm();

        Puzzle puzzle = new Puzzle(level,gameMode,algorithm,window);

        try {
            puzzle.run();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
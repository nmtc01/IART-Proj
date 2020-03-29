import menu.Menu;
import menu.states.Mode;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        //Get puzzle
        System.out.print("Puzzle name: ");
        //Scanner input = new Scanner(System.in);
        //String file_name = input.nextLine();

        Window window = new Window(10);
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

        Puzzle puzzle = new Puzzle(level);

        try {
            puzzle.run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
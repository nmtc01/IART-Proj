import menu.Menu;
import menu.states.Mode;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
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
        */

        Puzzle puzzle1 = new Puzzle("lvl1","solver","BFS");
        Puzzle puzzle2 = new Puzzle("lvl2","solver","BFS");
        Puzzle puzzle3 = new Puzzle("lvl3","solver","BFS");
        Puzzle puzzle4 = new Puzzle("lvl4","solver","BFS");
        Puzzle puzzle5 = new Puzzle("lvl5","solver","BFS");
        Puzzle puzzle6 = new Puzzle("lvl6","solver","BFS");
        Puzzle puzzle7 = new Puzzle("lvl7","solver","BFS");
        Puzzle puzzle8 = new Puzzle("lvl8","solver","BFS");
        Puzzle puzzle9 = new Puzzle("lvl9","solver","BFS");
        Puzzle puzzle10 = new Puzzle("lvl10","solver","BFS");
        Puzzle puzzle11 = new Puzzle("lvl11","solver","BFS");
        Puzzle puzzle12 = new Puzzle("lvl12","solver","BFS");
        Puzzle puzzle13 = new Puzzle("lvl13","solver","BFS");
        Puzzle puzzle14 = new Puzzle("lvl14","solver","BFS");
        Puzzle puzzle15 = new Puzzle("lvl15","solver","BFS");
        Puzzle puzzle17 = new Puzzle("lvl17","solver","BFS");


        puzzle1.run();
        System.out.println("lvl 1 ---------------------------------------------------------------");
        puzzle2.run();
        System.out.println("lvl 2 ---------------------------------------------------------------");
        puzzle3.run();
        System.out.println("lvl 3 ---------------------------------------------------------------");
        puzzle4.run();
        System.out.println("lvl 4 ---------------------------------------------------------------");
        puzzle5.run();
        System.out.println("lvl 5 ---------------------------------------------------------------");
        puzzle6.run();
        System.out.println("lvl 6 ---------------------------------------------------------------");
        puzzle7.run();
        System.out.println("lvl 7 ---------------------------------------------------------------");
        puzzle8.run();
        System.out.println("lvl 8 ---------------------------------------------------------------");
        puzzle9.run();
        System.out.println("lvl 9 ---------------------------------------------------------------");
        puzzle10.run();
        System.out.println("lvl 10 ---------------------------------------------------------------");
        puzzle11.run();
        System.out.println("lvl 11 ---------------------------------------------------------------");
        puzzle12.run();
        System.out.println("lvl 12 ---------------------------------------------------------------");
        puzzle13.run();
        System.out.println("lvl 13 ---------------------------------------------------------------");
        puzzle14.run();
        System.out.println("lvl 14 ---------------------------------------------------------------");
        puzzle15.run();
        System.out.println("lvl 15 ---------------------------------------------------------------");
        puzzle17.run();
        System.out.println("lvl 17 ---------------------------------------------------------------");


    }
}
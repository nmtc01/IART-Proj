import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        //Get puzzle
        System.out.print("Puzzle name: ");
        Scanner input = new Scanner(System.in);
        String file_name = input.nextLine();
        Puzzle puzzle = new Puzzle(file_name);

        try {
            puzzle.run();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
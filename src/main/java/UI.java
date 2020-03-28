import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public UI(){
        this.start();
    }

    public void start(){
        System.out.println(" ------------- Zhed  ------------- ");
    }

    public Integer get_option(){
        System.out.println("Choose if you want to play or to get a puzzle's solution:");
        System.out.println("1 - Play!");
        System.out.println("2 - Get solution.");
        System.out.println("3 - Exit");
        Scanner option = new Scanner(System.in);

        return option.nextInt();
    }

    public String get_file_name(){
        System.out.print("Puzzle name: ");
        Scanner input = new Scanner(System.in);
        String file_name = input.nextLine();
        return file_name;
    }

    public void clear_console(){
        System.out.print("\033[H\033[2J");
    }

    public void draw_puzzle(ArrayList<ArrayList<Integer>> matrix){
        System.out.print('\n');
        int mat_size = matrix.get(0).size();
        System.out.print(' ');
        System.out.print(' ');
        for(int top = 0;top<mat_size; top++){

            System.out.print(' ');
            System.out.print(' ');
            System.out.print(' ');
            System.out.print(top);
            System.out.print(' ');
            System.out.print(' ');
        }
        System.out.print("\n");

        //draw first line on the console
        System.out.print(' ');
        System.out.print(' ');
        System.out.print('╔');
        for(int top = 0;top<matrix.get(0).size(); top++){

            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            if(top < mat_size -1)
             System.out.print('╦');
            else
                System.out.print('╗');

        }
        System.out.print("\n");

        //draw other lines
       for(int line=0;line<matrix.size(); line++){
           System.out.print(line);
           System.out.print(' ');
           for(int mid = 0;mid<matrix.get(line).size(); mid++){
               System.out.print('║');

               if(matrix.get(line).get(mid) == 0){
                   System.out.print(' ');
                   System.out.print(' ');
                   System.out.print(' ');
                   System.out.print(' ');
                   System.out.print(' ');
               }else if(matrix.get(line).get(mid) == -1){
                   System.out.print('▒');
                   System.out.print('▒');
                   System.out.print('▒');
                   System.out.print('▒');
                   System.out.print('▒');
               } else if(matrix.get(line).get(mid) == -2) {
                   System.out.print(' ');
                   System.out.print(' ');
                   System.out.print('G');
                   System.out.print(' ');
                   System.out.print(' ');
               } else  {
                   System.out.print('░');
                   System.out.print('░');
                   System.out.print(matrix.get(line).get(mid));
                   System.out.print('░');
                   System.out.print('░');
               }
               if(mid == mat_size -1 )
                   System.out.print('║');
           }
           System.out.print("\n");
           if(line < mat_size -1) {
               for (int top = 0; top < matrix.get(line).size(); top++) {
                   if (top == 0){
                       System.out.print(' ');
                       System.out.print(' ');
                       System.out.print('╠');
                   }
                   System.out.print('═');
                   System.out.print('═');
                   System.out.print('═');
                   System.out.print('═');
                   System.out.print('═');
                   if (top < mat_size - 1)
                       System.out.print('╬');
                   else System.out.print('╣');
               }
               System.out.print("\n");
           }
       }

       //draw last line on the console
        System.out.print(' ');
        System.out.print(' ');
        System.out.print('╚');
        for(int bot = 0;bot<matrix.get(0).size(); bot++){
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            System.out.print('═');
            if(bot < mat_size -1)
                System.out.print('╩');
            else System.out.print('╝');
        }
        System.out.print("\n");
    }

    public Point get_piece_to_move(){
        System.out.println("Insert the coordinates of the piece you want to move: ");
        Integer line, column;
        Scanner input = new Scanner(System.in);

        System.out.print("Line: ");
        line = input.nextInt();

        System.out.print("Column: ");
        column = input.nextInt();

        Point point = new Point(line, column);

        return point;
    }

    public Character get_move_direction() {
        Character direction;
        while(true) {
            System.out.println("U - Up");
            System.out.println("D - Down");
            System.out.println("L - Left");
            System.out.println("R - Right");
            System.out.println("Insert the direction (example R): ");
            Scanner input = new Scanner(System.in);
            direction = input.nextLine().charAt(0);
            if (direction.equals('U') || direction.equals('D') || direction.equals('L') || direction.equals('R') ||
                direction.equals('u') || direction.equals('d') || direction.equals('l') || direction.equals('r') )
                break;
        }

        return direction;
    }
}

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;

public class GameView {
    private Screen screen;
    private ArrayList<ArrayList<Integer>> board;

    public GameView(Screen screen, ArrayList<ArrayList<Integer>> matrix) {
        this.screen = screen;
        this.board = matrix;
    }

    private void draw_board(TextGraphics textGraphics, ArrayList<ArrayList<Integer>> board) {
        //Rectangle
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#EEEEEE"));
        textGraphics.fillRectangle(new TerminalPosition(5, 3), new TerminalSize(board.size()*4+1, board.size()*3+1), ' ');

        //Line of numbers
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#EEEEEE"));

        textGraphics.putString(new TerminalPosition(5, 0), "╔");
        textGraphics.putString(new TerminalPosition(5, 1), "║");
        textGraphics.putString(new TerminalPosition(5, 2), "║");
        for (int i = 0; i < board.size(); i++) {
            textGraphics.putString(new TerminalPosition(i*4 + 6, 0), "═");
            textGraphics.putString(new TerminalPosition(i*4 + 6, 1), " ");
            textGraphics.putString(new TerminalPosition(i*4 + 6, 2), " ");
            textGraphics.putString(new TerminalPosition(i*4 + 7, 0), "═");
            textGraphics.putString(new TerminalPosition(i*4 + 7, 1), Integer.toString(i));
            textGraphics.putString(new TerminalPosition(i*4 + 7, 2), " ");
            textGraphics.putString(new TerminalPosition(i*4 + 8, 0), "═");
            textGraphics.putString(new TerminalPosition(i*4 + 8, 1), " ");
            textGraphics.putString(new TerminalPosition(i*4 + 8, 2), " ");
            if (i == board.size()-1)
                textGraphics.putString(new TerminalPosition(i*4 + 9, 0), "╗");
            else textGraphics.putString(new TerminalPosition(i*4 + 9, 0), "╦");
            textGraphics.putString(new TerminalPosition(i*4 + 9, 1), "║");
            textGraphics.putString(new TerminalPosition(i*4 + 9, 2), "║");
        }

        //Lines
        textGraphics.putString(new TerminalPosition(0, 3), "╔");
        textGraphics.putString(new TerminalPosition(0, 4), "║");
        textGraphics.putString(new TerminalPosition(0, 5), "║");
        textGraphics.putString(new TerminalPosition(1, 3), "═");
        textGraphics.putString(new TerminalPosition(2, 3), "═");
        textGraphics.putString(new TerminalPosition(3, 3), "═");
        textGraphics.putString(new TerminalPosition(4, 3), "═");
        for (int i = 0; i < board.size()-1; i++) {
            textGraphics.putString(new TerminalPosition(0, i*3+6), "╠");
            textGraphics.putString(new TerminalPosition(0, i*3+7), "║");
            textGraphics.putString(new TerminalPosition(0, i*3+8), "║");
            textGraphics.putString(new TerminalPosition(1, i*3+6), "═");
            textGraphics.putString(new TerminalPosition(2, i*3+6), "═");
            textGraphics.putString(new TerminalPosition(3, i*3+6), "═");
            textGraphics.putString(new TerminalPosition(4, i*3+6), "═");
            if (i == board.size()-2) {
                textGraphics.putString(new TerminalPosition(0, i * 3 + 9), "╚");
                textGraphics.putString(new TerminalPosition(1, i*3+9), "═");
                textGraphics.putString(new TerminalPosition(2, i*3+9), "═");
                textGraphics.putString(new TerminalPosition(3, i*3+9), "═");
                textGraphics.putString(new TerminalPosition(4, i*3+9), "═");
            }
        }

        for (int i = 1; i < board.size()+1; i++) {
            for (int j = 1; j < board.size()+2; j++) {
                textGraphics.putString(new TerminalPosition(j*4-2, i*3), "═");
                textGraphics.putString(new TerminalPosition(j*4-1, i*3), "═");
                textGraphics.putString(new TerminalPosition(j*4, i*3), "═");
                textGraphics.putString(new TerminalPosition(j*4+1, i*3), "╬");
                textGraphics.putString(new TerminalPosition(j*4+1, i*3+1), "║");
                textGraphics.putString(new TerminalPosition(j*4+1, i*3+2), "║");
            }
        }

        for (int j = 0; j < board.size(); j++) {
            textGraphics.putString(new TerminalPosition(1, j*3+4), " ");
            textGraphics.putString(new TerminalPosition(2, j*3+4), Integer.toString(j));
            textGraphics.putString(new TerminalPosition(3, j*3+4), " ");
            textGraphics.putString(new TerminalPosition(4, j*3+4), " ");
            textGraphics.putString(new TerminalPosition(1, j*3+5), " ");
            textGraphics.putString(new TerminalPosition(2, j*3+5), " ");
            textGraphics.putString(new TerminalPosition(3, j*3+5), " ");
            textGraphics.putString(new TerminalPosition(4, j*3+5), " ");
            textGraphics.putString(new TerminalPosition(j*4+6, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+6, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+6, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+7, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+7, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+7, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+8, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+8, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+8, board.size()*3+3), "═");
            textGraphics.putString(new TerminalPosition(j*4+5, board.size()*3+3), "╩");
            for (int i = 0; i < board.size(); i++) {
                Integer element = this.board.get(i).get(j);
                switch (element) {
                    case 0:{
                        break;
                    }
                    case -2:{
                        textGraphics.putString(new TerminalPosition(j * 4 + 6, i * 3 + 4), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 7, i * 3 + 4), "G");
                        textGraphics.putString(new TerminalPosition(j * 4 + 8, i * 3 + 4), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 6, i * 3 + 5), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 7, i * 3 + 5), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 8, i * 3 + 5), " ");
                        break;
                    }
                    default:{
                        textGraphics.putString(new TerminalPosition(j * 4 + 6, i * 3 + 4), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 7, i * 3 + 4), Integer.toString(element));
                        textGraphics.putString(new TerminalPosition(j * 4 + 8, i * 3 + 4), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 6, i * 3 + 5), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 7, i * 3 + 5), " ");
                        textGraphics.putString(new TerminalPosition(j * 4 + 8, i * 3 + 5), " ");
                        break;
                    }
                }
            }
        }
        textGraphics.putString(new TerminalPosition(board.size()*4+5, board.size()*3+3), "╝");
    }

    private void draw() throws IOException {
        this.screen.clear();
        TextGraphics textGraphics = this.screen.newTextGraphics();
        draw_board(textGraphics, board);
        this.screen.refresh();
    }

    public void run() throws IOException{
        draw();
    }
}

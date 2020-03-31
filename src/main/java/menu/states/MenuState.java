package menu.states;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;

public interface MenuState {
    void draw(TextGraphics textGraphics);
    MenuState processKey() throws IOException;
    Mode getMode();
}

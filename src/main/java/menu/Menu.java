package menu;

import menu.states.MainState;
import menu.states.MenuState;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import menu.states.Mode;

import java.io.IOException;

public class Menu {
    Screen screen;
    MenuState menuState;

    public Menu(Screen screen){
        this.screen = screen;
        this.menuState =  new MainState(screen);
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public void setMenuState(MenuState menuState) {
        this.menuState = menuState;
    }

    public Mode run() throws IOException {
        screen.clear();
        TextGraphics textGraphics = screen.newTextGraphics();
        menuState.draw(textGraphics);
        screen.refresh();

        menuState = menuState.processKey();

        return menuState.getMode();
    }
}

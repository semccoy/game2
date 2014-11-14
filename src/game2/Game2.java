package game2;

import javalib.funworld.*;
import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

// move screen with player? (return screen +/- char location on a bigger world screen) 
public class Game2 extends World implements Constants {

    public static WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);

    public WorldImage buildWorld() {
        return new OverlayImages(universe, showScore());
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "your score here", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), "wow such score", 20, Color.white));
    }

    public Game2(WorldImage uni) {
        super();
        this.universe = uni;
    }

    public WorldImage makeImage() {
        return buildWorld();
    }

    public World onTick() {
        buildWorld();
        return new Game2(universe);
    }

    public World onKeyEvent(String key) {
        return new Game2(universe);
    }


    public static void main(String[] args) throws Exception {
        WorldBuilder game = new WorldBuilder(new Player(playerStart, 40, 40, Color.red), new Lobby(Lobby.lobby));
        game.bigBang(WIDTH, HEIGHT, .1);
    }
}

package game2;

import javalib.funworld.*;
import javalib.worldimages.*;
import java.awt.*;

public class Game2 extends World implements Constants {

    public static WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);

    public Game2(WorldImage uni) {
        super();
        this.universe = uni;
    }

    public WorldImage makeImage() {
        return universe;
    }

    public World onKeyEvent(String key) {
        return new Game2(universe);
    }

    public static void main(String[] args) throws Exception {
        Lobby game = new Lobby(new Game2(universe),
                new Player(playerStart, 40, 40, playerColor),
                new Bill(new Posn(1320, 120), 20, 0, 0, Color.yellow),
                new Bill(new Posn(1320, 120), 20, 0, 0, Color.yellow),
                new Bill(new Posn(1320, 120), 20, 0, 0, Color.yellow));
        
        game.bigBang(WIDTH, HEIGHT, .1);
    }

}

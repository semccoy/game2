package game2;

import javalib.funworld.*;
import javalib.worldimages.*;
import java.awt.*;

public class Game2 extends World implements Constants {

    static Color playerColor = Color.red;
    
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
        WorldBuilder game = new WorldBuilder(new Player(playerStart, 40, 40, playerColor), new Lobby(Lobby.lobby));
        game.bigBang(WIDTH, HEIGHT, .1);
    }

}

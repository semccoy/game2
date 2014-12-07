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
        BillGame game = new BillGame(new Game2(universe),
                new Player(playerStart, 40, 40, playerColor),
                new Bill(bill1Start, billRadius, 0, 0, Color.yellow),
                new Bill(bill2Start, billRadius, 0, 0, Color.yellow),
                new Bill(bill3Start, billRadius, 0, 0, Color.yellow));
        
        game.bigBang(WIDTH, HEIGHT, .05);
    }

}

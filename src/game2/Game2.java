package game2;

import static game2.Utilities.*;
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
        PauseWorld.accessHighscores();
        int rand = randomInt(0, 2);
        PlayWorld game = new PlayWorld(new Game2(universe),
                new Player(playerStart, 40, 40, "normal", playerStartColor),
                new Dot(dot1Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                new Dot(dot2Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                new Dot(dot3Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                new Powerup(powerupStart, 20, 0, 0, randomInt(1, 4), powerupTypes[rand], powerupColors[rand]));
        game.bigBang(WIDTH, HEIGHT, .01);
    }
}

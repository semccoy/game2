package game2;

import javalib.funworld.*;
import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

// move screen with player? (return screen +/- char location on a bigger world screen) 
public class Game2 extends World implements Constants {

    public static WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);

    public WorldImage buildWorld() {
        WorldImage newscene = universe;

        return new OverlayImages(newscene, showScore());
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

    public WorldEnd worldEnds() {
        String winText;
        if (thisIsTheEnd) {
            if (movements <= minMovements) {
                winText = "Great job! That only took you " + movements + " moves!";
            } else if (movements <= (minMovements + 10)) {
                winText = "Not bad! That was " + movements + " moves.";
            } else {
                winText = "What are you doing?? What took you " + movements + " moves?";
            }
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(WIDTH / 2, HEIGHT / 2), winText, 40, Color.white)));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    public static void main(String[] args) throws Exception {
        Lobby game = new Lobby(Lobby.lobby);
        game.bigBang(WIDTH, HEIGHT, .1);
    }
}

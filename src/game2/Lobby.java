package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Lobby extends World implements Constants {

    public static WorldImage lobby = new RectangleImage(base, WIDTH, HEIGHT, Color.gray);

    public WorldImage buildWorld() {
        WorldImage newscene = lobby;
        return new OverlayImages(newscene, showScore());
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "your score here", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), "wow such score", 20, Color.white));
    }

    public Lobby(WorldImage lob) {
        super();
        this.lobby = lob;
    }

    public WorldImage makeImage() {
        return buildWorld();
    }

//    public World onTick() {
//        buildWorld();
//        return new Game2(lobby);
//    }
//
//    public World onKeyEvent(String key) {
//        return new Game2(lobby);
//    }
}

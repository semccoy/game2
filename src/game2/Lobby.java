package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Lobby extends World implements Constants {

    
    Portal portal1 = new Portal(new Posn(410, Utilities.randomInt(240, HEIGHT - 160)), 40, Utilities.randomColor());
    Portal portal2 = new Portal(new Posn(1030, Utilities.randomInt(240, HEIGHT - 160)), 40, Utilities.randomColor());
    public static WorldImage lobby = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);

    public Lobby(WorldImage lob) {
        super();
        this.lobby = lob;
    }

    public WorldImage buildWorld() {
        return new OverlayImages(universe,
                new OverlayImages(lobby,
                        new OverlayImages(portal1.placePortal(),
                                new OverlayImages(portal2.placePortal(), showScore()))));
    }

    public WorldImage makeImage() {
        return buildWorld();
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "your score here", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), "wow such score", 20, Color.white));
    }

}

package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Lobby extends World implements Constants {
    
    TextImage beratement;

    Posn p1 = new Posn(410, Utilities.randomInt(240, HEIGHT - 160));
    Posn p2 = new Posn(1030, Utilities.randomInt(240, HEIGHT - 160));

    public static WorldImage lobby = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);

    public Lobby(WorldImage lob) {
        super();
        this.lobby = lob;
    }

    public WorldImage buildWorld() {
        Portal portal1 = new Portal(p1, 40, Utilities.randomColor());
        Portal portal2 = new Portal(p2, 40, Utilities.randomColor());

        return new OverlayImages(universe,
                new OverlayImages(lobby,
                        new OverlayImages(portal1.placePortal(),
                                new OverlayImages(portal2.placePortal(), showScore()))));
    }

    public WorldImage makeImage() {
        return buildWorld();
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "wow such score", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65),"" + Player.movements, 20, Color.white));
    }

}

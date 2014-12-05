package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Lobby extends World implements Constants {


    public static WorldImage lobby = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);

    // add onTick to get new portal colors so they're no longer boring
    
    
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
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), "" + Player.movements, 20, Color.white));
    }

}

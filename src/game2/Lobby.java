package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Lobby extends World implements Constants {
// move screen with player? (return screen +/- char location on a bigger world screen) 

    Player player;
    World world;

    // this is just a square off the playing screen that we build our trail on top of
    // - i get issues when i don't use this (for example, since the trail doesn't
    //      have to be > 0 units long at all times, we get NPEs)
    // - if there's a better way to build this arraylist of images please let me know!
    WorldImage newTrail = new RectangleImage(new Posn(-100, -100), 40, 40, Color.black);

    public Lobby(World world, Player player) {
        super();
        this.player = player;
        this.world = world;
    }

    public WorldImage makeTrail() {
        for (int i = 0; i < trail.size(); i++) {
            int tailSize = 10 + i * maxTrailSize;
            RectangleImage temp = new RectangleImage(trail.get(i), tailSize, tailSize, Color.green);
            newTrail = new OverlayImages(newTrail, temp);
        }
        return newTrail;
    }

    public WorldImage buildWorld() {
        return new OverlayImages(universe, lobby);

        // collision stuff
//        return new OverlayImages(universe,
//                new OverlayImages(lobby,
//                        new OverlayImages(bill1.placeBill(),
//                                new OverlayImages(bill2.placeBill(), showScore()))));
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.world.makeImage(),
                new OverlayImages(showScore(),
                        new OverlayImages(lobby,
                                new OverlayImages(makeTrail(), this.player.playerImage()))));
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), score.print(), 20, Color.white));
    }

    public World onKeyEvent(String key) {
        return new Lobby(this.world, this.player.movePlayer(key));
    }

    public World onTick() {
        return new Lobby(this.world, this.player);
    }

}

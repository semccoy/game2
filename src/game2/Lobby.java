package game2;

import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Lobby extends World implements Constants {
// move screen with player? (return screen +/- char location on a bigger world screen) 

    Player player;
    World world;

    public Lobby(World world, Player player) {
        super();
        this.player = player;
        this.world = world;
    }

    public WorldImage buildWorld() {
        return new OverlayImages(universe, lobby);
        
        // collision stuff
//        return new OverlayImages(universe,
//                new OverlayImages(lobby,
//                        new OverlayImages(bill1.placeBill(),
//                                new OverlayImages(bill2.placeBill(), showScore()))));

    }

    
    WorldImage newTrail = new RectangleImage(new Posn(0,0), 40, 40, Color.black);
    public WorldImage makeImage() {
        for (int i = 0; i < trail.size(); i++) {
            RectangleImage temp = new RectangleImage(trail.get(i), 40, 40, Color.green);
            newTrail = new OverlayImages(newTrail, temp);
        }
        
        return new OverlayImages(this.world.makeImage(),
                new OverlayImages(showScore(),
                new OverlayImages(lobby,
                        new OverlayImages(newTrail, this.player.playerImage()))));
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

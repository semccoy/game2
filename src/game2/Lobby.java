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
        return new OverlayImages(universe,
                new OverlayImages(lobby,
                        new OverlayImages(bill1.placeBill(),
                                new OverlayImages(bill2.placeBill(), showScore()))));
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.world.makeImage(),
                new OverlayImages(lobby,
                        new OverlayImages(this.player.playerImage(), showScore())));
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), "" + Player.movements, 20, Color.white));
    }

    public World onKeyEvent(String key) {
        return new Lobby(this.world, this.player.movePlayer(key));
    }

    public World onTick() {
        return new Lobby(this.world, this.player);
    }
}

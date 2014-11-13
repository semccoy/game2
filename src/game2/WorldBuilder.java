package game2;

import javalib.funworld.*;
import javalib.worldimages.*;

public class WorldBuilder extends World implements Constants {

    Player player;
    World world;

    public WorldBuilder(Player player, World world) {
        super();
        this.player = player;
        this.world = world;
    }

    public World onKeyEvent(String key) {
        return new WorldBuilder(this.player.movePlayer(key), this.world);
    }

    public World onTick() {
        return new WorldBuilder(this.player, this.world);
    }

    public WorldImage makeImage() {
//        if (inLobbyHuh) {
        return new OverlayImages(this.world.makeImage(), this.player.playerImage());
//        } else if (inGameOneHuh) {
//            return new OverlayImages(universe, player.playerImage());
//        } else if (inGameTwoHuh) {
//            return new OverlayImages(universe, player.playerImage());
//        } else {
//            return new OverlayImages(this.world.makeImage(), player.playerImage());
//        }

    }

    // other functions, worldEnds etc
}

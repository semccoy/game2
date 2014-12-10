package game2;

import static game2.Utilities.*;
import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class BillGame extends World implements Constants {

    Player player;
    World world;
    Bill bill1, bill2, bill3;

    // this is just a square off the playing screen that we build our trail on top of
    // - i get issues when i don't use this (for example, since the trail doesn't
    //      have to be > 0 units long at all times, we get NPEs)
    // - if there's a better way to build this arraylist of images please let me know!
    WorldImage newTrail = new RectangleImage(new Posn(-100, -100), 40, 40, Color.black);

    public BillGame(World world, Player player, Bill bill1, Bill bill2, Bill bill3) {
        super();
        this.player = player;
        this.world = world;
        this.bill1 = bill1;
        this.bill2 = bill2;
        this.bill3 = bill3;
    }

    public WorldImage makeTrail() {
        int tailSize;
        for (int i = 0; i < trail.size(); i++) {
            tailSize = 5 + i * 30 / maxTrailSize;
            RectangleImage temp = new RectangleImage(trail.get(i), tailSize, tailSize,
                    new Color(255, 200 - i * 100 / maxTrailSize, 200 - i * 100 / maxTrailSize));
            newTrail = new OverlayImages(newTrail, temp);
        }
        return newTrail;
    }

    public WorldImage buildWorld() {
        return new OverlayImages(universe, background);
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.world.makeImage(),
                new OverlayImages(showScore(),
                        new OverlayImages(showStats(),
                                new OverlayImages(background,
                                        new OverlayImages(makeTrail(),
                                                new OverlayImages(bill1.billImage(),
                                                        new OverlayImages(bill2.billImage(),
                                                                new OverlayImages(bill3.billImage(),
                                                                        this.player.playerImage()))))))));
    }

    public WorldImage showScore() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, Color.white),
                new TextImage(new Posn(WIDTH / 2, 65), score.print(), 20, Color.white));
    }

    public WorldImage showStats() {
        return new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 5, 25), "bills spawned: ", 15, Color.white),
                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 100, 25), billSpawns.print(), 15, Color.white),
                        new OverlayImages(new TextImage(new Posn(WIDTH / 5, 45), "times whacked: ", 15, Color.white),
                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 100, 45), whacks.print(), 15, Color.white),
                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 15, 65), "worlds fallen off of: ", 15, Color.white),
                                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 100, 65), falls.print(), 15, Color.white),
                                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 1, 85), "resets resetted: ", 15, Color.white),
                                                                new TextImage(new Posn(WIDTH / 5 + 100, 85), resets.print(), 15, Color.white))))))));
    }

    public World onKeyEvent(String key) {
        return new BillGame(this.world, this.player.movePlayer(key), this.bill1, this.bill2, this.bill3);
    }

    public World onTick() {
        // if you want to make it harder, increase speed as you get better
//        if (score.score <= 50) {
//            speedo = 4;
//        } else if (score.score > 50) {
//            speedo = 6;
//        } else if (score.score > 100) {
//            speedo = 8;
//        }
        // if score > some value, maybe 150, show an option to go to the other game
        // maybe have to hit "g" or something to go to game2
        // if score is too low you lose because you're bad at this
        // if whacks is too high " "
        
        if (bill1.hitPlayer(player) || bill2.hitPlayer(player) || bill3.hitPlayer(player)) {
            whacks.increaseBy(1);
            score.increaseBy(-3);
        }
        if (!bill1.inBounds()) {
            billSpawns.increaseBy(1);
            bill1 = new Bill(new Posn(billStartX, randomInt(120, 680)), billRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }
        if (!bill2.inBounds()) {
            billSpawns.increaseBy(1);
            bill2 = new Bill(new Posn(billStartX, randomInt(120, 680)), billRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }
        if (!bill3.inBounds()) {
            billSpawns.increaseBy(1);
            bill3 = new Bill(new Posn(billStartX, randomInt(120, 680)), billRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }

        return new BillGame(this.world, this.player, this.bill1.moveBillTowards(player),
                this.bill2.moveBillTowards(player), this.bill3.moveBillTowards(player));
    }
}

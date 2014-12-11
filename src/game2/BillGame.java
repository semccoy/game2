package game2;

import static game2.Utilities.*;
import java.awt.Color;
import javalib.funworld.*;
import javalib.worldimages.*;

public class BillGame extends World implements Constants {

    Player player;
    World world;
    Bill bill1, bill2, bill3;
    Powerup powerup;

    WorldImage newTrail = new RectangleImage(new Posn(-100, -100), 40, 40, Color.black);

    public BillGame(World world, Player player, Bill bill1, Bill bill2, Bill bill3, Powerup powerup) {
        super();
        this.player = player;
        this.world = world;
        this.bill1 = bill1;
        this.bill2 = bill2;
        this.bill3 = bill3;
        this.powerup = powerup;
    }



    public WorldImage buildWorld() {
        return new OverlayImages(universe, background);
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.world.makeImage(),
                new OverlayImages(showScore(),
                        new OverlayImages(showStats(),
                                new OverlayImages(background,
                                        new OverlayImages(powerup.powerupImage(),
                                                new OverlayImages(makeTrail(),
                                                        new OverlayImages(bill1.billImage(),
                                                                new OverlayImages(bill2.billImage(),
                                                                        new OverlayImages(bill3.billImage(),
                                                                                this.player.playerImage())))))))));
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

    public WorldImage showScore() {
        Color color = Color.white;
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, color),
                new TextImage(new Posn(WIDTH / 2, 65), score.print(), 20, color));
    }

    public WorldImage showStats() {
        Color color = Color.white;
        return new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 5, 25), "bills spawned: ", 15, color),
                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 100, 25), billSpawns.print(), 15, color),
                        new OverlayImages(new TextImage(new Posn(WIDTH / 5, 45), "times whacked: ", 15, color),
                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 100, 45), whacks.print(), 15, color),
                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 15, 65), "worlds fallen off of: ", 15, color),
                                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 100, 65), falls.print(), 15, color),
                                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 1, 85), "resets resetted: ", 15, color),
                                                                new TextImage(new Posn(WIDTH / 5 + 100, 85), resets.print(), 15, color))))))));
    }

    public World onKeyEvent(String key) {
        return new BillGame(this.world, this.player.movePlayer(key), this.bill1, this.bill2, this.bill3, this.powerup);
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
        if (powerup.hitPlayer(player)) {
            player.color = powerup.color;
             score.increaseBy(10);
        }
        
        if (bill1.hitPowerup(powerup) || bill2.hitPowerup(powerup) || bill3.hitPowerup(powerup)) {
            int rand = randomInt(0, 2);
            // if color or type is one thing, make it be another
            powerup.color = randomColor();
        }

        if (!bill1.inBounds()) {
            billSpawns.increaseBy(1);
            bill1 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }
        if (!bill2.inBounds()) {
            billSpawns.increaseBy(1);
            bill2 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }
        if (!bill3.inBounds()) {
            billSpawns.increaseBy(1);
            bill3 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo/2), Color.yellow);
        }
        if (!powerup.inBounds()) {
            int rand = randomInt(0, 1);
            if (rand == 0) {
                powerup = new Powerup(new Posn(randomInt(140, 300), 680), objectRadius, 0, 0, 2, "strongtail", Color.cyan);
            } else {
                powerup = new Powerup(new Posn(randomInt(140, 300), 120), objectRadius, 0, 0, 2, "strongtail", Color.cyan);
            }
        }

        return new BillGame(this.world, this.player, this.bill1.moveBillTowards(player),
                this.bill2.moveBillTowards(player), this.bill3.moveBillTowards(player),
                this.powerup.movePowerupAwayFrom(player));

    }
}

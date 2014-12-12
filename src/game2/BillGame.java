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
                                                new OverlayImages(bill1.billImage(),
                                                        new OverlayImages(bill2.billImage(),
                                                                new OverlayImages(bill3.billImage(),
                                                                        new OverlayImages(makeTrail(player),
                                                                                this.player.playerImage())))))))));
    }

    public WorldImage makeTrail(Player player) {
        int tailSize;
        int r = player.color.getRed();
        int g = player.color.getGreen();
        int b = player.color.getBlue();
        for (int i = 0; i < trail.size(); i++) {
            tailSize = 5 + i * 30 / maxTrailSize;
            if (player.color == powerupColors[0]) {
                // red
                RectangleImage temp = new RectangleImage(trail.get(i), tailSize, tailSize,
                        new Color(r, (r - 55) - i * 100 / maxTrailSize, (r - 55) - i * 100 / maxTrailSize));
                newTrail = new OverlayImages(newTrail, temp);
            } else if (player.color == powerupColors[1]) {
                // green
                RectangleImage temp = new RectangleImage(trail.get(i), tailSize, tailSize,
                        new Color((g - 55) - i * 100 / maxTrailSize, g, (g - 55) - i * 100 / maxTrailSize));
                newTrail = new OverlayImages(newTrail, temp);
            } else if (player.color == powerupColors[2]) {
                // blue
                RectangleImage temp = new RectangleImage(trail.get(i), tailSize, tailSize,
                        new Color((b - 55) - i * 100 / maxTrailSize, (b - 55) - i * 100 / maxTrailSize, b));
                newTrail = new OverlayImages(newTrail, temp);
            } else {
                // tealish
                RectangleImage temp = new RectangleImage(trail.get(i), tailSize, tailSize,
                        new Color((r + 55) + i * 100 / maxTrailSize, g, (b - 55) - i * 100 / maxTrailSize));
                newTrail = new OverlayImages(newTrail, temp);
            }
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

        int rand = randomInt(0, 2);

        // bill --> player
        if (bill1.hitPlayer(player) || bill2.hitPlayer(player) || bill3.hitPlayer(player)) {
            whacks.increaseBy(1);
            score.increaseBy(-1);
        }

        // powerup --> player
        if (powerup.hitPlayer(player)) {
            player.type = powerup.type;
            player.color = powerup.color;
            score.increaseBy(3);
        }

        // bill --> powerup
        if (bill1.hitPowerup(powerup) || bill2.hitPowerup(powerup) || bill3.hitPowerup(powerup)) {
            powerup.type = powerupTypes[rand];
            powerup.color = powerupColors[rand];
        }

        // bill --> oob
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
            bill3 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo / 2), Color.yellow);
        }

        // powerup --> oob
        if (!powerup.inBounds()) {
            int randy = randomInt(0, 1);
            if (randy == 0) {
                powerup = new Powerup(new Posn(randomInt(140, 300), 680), objectRadius, 0, 0, 2, powerupTypes[rand], powerupColors[rand]);
            } else {
                powerup = new Powerup(new Posn(randomInt(140, 300), 120), objectRadius, 0, 0, 2, powerupTypes[rand], powerupColors[rand]);
            }
        }

        if (player.type.equals(powerupTypes[0])) {
            if (trail.size() == 5) {
                if (bill1.hitTrail(trail)) {
                    score.increaseBy(3);
                    bill1 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                } else if (bill2.hitTrail(trail)) {
                    score.increaseBy(3);
                    bill2 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                } else if (bill3.hitTrail(trail)) {
                    score.increaseBy(3);
                    bill3 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                }
            }
        }

        return new BillGame(this.world, this.player, this.bill1.moveBillTowards(player),
                this.bill2.moveBillTowards(player), this.bill3.moveBillTowards(player),
                this.powerup.movePowerupAwayFrom(player));

    }
}

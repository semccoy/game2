package game2;

import static game2.Utilities.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javalib.funworld.*;
import javalib.worldimages.*;

public class BillGame extends World implements Constants {

    Player player;
    World world;
    public static Bill bill1, bill2, bill3;
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
                                new OverlayImages(showPowerups(),
                                        new OverlayImages(showOptions(),
                                                new OverlayImages(background,
                                                        new OverlayImages(powerup.powerupImage(),
                                                                new OverlayImages(bill1.billImage(),
                                                                        new OverlayImages(bill2.billImage(),
                                                                                new OverlayImages(bill3.billImage(),
                                                                                        new OverlayImages(makeTrail(player),
                                                                                                this.player.playerImage())))))))))));
    }

    public WorldImage showOptions() {
        String canHopWorldsHuh;
        if (score.score < 100) {
            canHopWorldsHuh = "";
        } else {
            canHopWorldsHuh = "World hop available!";
        }
        return new TextImage(new Posn(WIDTH / 2 + 350, 60), canHopWorldsHuh, 40, Color.white);
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

    public World onKeyEvent(String key) {
        // "+" for testing
        if (key.equals("+")) {
            score.increaseBy(1000);
        }
        // "l" for "leave"
        if (key.equals("l")) {
            playOnHuh.increaseBy(-1);
        }
        // "g" for "go"
        if (score.score > 100) {
            if (key.equals("g")) {
                player.color = playerStartColor;
                return new Pause(this.world, this.player);
            }
        }
        return new BillGame(this.world, this.player.movePlayer(key), this.bill1, this.bill2, this.bill3, this.powerup);
    }

    public WorldEnd worldEnds() {
        String finalText;
        if (playOnHuh.score.equals(0)) {
            if (score.score >= Pause.highscores.get(10)) {
                finalText = "Great job! Your score of " + score.score + " was added to the highscores!";
            } else {
                finalText = "Too bad! Your score of " + score.score + " was NOT added to the highscores!";
            }
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(WIDTH / 2, HEIGHT / 2 + 150), finalText, 30, Color.white)));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    public World onTick() {
        int rand = randomInt(0, 2);
        if (score.score <= -100) {
            playOnHuh.increaseBy(-1);
        }
        if (whacks.score > 200) {
            playOnHuh.increaseBy(-1);
        }
        // bill --> player
        if (bill1.hitPlayer(player) || bill2.hitPlayer(player) || bill3.hitPlayer(player)) {
            whacks.increaseBy(1);
            score.increaseBy(-1);
        }
        // powerup --> player
        if (powerup.hitPlayer(player)) {
            powerupsGotten.increaseBy(1);
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
            powerupSpawns.increaseBy(1);
            int randy = randomInt(0, 1);
            if (randy == 0) {
                powerup = new Powerup(new Posn(randomInt(140, 300), 680), objectRadius, 0, 0, 2, powerupTypes[rand], powerupColors[rand]);
            } else {
                powerup = new Powerup(new Posn(randomInt(140, 300), 120), objectRadius, 0, 0, 2, powerupTypes[rand], powerupColors[rand]);
            }
        }
        if (player.type.equals("normal")) {
            if (wipesLeft.score == 0) {
                player.color = playerStartColor;
            }
        }
        if (player.type.equals(powerupTypes[0])) {
            if (trail.size() == 5) {
                if (bill1.hitTrail(trail)) {
                    pokes.increaseBy(1);
                    score.increaseBy(3);
                    bill1 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                } else if (bill2.hitTrail(trail)) {
                    pokes.increaseBy(1);
                    score.increaseBy(3);
                    bill2 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                } else if (bill3.hitTrail(trail)) {
                    pokes.increaseBy(1);
                    score.increaseBy(3);
                    bill3 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                }
            }
        }
        if (player.type.equals(powerupTypes[1])) {
            player.type = "normal";
            wipesLeft.increaseBy(1);
            score.increaseBy(3);
        }
        if (player.type.equals(powerupTypes[2])) {
            bill1.speed = bill2.speed = bill3.speed = powerup.speed = 1;
        }
        return new BillGame(this.world, this.player, this.bill1.moveBillTowards(player),
                this.bill2.moveBillTowards(player), this.bill3.moveBillTowards(player),
                this.powerup.movePowerupAwayFrom(player));
    }
}

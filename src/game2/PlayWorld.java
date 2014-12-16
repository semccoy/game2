package game2;

import static game2.Utilities.*;
import java.awt.*;
import java.io.IOException;
import javalib.funworld.*;
import javalib.worldimages.*;

public class PlayWorld extends World implements Constants {

    Player player;
    World world;
    public static Dot dot1, dot2, dot3;
    Powerup powerup;

    WorldImage newTrail = new RectangleImage(new Posn(-100, -100), 40, 40, Color.black);

    public PlayWorld(World world, Player player, Dot dot1, Dot dot2, Dot dot3, Powerup powerup) {
        super();
        this.player = player;
        this.world = world;
        this.dot1 = dot1;
        this.dot2 = dot2;
        this.dot3 = dot3;
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
                                                                new OverlayImages(dot1.dotImage(),
                                                                        new OverlayImages(dot2.dotImage(),
                                                                                new OverlayImages(dot3.dotImage(),
                                                                                        new OverlayImages(makeTrail(player),
                                                                                                this.player.playerImage())))))))))));
    }

    public WorldImage showOptions() {
        String canHopWorldsHuh;
        if (invisibleScore.score < 100) {
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
            score.increaseBy(100);
            invisibleScore.increaseBy(100);
        }
        // "l" for "leave"
        if (key.equals("l")) {
            playOnHuh.increaseBy(-1);
        }
        // "g" for "go"
        if (invisibleScore.score >= 100) {
            if (key.equals("g")) {
                player.color = playerStartColor;
                return new PauseWorld(this.world, this.player);
            }
        }
        return new PlayWorld(this.world, this.player.movePlayer(key), this.dot1, this.dot2, this.dot3, this.powerup);
    }

    public WorldEnd worldEnds() {
        String finalText;
        if (playOnHuh.score.equals(0)) {
            if (score.score >= PauseWorld.highscores.get(PauseWorld.highscores.size() - 1)) {
                PauseWorld.insertScore(score);
                finalText = "Great job! Your score of " + score.score + " was added to the highscores!";
            } else if (score.score <= -100) {
                finalText = "You lose! Your score of " + score.score + " was really bad! :(";
            } else if (whacks.score > 200) {
                finalText = "You lose! You've been whacked " + whacks.score + " times, which is just too many! :(";
            } else {
                finalText = "Too bad! Your score of " + score.score + " was too low to be added to the highscores! :(";
            }
            try {
                PauseWorld.saveHighscores();
                System.out.println("Highscores after end of game:");
                PauseWorld.printOutHighscores();
            } catch (IOException ex) {
                System.out.println("saveHighscores or printOutHighscores failure" + ex);
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
        // dot --> player
        if (dot1.hitPlayer(player) || dot2.hitPlayer(player) || dot3.hitPlayer(player)) {
            whacks.increaseBy(1);
            score.increaseBy(-1);
            invisibleScore.increaseBy(-1);
        }
        // powerup --> player
        if (powerup.hitPlayer(player)) {
            powerupsGotten.increaseBy(1);
            player.type = powerup.type;
            player.color = powerup.color;
            score.increaseBy(3);
            invisibleScore.increaseBy(3);
        }
        // dot --> powerup
        if (dot1.hitPowerup(powerup) || dot2.hitPowerup(powerup) || dot3.hitPowerup(powerup)) {
            powerup.type = powerupTypes[rand];
            powerup.color = powerupColors[rand];
        }
        // dot --> oob
        if (!dot1.inBounds()) {
            dotSpawns.increaseBy(1);
            dot1 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }
        if (!dot2.inBounds()) {
            dotSpawns.increaseBy(1);
            dot2 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
        }
        if (!dot3.inBounds()) {
            dotSpawns.increaseBy(1);
            dot3 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo / 2), Color.yellow);
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
                if (dot1.hitTrail(trail)) {
                    pokes.increaseBy(1);
                    score.increaseBy(3);
                    invisibleScore.increaseBy(3);
                    dot1 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                } else if (dot2.hitTrail(trail)) {
                    pokes.increaseBy(1);
                    score.increaseBy(3);
                    invisibleScore.increaseBy(3);
                    dot2 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                } else if (dot3.hitTrail(trail)) {
                    pokes.increaseBy(1);
                    score.increaseBy(3);
                    invisibleScore.increaseBy(3);
                    dot3 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                }
            }
        }
        if (player.type.equals(powerupTypes[1])) {
            player.type = "normal";
            wipesLeft.increaseBy(1);
            score.increaseBy(3);
            invisibleScore.increaseBy(3);
        }
        if (player.type.equals(powerupTypes[2])) {
            dot1.speed = dot2.speed = dot3.speed = powerup.speed = 1;
        }
        return new PlayWorld(this.world, this.player, this.dot1.moveDotTowards(player),
                this.dot2.moveDotTowards(player), this.dot3.moveDotTowards(player),
                this.powerup.movePowerupAwayFrom(player));
    }
}

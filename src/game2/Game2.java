package game2;

import static game2.Utilities.*;
import javalib.funworld.*;
import javalib.worldimages.*;
import java.awt.*;

public class Game2 extends World implements Constants {

    public static WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);

    public Game2(WorldImage uni) {
        super();
        this.universe = uni;
    }

    public WorldImage makeImage() {
        return universe;
    }

    public World onKeyEvent(String key) {
        return new Game2(universe);
    }

    public static void main(String[] args) throws Exception {

        Tests.testAllTheThings();

        // reset all scores or print them out or something before player plays
        System.out.println("Before you played, the testing suite obtained the following scores:");
        System.out.println("    Note that all scores were tested; most were reset by the suite.");
        System.out.println("    The suite ran " + numberOfTests + " times.\n");
        System.out.println("score: " + score.print());
        System.out.println("falls: " + falls.print());
        System.out.println("resets: " + resets.print());
        System.out.println("\nThese scores have now been reset. Have fun!\n");

        score.score = 0;
        invisibleScore.score = 0;
        whacks.score = 0;
        dotSpawns.score = 3;
        falls.score = 0;
        resets.score = 0;
        powerupSpawns.score = 1;
        powerupsGotten.score = 0;
        pokes.score = 0;
        wipesLeft.score = 0;
        playOnHuh.score = 1;
        trail.clear();

        // begin normal gameplay stuff
        PauseWorld.accessHighscores();
        System.out.println("Highscores before start of game:");
        PauseWorld.printOutHighscores();
        int rand = randomInt(0, 2);
        PlayWorld game = new PlayWorld(new Game2(universe),
                new Player(playerStart, 40, 40, "normal", playerStartColor),
                new Dot(dot1Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                new Dot(dot2Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                new Dot(dot3Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                new Powerup(powerupStart, 20, 0, 0, randomInt(1, 4), powerupTypes[rand], powerupColors[rand]));
        game.bigBang(WIDTH, HEIGHT, .01);
    }
}

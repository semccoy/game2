package game2;

import static game2.Constants.HEIGHT;
import static game2.Constants.WIDTH;
import static game2.Constants.bill1Start;
import static game2.Constants.bill2Start;
import static game2.Constants.bill3Start;
import static game2.Constants.objectRadius;
import static game2.Constants.playerStart;
import static game2.Constants.playerStartColor;
import static game2.Constants.powerupColors;
import static game2.Constants.powerupStart;
import static game2.Constants.powerupTypes;
import static game2.Game2.universe;
import static game2.Utilities.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javalib.funworld.*;
import javalib.worldimages.*;

public class Pause extends World implements Constants {
    // TODO:
    //  highscores 
    // reminders of how to game

    static ArrayList<Integer> highscores = new ArrayList<Integer>();

    World world;
    Player player;

    public Pause(World world, Player player) {
        super();
        this.world = world;
        this.player = player;
    }

    public WorldImage buildWorld() {
        return new OverlayImages(universe, background);
    }

    public WorldImage makeImage() {
        return new OverlayImages(this.world.makeImage(),
                new OverlayImages(showScore(),
                        new OverlayImages(showStats(),
                                new OverlayImages(showPowerups(),
                                        new OverlayImages(background,
                                                this.player.playerImage())))));
    }

    public WorldImage showScore() {
        Color color = Color.white;
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, color),
                new TextImage(new Posn(WIDTH / 2, 65), score.print(), 20, color));
    }

    public World onKeyEvent(String key) {
        if (key.equals("b")) {
            int rand = randomInt(0, 2);
            return new BillGame(new Game2(universe),
                    new Player(playerStart, 40, 40, "normal", playerStartColor),
                    new Bill(bill1Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                    new Bill(bill2Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                    new Bill(bill3Start, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                    new Powerup(powerupStart, 20, 0, 0, randomInt(1, 4), powerupTypes[rand], powerupColors[rand]));
        } else {
            return new Pause(this.world, this.player.movePlayer(key));
        }
    }

    public static void displayHighscores() throws IOException {
        FileReader fileReader = new FileReader("highscores.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        for (int i = 0; i < 10; i++) {
            highscores.add(Integer.parseInt(line));
            System.out.println("" + highscores.get(i));
            line = bufferedReader.readLine();
        }
        fileReader.close();
    }

    public static void potentiallyInsertScore(Score score) {
        if (score.score > highscores.size()) {
            highscores.add(score.score);
            Collections.sort(highscores);
            Collections.reverse(highscores);
        }
    }

    public static void saveHighscores() throws IOException {
        FileWriter fileWriter = new FileWriter("highscores.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < 10; i++) {
            String currentLine = Integer.toString(highscores.get(i));
            if (currentLine.length() == 0) {
                break;
            }
            printWriter.println(currentLine);
        }
        fileWriter.close();
    }
}

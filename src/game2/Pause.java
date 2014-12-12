package game2;

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
    WorldImage highscoreList = new TextImage(new Posn(WIDTH / 2, 150), "HIGHSCORES:", 30, Color.white);

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
                                        new OverlayImages(showOptions(),
                                                new OverlayImages(background,
                                                        new OverlayImages(this.player.playerImage(),
                                                                showHighscores())))))));
    }

    public WorldImage showHighscores() {
        for (int i = 0; i < 10; i++) {
            TextImage temp = new TextImage(new Posn(WIDTH / 2, 200 + 25 * i),
                    (i + 1) + ".   " + Integer.toString(highscores.get(i)), 20, Color.white);
            highscoreList = new OverlayImages(highscoreList, temp);
        }
        return highscoreList;

//        Color color = Color.white;
//        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, color),
//                new TextImage(new Posn(WIDTH / 2, 65), score.print(), 20, color));
    }

    public WorldImage showOptions() {
        String canHopWorldsHuh = "World hop available!";
        return new TextImage(new Posn(WIDTH / 2 + 350, 60), canHopWorldsHuh, 40, Color.white);
    }

    public World onKeyEvent(String key) {
        // "+" for testing
        if (key.equals("+")) {
            score.increaseBy(100);
        }
        // "l" for "leave"
        if (key.equals("l")) {
            playOnHuh.increaseBy(-1);
        }
        // "b" for "BillGame"
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

    public WorldEnd worldEnds() {
        String finalText;

        if (playOnHuh.score.equals(0)) {
            try {
                accessHighscores();
            } catch (IOException ex) {
                System.out.println("accessHighscores failure" + ex);
            }

            if (score.score >= highscores.get(highscores.size() - 1)) {
                insertScore(score);
                finalText = "Great job! Your score of " + score.score + " was added to the highscores!";
            } else if (score.score <= -100) {
                finalText = "You lose! Your score of " + score.score + " was really bad! :(";
            } else {
                finalText = "Too bad! Your score of " + score.score + " was too low to be added to the highscores!";
            }

            try {
                saveHighscores();
            } catch (IOException ex) {
                System.out.println("saveHighscores failure" + ex);
            }

//            System.out.println("last in list --- " + score.score);
//            System.out.println("last in list --- " + highscores.get(highscores.size() - 1));
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(WIDTH / 2, HEIGHT / 2 + 150), finalText, 30, Color.white)));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    public static void accessHighscores() throws IOException {
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

    public static void insertScore(Score score) {
        highscores.add(5, score.score); // add score somewhere in the middle
        Collections.sort(highscores);
        Collections.reverse(highscores);
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

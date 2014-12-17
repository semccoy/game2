package game2;

import static game2.Utilities.*;
import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

public interface Constants {

    // test stuff
    int numberOfTests = 100;

    // player stuff
    Posn playerStart = new Posn(720, 400);
    Color playerStartColor = new Color(55, 200, 255);

    // trail stuff
    int maxTrailSize = 5;
    public ArrayList<Posn> trail = new ArrayList<Posn>();

    // object stuff
    int wiggliness = 2;
    int speedo = 4; // default max speed of dots
    int objectRadius = 20;
    int dotStartX = 1320;
    int powerupTop = 120;
    int powerupBottom = 680;
    Posn dot1Start = new Posn(dotStartX, 120);
    Posn dot2Start = new Posn(dotStartX, 400);
    Posn dot3Start = new Posn(dotStartX, 680);
    Posn powerupStart = new Posn(1000, 400);
    String[] powerupTypes = {"trailwhip", "wiper", "slowtime"};
    Color[] powerupColors = {new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255)};

    // stat attributes
    Score score = new Score(0);
    Score invisibleScore = new Score(0);
    Score whacks = new Score(0);
    Score dotSpawns = new Score(3);
    Score falls = new Score(0);
    Score resets = new Score(0);

    // powerup attributes
    Score powerupSpawns = new Score(1);
    Score powerupsGotten = new Score(0);
    Score pokes = new Score(0);
    Score wipesLeft = new Score(0);

    // world stuff
    Score playOnHuh = new Score(1);

    // display stuff
    int step = 40;
    int WIDTH = 1440;
    int HEIGHT = 800;
    Posn base = new Posn(WIDTH / 2, HEIGHT / 2);
    WorldImage background = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);
    WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);
}

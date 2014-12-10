package game2;

import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

public interface Constants {

    // test stuff
    int numberOfTests = 0;

    // player stuff
    Posn playerStart = new Posn(720, 400);
    Color playerColor = Color.red;

    // trail stuff
    int maxTrailSize = 8;
    ArrayList<Posn> trail = new ArrayList<Posn>();

    // bill stuff
    int billRadius = 20;
    int billStartX = 1320;
    Posn bill1Start = new Posn(1320, 120);
    Posn bill2Start = new Posn(1320, 400);
    Posn bill3Start = new Posn(1320, 680);

    // other attributes
    int speedo = 4; // default max speed of bills
    Score score = new Score(0);
    Score whacks = new Score(0);
    Score billSpawns = new Score(3);
    Score falls = new Score(0);
    Score resets = new Score(0);

    // world stuff
    boolean thisIsTheEnd = false;

    // display stuff
    int step = 40;
    int WIDTH = 1440;
    int HEIGHT = 800;
    Posn base = new Posn(WIDTH / 2, HEIGHT / 2);
    WorldImage background = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);
    WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);
}

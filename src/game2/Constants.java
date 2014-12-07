package game2;

import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

public interface Constants {

    int numberOfTests = 0;
    int maxTrailSize = 5;
    ArrayList<Posn> trail = new ArrayList<Posn>();
    Score score = new Score(0);
    int step = 40;
    int WIDTH = 1440;
    int HEIGHT = 800;
    int billRadius = 20;
    Posn base = new Posn(WIDTH / 2, HEIGHT / 2);
    Posn bill1Start = new Posn(1320, 120);
    Posn bill2Start = new Posn(1320, 400);
    Posn bill3Start = new Posn(1320, 680);
    
    boolean thisIsTheEnd = false;
    Posn playerStart = new Posn(720, 400);
    Color playerColor = Color.red;
    WorldImage background = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);
    WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);
}

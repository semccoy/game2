package game2;

import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

public interface Constants {

    int maxTrailSize = 5;
    ArrayList<Posn> trail = new ArrayList<Posn>();
    // Trail trail = new Trail(new ArrayList<Posn>(maxTrailSize));
    Score score = new Score(0);
    int step = 40;
    int WIDTH = 1440;
    int HEIGHT = 800;
    int CELLSIZE = 40;
    int CELLSWIDE = 27;
    int CELLSHIGH = 15;
    int BillSize = 40;
    int BillRadius = 40;
    Posn base = new Posn(WIDTH / 2, HEIGHT / 2);
    // collision stuff
//    Posn p1 = new Posn(410, Utilities.randomInt(240, HEIGHT - 160));
//    Posn p2 = new Posn(1030, Utilities.randomInt(240, HEIGHT - 160));
//    Bill bill1 = new Bill(p1, BillSize, Utilities.randomColor());
//    Bill bill2 = new Bill(p2, BillSize, Utilities.randomColor());
    int minMovements = 0;
    int numberOfTests = 0;
    boolean inLobbyHuh = true;
    boolean inGameOneHuh = false;
    boolean inGameTwoHuh = false;
    boolean thisIsTheEnd = false;
    Random rand = new Random();
    Posn playerStart = new Posn(400, 400);
    Posn upperleft = new Posn(200, 120);
    Color playerColor = Color.red;
    WorldImage lobby = new RectangleImage(base, WIDTH - 200, HEIGHT - 200, Color.gray);
    WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);
}

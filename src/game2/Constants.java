package game2;

import javalib.funworld.*;
import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

public interface Constants {

    int step = 40;
    int WIDTH = 1440;
    int HEIGHT = 800;
    int CELLSIZE = 40;
    int CELLSWIDE = 27;
    int CELLSHIGH = 15;
    int portalSize = 40;
    int portalRadius = 40;
    Posn p1 = new Posn(410, Utilities.randomInt(240, HEIGHT - 160));
    Posn p2 = new Posn(1030, Utilities.randomInt(240, HEIGHT - 160));
    Portal portal1 = new Portal(p1, portalSize, Utilities.randomColor());
    Portal portal2 = new Portal(p2, portalSize, Utilities.randomColor());
    int minMovements = 0;
    int numberOfTests = 0;
    boolean inLobbyHuh = true;
    boolean inGameOneHuh = false;
    boolean inGameTwoHuh = false;
    boolean thisIsTheEnd = false;
    Random rand = new Random();
    Posn playerStart = new Posn(720, 400);
    Posn upperleft = new Posn(200, 120);
    Posn base = new Posn(WIDTH / 2, HEIGHT / 2);
    public WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);
}

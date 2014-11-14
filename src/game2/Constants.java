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

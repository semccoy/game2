package game2;

import javalib.impworld.*;
import javalib.worldimages.*;
import java.awt.*;
import java.util.*;

public interface Constants {
    int step = 20;
    int WIDTH = 1440;
    int HEIGHT = 800;
    int CELLSIZE = 40;
    int CELLSWIDE = 27;
    int CELLSHIGH = 15;
    int minMovements = 0;
    int numberOfTests = 0;
    boolean thisIsTheEnd = false;
    // booleans for which game to play
    Random rand = new Random();
    Posn upperleft = new Posn(200, 120);
    Posn base = new Posn(WIDTH / 2, HEIGHT / 2);
    public WorldImage universe = new RectangleImage(base, WIDTH, HEIGHT, Color.black);
    public WorldImage background = new RectangleImage(base, 1080, 600, Color.lightGray);
    public RectangleImage start = new RectangleImage(new Posn(240, 160), CELLSIZE, CELLSIZE, Color.green);
    public ArrayList<RectangleImage> worldArray = new ArrayList<RectangleImage>();
    public ArrayList<RectangleImage> pathArray = new ArrayList<RectangleImage>();
    public ArrayList<RectangleImage> tempPaths = new ArrayList<RectangleImage>();

}

package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Player implements Constants {

    static int movements = 0;

    Posn center;
    int length;
    int width;
    Color color;

    Player(Posn center, int length, int width, Color color) {
        this.center = center;
        this.length = length;
        this.width = width;
        this.color = color;
    }

    WorldImage playerImage() {
        return new RectangleImage(this.center, this.length, this.width, this.color);
    }

    // make this while (inbounds), else you lose points, get recentered, and it yells at you
    public Player movePlayer(String key) {
        if ((key.equals("up") || key.equals("w"))) {
            movements++;
            return new Player(new Posn(this.center.x, this.center.y - step), this.length, this.width, this.color);
        } else if ((key.equals("down") || key.equals("s"))) {
            movements++;
            return new Player(new Posn(this.center.x, this.center.y + step), this.length, this.width, this.color);
        } else if ((key.equals("left") || key.equals("a"))) {
            movements++;
            return new Player(new Posn(this.center.x - step, this.center.y), this.length, this.width, this.color);
        } else if ((key.equals("right") || key.equals("d"))) {
            movements++;
            return new Player(new Posn(this.center.x + step, this.center.y), this.length, this.width, this.color);
        } else if (key.equals("x")) {
            movements = movements + 5;
            return new Player(new Posn(500, 250), this.length, this.width, this.color);
        } else {
            return new Player(this.center, this.length, this.width, this.color);
        }
    }


    public Posn getCoords() {
        return new Posn(this.center.x, this.center.y);
    }
    
    // frame image to bound world
    
    
    boolean outOfBounds(int width, int height) {
        return this.center.x < 0
                || this.center.x > width
                || this.center.y < 0
                || this.center.y > height;
    }

}

package game2;

import static game2.Utilities.*;
import java.awt.*;
import java.util.ArrayList;
import javalib.worldimages.*;

public class Dot implements Constants {

    Posn center;
    int radius;
    int dx;
    int dy;
    int speed;
    Color color;

    Dot(Posn center, int radius, int dx, int dy, int speed, Color color) {
        this.center = center;
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.color = color;
    }

    WorldImage dotImage() {
        return new DiskImage(this.center, this.radius, this.color);
    }

    public WorldImage placeDot() {
        return this.dotImage();
    }

    public Dot moveDotTowards(Player player) {
        int my = this.center.y - player.center.y;
        return new Dot(new Posn(this.center.x - speed * 5, this.center.y - randomInt(wiggliness - 1, wiggliness + 1) * my / step),
                this.radius, this.dx, this.dy, this.speed, this.color);
    }

    boolean inBounds() {
        return !(hitTopBorder()) && !(hitBottomBorder()) && !(hitLeftBorder());
    }

    public boolean hitTopBorder() {
        return this.center.y < 100;
    }

    public boolean hitBottomBorder() {
        return this.center.y > 700;
    }

    public boolean hitLeftBorder() {
        return this.center.x < 140;
    }

    boolean hitPlayer(Player player) {
        return (this.center.x >= player.center.x - 2 * objectRadius)
                && (this.center.x <= player.center.x + 2 * objectRadius)
                && (this.center.y >= player.center.y - 2 * objectRadius)
                && (this.center.y <= player.center.y + 2 * objectRadius);
    }

    boolean hitPowerup(Powerup powerup) {
        return (this.center.x >= powerup.center.x - 2 * objectRadius)
                && (this.center.x <= powerup.center.x + 2 * objectRadius)
                && (this.center.y >= powerup.center.y - 2 * objectRadius)
                && (this.center.y <= powerup.center.y + 2 * objectRadius);
    }

    boolean hitTrail(ArrayList<Posn> trail) {
        return (this.center.x >= trail.get(0).x - 2 * objectRadius)
                && (this.center.x <= trail.get(0).x + 2 * objectRadius)
                && (this.center.y >= trail.get(0).y - 2 * objectRadius)
                && (this.center.y <= trail.get(0).y + 2 * objectRadius);
    }
}

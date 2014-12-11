package game2;

import static game2.Utilities.*;
import java.awt.Color;
import javalib.worldimages.*;

public class Powerup implements Constants, BoundedObject {

    Posn center;
    int radius;
    int dx;
    int dy;
    int speed;
    String type;
    Color color;

    Powerup(Posn center, int radius, int dx, int dy, int speed, String type, Color color) {
        this.center = center;
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.type = type;
        this.color = color;
    }

    WorldImage powerupImage() {
        return new DiskImage(this.center, this.radius, this.color);
    }

    public WorldImage placePowerup() {
        return this.placePowerup();
    }

    public boolean tooCloseToPlayer(Player player) {
        return (this.center.x <= player.center.x + 200)
                && (this.center.x >= player.center.x - 200)
                && (this.center.y <= player.center.y + 100)
                && (this.center.y >= player.center.y - 100);
    }

    // make this use player coords
    public Powerup movePowerupAwayFrom(Player player) {
        if (this.center.y > 400) {
            return new Powerup(new Posn(this.center.x + speed * 5, this.center.y - speed),
                    this.radius, this.dx, this.dy, this.speed, this.type, this.color);
        } else {
            return new Powerup(new Posn(this.center.x + speed * 5, this.center.y + speed),
                    this.radius, this.dx, this.dy, this.speed, this.type, this.color);
        }
    }

    boolean inBounds() {
        return //!(hitTopBorder())  && !(hitBottomBorder()) &&
                !(hitLeftBorder()) && !(hitRightBorder());
    }

    public boolean hitTopBorder() {
        return this.center.y < 140;
    }

    public boolean hitBottomBorder() {
        return this.center.y > 660;
    }

    public boolean hitLeftBorder() {
        return this.center.x < 140;
    }

    public boolean hitRightBorder() {
        return this.center.x > 1300;
    }

    boolean hitPlayer(Player player) {
        return (this.center.x >= player.center.x - objectRadius)
                && (this.center.x <= player.center.x + objectRadius)
                && (this.center.y >= player.center.y - objectRadius)
                && (this.center.y <= player.center.y + objectRadius);
    }
}

package game2;

import static game2.Utilities.*;
import java.awt.*;
import javalib.worldimages.*;

public class Bill implements Constants, BoundedObject {

    Posn center;
    int radius;
    int dx;
    int dy;
    int speed;
    Color color;

    Bill(Posn center, int radius, int dx, int dy, int speed, Color color) {
        this.center = center;
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
        this.color = color;
    }

    WorldImage billImage() {
        return new DiskImage(this.center, this.radius, this.color);
    }

    public WorldImage placeBill() {
        return this.billImage();
    }

    public Bill moveBillTowards(Player player) {
        int mx = this.center.x - player.center.x;
        int my = this.center.y - player.center.y;
        return new Bill(new Posn(this.center.x - speed * 5, this.center.y - randomInt(wiggliness - 1, wiggliness + 1) * my / step),
                this.radius, this.dx, this.dy, this.speed, this.color);
//        return new Bill(new Posn(this.center.x - randomInt(wiggliness - 1, wiggliness + 1) * mx / step,  this.center.y- speed * 5),
//                this.radius, this.dx, this.dy, this.speed, this.color); // and return bills from bottom of screen?
    }

    boolean inBounds() {
        return !(hitTopBorder()) && !(hitBottomBorder())
                && !(hitLeftBorder());
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
        return (this.center.x >= player.center.x - objectRadius)
                && (this.center.x <= player.center.x + objectRadius)
                && (this.center.y >= player.center.y - objectRadius)
                && (this.center.y <= player.center.y + objectRadius);
    }

    boolean hitPowerup(Powerup powerup) {
        return (this.center.x >= powerup.center.x - objectRadius)
                && (this.center.x <= powerup.center.x + objectRadius)
                && (this.center.y >= powerup.center.y - objectRadius)
                && (this.center.y <= powerup.center.y + objectRadius);
    }
}

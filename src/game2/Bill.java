package game2;

import static game2.Utilities.*;
import java.awt.*;
import javalib.worldimages.*;

public class Bill implements Constants, BoundedObject {

    Posn center;
    int radius;
    Color color;
    int dx;
    int dy;
    int speed;

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

    int wiggliness = 2;

    public Bill moveBillTowards(Player player) {
        int my = this.center.y - player.center.y;

        return new Bill(new Posn(this.center.x - speed * 5, this.center.y - randomInt(wiggliness - 1, wiggliness + 1) * my / step),
                this.radius, this.dx, this.dy, this.speed, this.color);
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

    // bills don't have hitRightBorder because they start there
    boolean hitPlayer(Player player) {
        return this.center.x >= player.center.x - billRadius
                && this.center.x <= player.center.x + billRadius
                && this.center.y >= player.center.y - billRadius
                && this.center.y <= player.center.y + billRadius;
    }

}

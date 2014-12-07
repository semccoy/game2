package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Bill implements Constants {

    Posn center;
    int radius;
    Color color;
    int dx;
    int dy;

    Bill(Posn center, int radius, int dx, int dy, Color color) {
        this.center = center;
        this.radius = radius;
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    WorldImage billImage() {
        return new DiskImage(this.center, this.radius, this.color);
    }

    public WorldImage placeBill() {
        return this.billImage();
    }

    public Bill moveBillTowards(Player player) {
//        int mx = this.center.x - player.center.x;
        int my = this.center.y - player.center.y;
        return new Bill(new Posn(this.center.x + step, this.center.y + my / step), this.radius, this.dx, this.dy, this.color);
    }

    // interface this stuff with player ("thing in playing field" interface)
    boolean inBounds() {
        return !(this.center.y < 140) && !(this.center.y > 660)
                && !(this.center.x < 140) && !(this.center.x > 1300);
    }

    boolean atTopBorder() {
        return this.center.y < 140;
    }

    boolean atBottomBorder() {
        return this.center.y > 660;
    }

    boolean atLeftBorder() {
        return this.center.x < 140;
    }

    boolean atRightBorder() {
        return this.center.x > 1300;
    }

    boolean hitPlayer(Player player) {
        return this.center == player.center;
    }

}

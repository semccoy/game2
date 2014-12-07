package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Bill implements Constants, BoundedObject {

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

    int slowDownBy = 2;
    int wiggliness = 4;

    public Bill moveBillTowards(Player player) {
        int my = this.center.y - player.center.y;
        return new Bill(new Posn(this.center.x - step / slowDownBy, this.center.y - wiggliness * my / step),
                this.radius, this.dx, this.dy, this.color);
    }

    // interface this stuff with player ("thing in playing field" interface)
    boolean inBounds() {
        return !(atTopBorder()) && !(atBottomBorder())
                && !(atLeftBorder()) && !(atRightBorder());
    }

    public boolean atTopBorder() {
        return this.center.y < 100;
    }

    public boolean atBottomBorder() {
        return this.center.y > 700;
    }

    public boolean atLeftBorder() {
        return this.center.x < 140;
    }

    public boolean atRightBorder() {
        return this.center.x > 1300;
    }

    boolean hitPlayer(Player player) {
        return this.center.x >= player.center.x - billRadius/2
                && this.center.x <= player.center.x + billRadius/2
                && this.center.y >= player.center.y - billRadius/2
                && this.center.y <= player.center.y + billRadius/2;
    }

}

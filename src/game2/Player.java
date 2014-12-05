package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Player implements Constants {

    static int movements = 0;

    Posn center;
    int length;
    int width;
    Color color;

    public Player(Posn center, int length, int width, Color color) {
        this.center = center;
        this.length = length;
        this.width = width;
        this.color = color;
    }

    WorldImage playerImage() {
        return new RectangleImage(this.center, this.length, this.width, this.color);
    }

    public Player movePlayer(String key) {
        // collision stuff
//        if (insideBill(Bill1)) {
//            System.out.println("1");
//        }
//        if (insideBill(Bill2)) {
//            System.out.println("2");
//        }
        if ((key.equals("up"))) {
            if (atTopBorder(this)) {
                movements += 10;
                return new Player(playerStart, this.length, this.width, this.color);
            }
            movements++;
            return new Player(new Posn(this.center.x, this.center.y - step), this.length, this.width, this.color);
        } else if ((key.equals("down"))) {
            if (atBottomBorder(this)) {
                movements += 10;
                return new Player(playerStart, this.length, this.width, this.color);
            }
            movements++;
            return new Player(new Posn(this.center.x, this.center.y + step), this.length, this.width, this.color);
        } else if ((key.equals("left"))) {
            if (atLeftBorder(this)) {
                movements += 10;
                return new Player(playerStart, this.length, this.width, this.color);
            }
            movements++;
            return new Player(new Posn(this.center.x - step, this.center.y), this.length, this.width, this.color);
        } else if ((key.equals("right"))) {
            if (atRightBorder(this)) {
                movements += 10;
                return new Player(playerStart, this.length, this.width, this.color);
            }
            movements++;
            return new Player(new Posn(this.center.x + step, this.center.y), this.length, this.width, this.color);
        } else if (key.equals("x") && !this.center.equals(playerStart)) {
            movements += 5;
            return new Player(playerStart, this.length, this.width, this.color);
        } else {
            return new Player(this.center, this.length, this.width, this.color);
        }

    }

    boolean atTopBorder(Player player) {
        return player.center.y < 140;
    }

    boolean atBottomBorder(Player player) {
        return player.center.y > 660;
    }

    boolean atLeftBorder(Player player) {
        return player.center.x < 140;
    }

    boolean atRightBorder(Player player) {
        return player.center.x > 1300;
    }

    // collision stuff, might be useful later on...
//    public boolean insideBill(Bill bill) {
//        if (this.center.x <= bill.center.x + step
//                && this.center.x >= bill.center.x - step
//                && this.center.y <= bill.center.y + step
//                && this.center.y >= bill.center.y - step) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}

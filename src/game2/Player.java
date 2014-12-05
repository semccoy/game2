package game2;

import java.awt.*;
import java.util.ArrayList;
import javalib.worldimages.*;

public class Player implements Constants {

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
//        if (insideBill(bill1)) {
//            System.out.println("1");
//        }
//        if (insideBill(bill2)) {
//            System.out.println("2");
//        }
        
        
        trail.addToTrail(this.center);
        
        
        switch (key) {
            case "up":
                if (atTopBorder(this)) {
                    score.increaseBy(10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x, this.center.y - step), this.length, this.width, this.color);
            case "down":
                if (atBottomBorder(this)) {
                    score.increaseBy(10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x, this.center.y + step), this.length, this.width, this.color);
            case "left":
                if (atLeftBorder(this)) {
                    score.increaseBy(10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x - step, this.center.y), this.length, this.width, this.color);
            case "right":
                if (atRightBorder(this)) {
                    score.increaseBy(10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x + step, this.center.y), this.length, this.width, this.color);
            case "x":
                score.increaseBy(5);
                return new Player(playerStart, this.length, this.width, this.color);
            default:
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

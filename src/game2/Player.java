package game2;

import static game2.Constants.billStartX;
import static game2.Constants.objectRadius;
import static game2.Constants.speedo;
import static game2.Utilities.randomInt;
import java.awt.*;
import javalib.worldimages.*;

public class Player implements Constants, BoundedObject {

    Posn center;
    int length;
    int width;
    String type;
    Color color;

    // TODO:
    // Persistent player attributes.
    // For example, the player in Zork carries various items and can use and dispose of them throughout the map.
    public Player(Posn center, int length, int width, String type, Color color) {
        this.center = center;
        this.length = length;
        this.width = width;
        this.type = type;
        this.color = color;
    }

    WorldImage playerImage() {
        return new RectangleImage(this.center, this.length, this.width, this.color);
    }

    public void populatePlayerTrail() {
        Posn newPos = this.center;
        if (this.trail.size() < maxTrailSize) {
            this.trail.add(newPos);
        } else {
            for (int i = 0; i < maxTrailSize - 1; i++) {
                this.trail.set(i, this.trail.get(i + 1));
            }
            this.trail.set(maxTrailSize - 1, newPos);
        }
    }

    public Player movePlayer(String key) {
        populatePlayerTrail();

        switch (key) {
            case "up":
                if (hitTopBorder()) {
                    falls.increaseBy(1);
                    score.increaseBy(-10);
                    invisibleScore.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.type, this.color);
                }
                score.increaseBy(1);
                invisibleScore.increaseBy(1);
                return new Player(new Posn(this.center.x, this.center.y - step), this.length, this.width, this.type, this.color);
            case "down":
                if (hitBottomBorder()) {
                    falls.increaseBy(1);
                    score.increaseBy(-10);
                    invisibleScore.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.type, this.color);
                }
                score.increaseBy(1);
                invisibleScore.increaseBy(1);
                return new Player(new Posn(this.center.x, this.center.y + step), this.length, this.width, this.type, this.color);
            case "left":
                if (hitLeftBorder()) {
                    falls.increaseBy(1);
                    score.increaseBy(-10);
                    invisibleScore.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.type, this.color);
                }
                score.increaseBy(1);
                invisibleScore.increaseBy(1);
                return new Player(new Posn(this.center.x - step, this.center.y), this.length, this.width, this.type, this.color);
            case "right":
                if (hitRightBorder()) {
                    falls.increaseBy(1);
                    score.increaseBy(-10);
                    invisibleScore.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.type, this.color);
                }
                score.increaseBy(1);
                invisibleScore.increaseBy(1);
                return new Player(new Posn(this.center.x + step, this.center.y), this.length, this.width, this.type, this.color);
            case "x":
                if (this.center != playerStart) {
                    resets.increaseBy(1);
                    score.increaseBy(-5);
                    invisibleScore.increaseBy(-5);
                    return new Player(playerStart, this.length, this.width, this.type, this.color);
                }
            case "w":
                if (wipesLeft.score > 0) {
                    wipesLeft.increaseBy(-1);
                    score.increaseBy(5);
                    invisibleScore.increaseBy(5);
                    BillGame.bill1 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                    BillGame.bill2 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                    BillGame.bill3 = new Bill(new Posn(billStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                }
            default:
                return new Player(this.center, this.length, this.width, this.type, this.color);
        }
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

}

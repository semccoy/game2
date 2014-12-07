package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Player implements Constants, BoundedObject {

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
                if (atTopBorder()) {
                    score.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x, this.center.y - step), this.length, this.width, this.color);
            case "down":
                if (atBottomBorder()) {
                    score.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x, this.center.y + step), this.length, this.width, this.color);
            case "left":
                if (atLeftBorder()) {
                    score.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x - step, this.center.y), this.length, this.width, this.color);
            case "right":
                if (atRightBorder()) {
                    score.increaseBy(-10);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
                score.increaseBy(1);
                return new Player(new Posn(this.center.x + step, this.center.y), this.length, this.width, this.color);
            case "x":
                if (this.center != playerStart) {
                    score.increaseBy(-5);
                    return new Player(playerStart, this.length, this.width, this.color);
                }
            default:
                return new Player(this.center, this.length, this.width, this.color);
        }
    }

    public boolean atTopBorder() {
        return this.center.y < 140;
    }

    public boolean atBottomBorder() {
        return this.center.y > 660;
    }

    public boolean atLeftBorder() {
        return this.center.x < 140;
    }

    public boolean atRightBorder() {
        return this.center.x > 1300;
    }

}

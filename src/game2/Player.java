package game2;

import static game2.Utilities.*;
import java.awt.*;
import javalib.worldimages.*;

public class Player implements Constants{

    Posn center;
    int length;
    int width;
    String type;
    Color color;

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

    public static void populatePlayerTrail(Player player) {
        Posn newPos = player.center;
        if (trail.size() < maxTrailSize) {
            trail.add(newPos);
        } else {
            for (int i = 0; i < maxTrailSize - 1; i++) {
                trail.set(i, trail.get(i + 1));
            }
            trail.set(maxTrailSize - 1, newPos);
        }
    }

    public Player movePlayer(String key) {
        populatePlayerTrail(this);
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
                    PlayWorld.dot1 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                    PlayWorld.dot2 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                    PlayWorld.dot3 = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), Color.yellow);
                }
            default:
                return new Player(this.center, this.length, this.width, this.type, this.color);
        }
    }
    
    boolean inBounds() {
        return !(hitTopBorder()) && !(hitBottomBorder()) && !(hitLeftBorder()) && !(hitRightBorder());
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

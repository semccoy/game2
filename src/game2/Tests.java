package game2;

import static game2.Utilities.*;
import java.awt.Color;
import java.util.*;
import javalib.worldimages.*;

public class Tests implements Constants {

    static String stringThings = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*()-_=+[{]}|;:',<.>/?";
    Random rand = new Random();

    public static String randomString(int maxLength) {
        int length = new Random().nextInt(maxLength);
        StringBuilder newString = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            newString.append(stringThings.charAt(new Random().nextInt(stringThings.length())));
        }
        return newString.toString();
    }

    public static String randomDirection() {
        // c/o Katherine
        Random rand = new Random();
        int nextValue = rand.nextInt(5);
        if (nextValue == 0) {
            return "up";
        } else if (nextValue == 1) {
            return "down";
        } else if (nextValue == 2) {
            return "right";
        } else if (nextValue == 3) {
            return "left";
        } else if (nextValue == 4) {
            return "x";
        } else {
            int stringVal = rand.nextInt(25);
            return Arrays.toString(Character.toChars(65 + stringVal));
        }
    }

    public static void testPlayerMovement(Player player, String direction) throws RuntimeException {
        int oldX = player.center.x;
        int oldY = player.center.y;
        if (direction.equals("up")) {
            if (!(player.movePlayer("up").center.y == oldY - step)) {
                throw new RuntimeException("moving up failed");
            }
        } else if (direction.equals("down")) {
            if (!(player.movePlayer("down").center.y == oldY + step)) {
                throw new RuntimeException("moving down failed");
            }
        } else if (direction.equals("left")) {
            if (!(player.movePlayer("left").center.x == oldX - step)) {
                throw new RuntimeException("moving left failed");
            }
        } else if (direction.equals("right")) {
            if (!(player.movePlayer("right").center.x == oldX + step)) {
                throw new RuntimeException("moving right failed");
            }
        } else if (direction.equals("x")) {
            if (!(player.movePlayer("x").center.equals(playerStart))) {
                throw new RuntimeException("returning to start with 'x' failed");
            }
        }

    }

    public static void testPlayerMovementEdge(Player player, String direction) throws RuntimeException {
        if (direction.equals("up") & player.hitTopBorder()) {
            if (!(player.movePlayer("up").center.equals(playerStart))) {
                throw new RuntimeException("moving up failed");
            }
        } else if (direction.equals("down") & player.hitBottomBorder()) {
            if (!(player.movePlayer("down").center.equals(playerStart))) {
                throw new RuntimeException("moving down failed");
            }
        } else if (direction.equals("left") & player.hitLeftBorder()) {
            if (!(player.movePlayer("left").center.equals(playerStart))) {
                throw new RuntimeException("moving left failed");
            }
        } else if (direction.equals("right") & player.hitRightBorder()) {
            if (!(player.movePlayer("right").center.equals(playerStart))) {
                throw new RuntimeException("moving right failed");
            }
        } else if (direction.equals("x")) {
            if (!(player.movePlayer("x").center.equals(playerStart))) {
                throw new RuntimeException("returning to start with 'x' failed");
            }
        } else {
            testPlayerMovement(player, direction);
        }
    }

    public static void testDotMovement(Dot dot, Player player) throws RuntimeException {
        int oldX = dot.center.x;
        // dot y-movement is variable and doesn't necessarily happen, so can't test as invariant
        if (!(dot.moveDotTowards(player).center.x == oldX - dot.speed * 5)) {
            throw new RuntimeException("dot x movement failed");
        }
    }

    public static void testPowerupMovement(Powerup powerup, Player player) throws RuntimeException {
        int oldX = powerup.center.x;
        int oldY = powerup.center.y;
        // dot y-movement is variable and doesn't necessarily happen, so can't test as invariant
        if (!(powerup.movePowerupAwayFrom(player).center.x == oldX + powerup.speed * 5 + player.center.x / 200)) {
            throw new RuntimeException("powerup x movement failed");
        }
        if (powerup.center.y > 400) {
            if (!(powerup.movePowerupAwayFrom(player).center.y == oldY - powerup.speed - player.center.y / 200)) {
                throw new RuntimeException("powerup y movement failed (y > 400)");
            }
        } else {
            if (!(powerup.movePowerupAwayFrom(player).center.y == oldY + powerup.speed + player.center.y / 200)) {
                throw new RuntimeException("powerup y movement failed (y <= 400)");
            }
        }

    }


    
    
    
    
    public static void testAllTheThings() {
        for (int i = 0; i < numberOfTests; i++) {

            Posn randomCenter = new Posn(randomInt(120, 1320), randomInt(120, 680));
            int randomSpeed = randomInt(1, 4);
            int randomSize = randomInt(1, 100);
            String randomType = randomString(100);

            randomDirection();

            testPlayerMovement(new Player(playerStart, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testPlayerMovementEdge(new Player(randomCenter, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testDotMovement(new Dot(randomCenter, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Player(randomCenter, randomSize, randomSize, randomType, randomColor()));

            testPowerupMovement(new Powerup(randomCenter, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()),
                    new Player(randomCenter, randomSize, randomSize, randomType, randomColor()));

            /// inbounds check
            // collision check
        }
    }

}

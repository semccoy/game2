package game2;

import static game2.Utilities.*;
import java.awt.Color;
import java.util.*;
import javalib.worldimages.*;

public class Tests implements Constants {

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

    public static void testAllTheThings() {
        for (int i = 0; i < numberOfTests; i++) {

            Posn randomCenter = new Posn(randomInt(120, 1320), randomInt(120, 680));

            randomDirection();
            
            
            
            testPlayerMovement(new Player(playerStart, 40, 40, "normal", playerStartColor), randomDirection());
            testPlayerMovementEdge(new Player(randomCenter, 40, 40, "normal", playerStartColor), randomDirection());
            testDotMovement(new Dot(randomCenter, objectRadius, 0, 0, randomInt(1, 4), Color.yellow),
                    new Player(randomCenter, 40, 40, "normal", playerStartColor));

            /// inbounds check
            // collision check
        }
    }

}

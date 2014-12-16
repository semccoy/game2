package game2;

import static game2.Utilities.*;
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

    public static void testPlayerInBounds(Player player) {
        // if out of bounds, cannot be in bounds
        if (player.hitTopBorder() || player.hitBottomBorder() || player.hitLeftBorder() || player.hitRightBorder()) {
            if ((player.inBounds())) {
                throw new RuntimeException("player in bounds check failed case 1a");
            }
            // and if not out of bounds, must be in bounds 
        } else {
            if (!(player.inBounds())) {
                throw new RuntimeException("player in bounds check failed case 1b");
            }
        }
        // if in bounds, cannot be out of bounds
        if ((player.inBounds())) {
            if (player.hitTopBorder() || player.hitBottomBorder() || player.hitLeftBorder() || player.hitRightBorder()) {
                throw new RuntimeException("player in bounds check failed case 2a");
            }
            // and if not in bounds, must be out of bounds
        } else {
            if (!(player.hitTopBorder() || player.hitBottomBorder() || player.hitLeftBorder() || player.hitRightBorder())) {
                throw new RuntimeException("player in bounds check failed case 2b");
            }
        }
    }

    public static void testDotInBounds(Dot dot) {
        // if out of bounds, cannot be in bounds
        if (dot.hitTopBorder() || dot.hitBottomBorder() || dot.hitLeftBorder()) {
            if ((dot.inBounds())) {
                throw new RuntimeException("dot in bounds check failed case 1a");
            }
            // and if not out of bounds, must be in bounds 
        } else {
            if (!(dot.inBounds())) {
                throw new RuntimeException("dot in bounds check failed case 1b");
            }
        }
        // if in bounds, cannot be out of bounds
        if ((dot.inBounds())) {
            if (dot.hitTopBorder() || dot.hitBottomBorder() || dot.hitLeftBorder()) {
                throw new RuntimeException("dot in bounds check failed case 2a");
            }
            // and if not in bounds, must be out of bounds
        } else {
            if (!(dot.hitTopBorder() || dot.hitBottomBorder() || dot.hitLeftBorder())) {
                throw new RuntimeException("dot in bounds check failed case 2b");
            }
        }
    }

    public static void testPowerupInBounds(Powerup powerup) {
        // if out of bounds, cannot be in bounds
        if (powerup.hitLeftBorder() || powerup.hitRightBorder()) {
            if ((powerup.inBounds())) {
                throw new RuntimeException("powerup in bounds check failed case 1a");
            }
            // and if not out of bounds, must be in bounds 
        } else {
            if (!(powerup.inBounds())) {
                throw new RuntimeException("powerup in bounds check failed case 1b");
            }
        }
        // if in bounds, cannot be out of bounds
        if ((powerup.inBounds())) {
            if (powerup.hitLeftBorder() || powerup.hitRightBorder()) {
                throw new RuntimeException("powerup in bounds check failed case 2a");
            }
            // and if not in bounds, must be out of bounds
        } else {
            if (!(powerup.hitLeftBorder() || powerup.hitRightBorder())) {
                throw new RuntimeException("powerup in bounds check failed case 2b");
            }
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
        if (direction.equals("up") && player.hitTopBorder()) {
            if (!(player.movePlayer("up").center.equals(playerStart))) {
                throw new RuntimeException("moving up failed");
            }
        } else if (direction.equals("down") && player.hitBottomBorder()) {
            if (!(player.movePlayer("down").center.equals(playerStart))) {
                throw new RuntimeException("moving down failed");
            }
        } else if (direction.equals("left") && player.hitLeftBorder()) {
            if (!(player.movePlayer("left").center.equals(playerStart))) {
                throw new RuntimeException("moving left failed");
            }
        } else if (direction.equals("right") && player.hitRightBorder()) {
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

            Posn randomCenterInBounds = new Posn(randomInt(120, 1320), randomInt(120, 680));
            Posn randomCenterAnywhere = new Posn(randomInt(0, 1440), randomInt(0, 800));
            int randomSpeed = randomInt(1, 4);
            int randomSize = randomInt(1, 100);
            String randomType = randomString(100);

            randomDirection();

            testPlayerInBounds(new Player(randomCenterInBounds, randomSize, randomSize, randomType, randomColor()));
            testPlayerInBounds(new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));

            testDotInBounds(new Dot(randomCenterInBounds, randomSize, randomSize, randomSize, randomSpeed, randomColor()));
            testDotInBounds(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()));

            testPowerupInBounds(new Powerup(randomCenterInBounds, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()));
            testPowerupInBounds(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()));

            testPlayerMovement(new Player(playerStart, randomSize, randomSize, randomType, randomColor()), randomDirection());

            testPlayerMovementEdge(new Player(randomCenterInBounds, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testPlayerMovementEdge(new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()), randomDirection());

            testDotMovement(new Dot(randomCenterInBounds, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Player(randomCenterInBounds, randomSize, randomSize, randomType, randomColor()));
            testDotMovement(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));

            testPowerupMovement(new Powerup(randomCenterInBounds, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()),
                    new Player(randomCenterInBounds, randomSize, randomSize, randomType, randomColor()));
            testPowerupMovement(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));

            // collision check
            //powerups work
            //scores work
        }
    }

}

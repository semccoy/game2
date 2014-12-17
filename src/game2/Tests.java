package game2;

import static game2.Utilities.*;
import java.util.*;
import javalib.funworld.*;
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

    public static void testTrailLength(Player player, String direction) {
        player.movePlayer(direction);
        Player.populatePlayerTrail(player);
        if (!(trail.size() <= 5)) {
            throw new RuntimeException("trail length failed");
        }

    }

    public static void testDotPlayerCollision(Dot dot, Player player) {
        // if the two hit, they should be close enough to be touching
        if (dot.hitPlayer(player)) {
            if (!(dot.center.x >= player.center.x - 2 * objectRadius)
                    && (dot.center.x <= player.center.x + 2 * objectRadius)
                    && (dot.center.y >= player.center.y - 2 * objectRadius)
                    && (dot.center.y <= player.center.y + 2 * objectRadius)) {
                throw new RuntimeException("dot-player collision failed case 1");
            }
            // but if the two didn't hit, they shouldn't be touching
        } else {
            if ((dot.center.x >= player.center.x - 2 * objectRadius)
                    && (dot.center.x <= player.center.x + 2 * objectRadius)
                    && (dot.center.y >= player.center.y - 2 * objectRadius)
                    && (dot.center.y <= player.center.y + 2 * objectRadius)) {
                throw new RuntimeException("dot-player collision failed case 2");
            }
        }
    }

    public static void testDotPowerupCollision(Dot dot, Powerup powerup) {
        // if the two hit, they should be close enough to be touching
        if (dot.hitPowerup(powerup)) {
            if (!(dot.center.x >= powerup.center.x - 2 * objectRadius)
                    && (dot.center.x <= powerup.center.x + 2 * objectRadius)
                    && (dot.center.y >= powerup.center.y - 2 * objectRadius)
                    && (dot.center.y <= powerup.center.y + 2 * objectRadius)) {
                throw new RuntimeException("dot-powerup collision failed case 1");
            }
            // but if the two didn't hit, they shouldn't be touching
        } else {
            if ((dot.center.x >= powerup.center.x - 2 * objectRadius)
                    && (dot.center.x <= powerup.center.x + 2 * objectRadius)
                    && (dot.center.y >= powerup.center.y - 2 * objectRadius)
                    && (dot.center.y <= powerup.center.y + 2 * objectRadius)) {
                throw new RuntimeException("dot-powerup collision failed case 2");
            }
        }
    }

    public static void testDotTrailCollision(Dot dot, ArrayList<Posn> trail) {
        for (int i = 0; i < 5; i++) {
            trail.add(new Posn(randomInt(120, 1320), randomInt(120, 680)));
        }
        // if the two hit, they should be close enough to be touching
        if (dot.hitTrail(trail)) {
            if (!(dot.center.x >= trail.get(0).x - 2 * objectRadius)
                    && (dot.center.x <= trail.get(0).x + 2 * objectRadius)
                    && (dot.center.y >= trail.get(0).y - 2 * objectRadius)
                    && (dot.center.y <= trail.get(0).y + 2 * objectRadius)) {
                throw new RuntimeException("dot-trail collision failed case 1");
            }
            // but if the two didn't hit, they shouldn't be touching
        } else {
            if ((dot.center.x >= trail.get(0).x - 2 * objectRadius)
                    && (dot.center.x <= trail.get(0).x + 2 * objectRadius)
                    && (dot.center.y >= trail.get(0).y - 2 * objectRadius)
                    && (dot.center.y <= trail.get(0).y + 2 * objectRadius)) {
                throw new RuntimeException("dot-trail collision failed case 2");
            }
        }
    }

    public static void testPowerupPlayerCollision(Powerup powerup, Player player) {
        // if the two hit, they should be close enough to be touching
        if (powerup.hitPlayer(player)) {
            if (!(powerup.center.x >= player.center.x - 2 * objectRadius)
                    && (powerup.center.x <= player.center.x + 2 * objectRadius)
                    && (powerup.center.y >= player.center.y - 2 * objectRadius)
                    && (powerup.center.y <= player.center.y + 2 * objectRadius)) {
                throw new RuntimeException("powerup-player collision failed case 1");
            }
            // but if the two didn't hit, they shouldn't be touching
        } else {
            if ((powerup.center.x >= player.center.x - 2 * objectRadius)
                    && (powerup.center.x <= player.center.x + 2 * objectRadius)
                    && (powerup.center.y >= player.center.y - 2 * objectRadius)
                    && (powerup.center.y <= player.center.y + 2 * objectRadius)) {
                throw new RuntimeException("powerup-player collision failed case 2");
            }
        }
    }

    public static void testPowerupChangesPlayerType(Powerup powerup, Player player) {
        if (powerup.hitPlayer(player)) {
            if (!powerup.type.equals(player.type)) {
                throw new RuntimeException("powerup changes player type failed");
            }
        }
    }

    public static void testTrailWhipPowerup(Powerup powerup, Player player, Dot dot, ArrayList<Posn> testTrail) {
        // code borrowed from onTick() method of PlayWorld class (didn't have its own method)
        for (int i = 0; i < 5; i++) {
            testTrail.add(new Posn(randomInt(120, 1320), randomInt(120, 680)));
        }
        if (powerup.hitPlayer(player)) {
            player.type = powerup.type;
            if (player.type.equals(powerupTypes[0])) {
                if (dot.hitTrail(testTrail)) {
                    dot = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), randomColor());
                    if (!(dot.center.x == dotStartX)) {
                        throw new RuntimeException("trailwhip powerup failed");
                    }
                }
            }
        }
    }

    public static void testWiperPowerup(Powerup powerup, Player player, Dot dot) {
        // code borrowed from onTick() method of PlayWorld class and
        // movePlayer() method of Player class (didn't have its own method)
        if (powerup.hitPlayer(player)) {
            player.type = powerup.type;
            if (player.type.equals(powerupTypes[1])) {
                player.type = "normal";
                wipesLeft.increaseBy(1);
            }
            player.movePlayer("w");
            if (!(wipesLeft.score == 0)) {
                throw new RuntimeException("wiper powerup test case decrementing not working");
            }
            if (player.type.equals("normal")) {
                if (wipesLeft.score == 0) {
                    player.color = playerStartColor;
                }
            }
            if (!(player.color == playerStartColor)) {
                throw new RuntimeException("wiper powerup test case player recoloring not working");
            }

            dot = new Dot(new Posn(dotStartX, randomInt(120, 680)), objectRadius, 0, 0, randomInt(1, speedo), randomColor());
            if (!(dot.center.x == dotStartX)) {
                throw new RuntimeException("wiper powerup test case dot resetting not working");
            }
        }
    }

    public static void testSlowTimePowerup(Powerup powerup, Player player, Dot dot) {
        // code borrowed from onTick() method of PlayWorld class (didn't have its own method)
        if (powerup.hitPlayer(player)) {
            player.type = powerup.type;
            if (player.type.equals(powerupTypes[2])) {
                dot.speed = powerup.speed = 1;
            }
            if (!(powerup.speed == 1) && (!(dot.speed == 1))) {
                throw new RuntimeException("slowtime powerup failed");
            }
        }
    }

    public static void testScoreIncrementing(Score testScore) {
        int oldScore = testScore.score;
        int randomInt = randomInt(-1000, 1000);
        testScore.increaseBy(randomInt);
        if (!(testScore.score == oldScore + randomInt)) {
            throw new RuntimeException("score incrementing failed");
        }
    }

    public static void testWorldChange(World testWorld) {
        playOnHuh.score = 1;
        invisibleScore.increaseBy(1);
        if (testWorld.onKeyEvent("g") instanceof PlayWorld) {
            if (invisibleScore.score > 100) {
                throw new RuntimeException("world hop from playworld failed");
            }
        } else if (testWorld.onKeyEvent("b") instanceof PauseWorld) {
            if (invisibleScore.score > 100) {
                if (!(playOnHuh.score == 0)) {
                    throw new RuntimeException("world hop from playworld failed");
                }
            }
        }
        testWorld.onKeyEvent("l");
        if (!(playOnHuh.score == 0)) {
            throw new RuntimeException("world hop from playworld failed");
        }
    }

    public static void testAllTheThings() {
        for (int i = 0; i < numberOfTests; i++) {

            Posn randomCenterInBounds = new Posn(randomInt(120, 1320), randomInt(120, 680));
            Posn randomCenterAnywhere = new Posn(randomInt(0, 1440), randomInt(0, 800));
            int randomSpeed = randomInt(0, 4);
            int randomSize = randomInt(0, 100);
            int randomScore = randomInt(-10000, 10000);
            String randomType = randomString(100);

            randomDirection();
            testPlayerInBounds(new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));
            testDotInBounds(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()));
            testPowerupInBounds(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()));
            testPlayerMovement(new Player(playerStart, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testPlayerMovementEdge(new Player(randomCenterInBounds, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testPlayerMovementEdge(new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testDotMovement(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));
            testPowerupMovement(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));
            testTrailLength(new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()), randomDirection());
            testDotPlayerCollision(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));
            testDotPowerupCollision(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()));
            testDotTrailCollision(new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new ArrayList<Posn>());
            testPowerupPlayerCollision(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));
            testPowerupChangesPlayerType(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()));
            testTrailWhipPowerup(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, powerupTypes[0], randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()),
                    new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new ArrayList<Posn>());
            testWiperPowerup(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, powerupTypes[1], randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()),
                    new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()));
            testSlowTimePowerup(new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, powerupTypes[2], randomColor()),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()),
                    new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()));
            testScoreIncrementing(new Score());
            testScoreIncrementing(new Score(randomScore));
            testWorldChange(new PlayWorld(new Game2(universe),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor()),
                    new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Dot(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSpeed, randomColor()),
                    new Powerup(randomCenterAnywhere, randomSize, randomSize, randomSize, randomSize, randomType, randomColor())));
            testWorldChange(new PauseWorld(new Game2(universe),
                    new Player(randomCenterAnywhere, randomSize, randomSize, randomType, randomColor())));
        }
    }
}

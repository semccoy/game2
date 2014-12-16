package game2;

import java.awt.*;
import java.util.Random;
import javalib.worldimages.*;

public class Utilities implements Constants {
    
    static String stringThings = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890`~!@#$%^&*()-_=+[{]}|;:',<.>/?";

    public static int randomInt(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static Color randomColor() {
        return new Color(randomInt(0, 255), randomInt(0, 255), randomInt(0, 255));
    }

    public static String randomString(int maxLength) {
        int length = new Random().nextInt(maxLength);
        StringBuilder newString = new StringBuilder("");
        for (int i = 0; i < length; i++) {
            newString.append(stringThings.charAt(new Random().nextInt(stringThings.length())));
        }
        return newString.toString();
    }

    public static WorldImage showScore() {
        Color color = Color.white;
        return new OverlayImages(new TextImage(new Posn(WIDTH / 2, 40), "score", 20, color),
                new TextImage(new Posn(WIDTH / 2, 65), score.print(), 20, color));
    }

    public static WorldImage showStats() {
        Color color = Color.white;
        return new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 95 + 79, 25), "dots spawned: ", 15, color),
                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 80, 25), dotSpawns.print(), 15, color),
                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 100 + 80, 45), "times whacked: ", 15, color),
                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 80, 45), whacks.print(), 15, color),
                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 115 + 80, 65), "worlds fallen off of: ", 15, color),
                                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 80, 65), falls.print(), 15, color),
                                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 - 101 + 80, 85), "resets resetted: ", 15, color),
                                                                new TextImage(new Posn(WIDTH / 5 + 80, 85), resets.print(), 15, color))))))));
    }

    public static WorldImage showPowerups() {
        Color color = Color.white;
        return new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 125 + 80, 25), "powerups spawned: ", 15, color),
                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 230 + 80, 25), powerupSpawns.print(), 15, color),
                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 133 + 80, 45), "powerups gotten: ", 15, color),
                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 230 + 80, 45), powerupsGotten.print(), 15, color),
                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 158 + 70, 65), "pokes poked: ", 15, color),
                                                new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 230 + 80, 65), pokes.print(), 15, color),
                                                        new OverlayImages(new TextImage(new Posn(WIDTH / 5 + 160 + 80, 85), "wipes left: ", 15, color),
                                                                new TextImage(new Posn(WIDTH / 5 + 230 + 80, 85), wipesLeft.print(), 15, color))))))));
    }

}

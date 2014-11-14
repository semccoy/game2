package game2;

import java.awt.*;
import java.util.Random;

public class Utilities implements Constants {

    public static int randomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static Color randomColor() {
        int r = randomInt(0, 255);
        int g = randomInt(0, 255);
        int b = randomInt(0, 255);
        return new Color(r, g, b);
    }

}

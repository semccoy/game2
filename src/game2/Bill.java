package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Bill implements Constants {

    Posn center;
    int radius;
    Color color;

    Bill(Posn center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    WorldImage billImage() {
        return new DiskImage(this.center, this.radius, this.color);
    }

    public WorldImage placeBill() {
        return this.billImage();
    }
    
}
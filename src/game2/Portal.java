package game2;

import java.awt.*;
import javalib.worldimages.*;

public class Portal implements Constants {

    Posn center;
    int radius;
    Color color;

    Portal(Posn center, int radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    WorldImage portalImage() {
        return new DiskImage(this.center, this.radius, this.color);
    }

    public WorldImage placePortal() {
        return this.portalImage();
    }
    
}
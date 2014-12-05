package game2;

import java.awt.Color;
import java.util.ArrayList;
import javalib.worldimages.*;

public class Trail implements Constants {

    ArrayList<Posn> trail = new ArrayList<Posn>(maxTrailSize);
    WorldImage newTrail = new RectangleImage(new Posn(0,0), 40, 40, Color.black);

    public Trail() {
    }

    public Trail(ArrayList<Posn> trail) {
        this.trail = trail;
    }

//    public void addToTrail(Posn newPos) {
//        if (this.trail.size() < maxTrailSize) {
//            this.trail.add(newPos);
//        } else {
//            for (int i = 0; i < maxTrailSize - 1; i++) {
//                this.trail.set(i, this.trail.get(i + 1));
//            }
//            this.trail.set(maxTrailSize - 1, newPos);
//        }
//        System.out.println("trail size: " + this.trail.size());
//    }

    

    public WorldImage trailImage() {
        for (int i = 0; i < trail.size(); i++) {
            RectangleImage temp = new RectangleImage(trail.get(i), 40, 40, Color.green);
            newTrail = new OverlayImages(newTrail, temp);
        }
        return newTrail;
    }
}

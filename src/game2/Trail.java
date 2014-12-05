package game2;

import java.util.ArrayList;
import javalib.worldimages.*;

public class Trail implements Constants {

    ArrayList<Posn> trail = new ArrayList<Posn>(maxTrailSize);

    public Trail() {
    }

    public Trail(ArrayList<Posn> trail) {
        this.trail = trail;
    }

    public void addToTrail(Posn newPos) {
        
        if (this.trail.size() < maxTrailSize) {
            this.trail.add(newPos);
        } else {
            for (int i = 0; i < maxTrailSize-1; i++) {
                this.trail.set(i, this.trail.get(i + 1));
            }
            this.trail.set(maxTrailSize-1, newPos);
        }
        System.out.println("trail size: " + this.trail.size());
    }
}

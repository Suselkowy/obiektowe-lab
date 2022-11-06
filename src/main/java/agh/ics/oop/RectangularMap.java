package agh.ics.oop;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{


    public RectangularMap(int width, int height){
        super(width, height);
    }

    @Override
    public Vector2d[] printLimit() {
        return new Vector2d[]{lowerLeft, upperRight};
    }


}

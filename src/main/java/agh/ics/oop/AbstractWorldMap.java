package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected final Vector2d lowerLeft;
    protected final Vector2d upperRight;

    protected List<IMapElement> elements;
    protected MapVisualizer mapVisualizer;

    public AbstractWorldMap(int width, int height){
        this.elements = new LinkedList<IMapElement>();
        this.mapVisualizer = new MapVisualizer(this);

        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.precedes(position) && upperRight.follows(position) && (!isOccupied(position) || objectAt(position) instanceof Grass);
    }

    @Override
    public boolean place(Animal animal) {
        if(!canMoveTo(animal.getPosition())){
            return false;
        }
        elements.add(0, animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (IMapElement curElement: elements){
            if (curElement.isAt(position)){
                return true;
            };
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (IMapElement curElement: elements){
            if (curElement.isAt(position)){
                return curElement;
            };
        }
        return null;
    }

    public abstract Vector2d[] printLimit();

    @Override
    public String toString() {
        Vector2d[] limits = printLimit();
        return mapVisualizer.draw(limits[0], limits[1]);
    }

}



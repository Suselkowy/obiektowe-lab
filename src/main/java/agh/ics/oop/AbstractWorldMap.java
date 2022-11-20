package agh.ics.oop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected final Vector2d lowerLeft;
    protected final Vector2d upperRight;

    Map<Vector2d, IMapElement> elements;
//    protected List<IMapElement> elements;
    protected MapVisualizer mapVisualizer;

    public AbstractWorldMap(int width, int height){
//        this.elements = new LinkedList<IMapElement>();
        this.elements = new HashMap<>();
        this.mapVisualizer = new MapVisualizer(this);

        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(width,height);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return lowerLeft.precedes(position) && upperRight.follows(position) && (!isOccupied(position) || objectAt(position) instanceof Grass);
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        if(!canMoveTo(animal.getPosition())){
            String errorCause = isOccupied(animal.getPosition()) ? "- field already occupied" : "- out of bounds";
            throw new IllegalArgumentException("Cannot place animal on place " +animal.getPosition().toString() +errorCause);
        }

        elements.put(animal.getPosition(), animal);
        return true;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(elements.get(position) != null){
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return elements.get(position);
    }

    public abstract Vector2d[] printLimit();

    @Override
    public String toString() {
        Vector2d[] limits = printLimit();
        return mapVisualizer.draw(limits[0], limits[1]);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement curElem = elements.get(oldPosition);
        elements.remove(oldPosition, curElem);
        if(elements.get(newPosition) != null){
            elements.remove(newPosition);
        }
        elements.put(newPosition, curElem);
    }



}



package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    TreeSet<Vector2d> xCoords;
    TreeSet<Vector2d> yCoords;
    public MapBoundary(){
        xCoords = new TreeSet<Vector2d>(new Comparator<Vector2d>(){
            @Override
            public int compare(Vector2d t1, Vector2d t2) {
                if (t1.x == t2.x){
                    if(t1.y > t2.y){
                        return 1;
                    }
                    if(t1.y == t2.y){
                        return 0;
                    }
                }else{
                    if(t1.x > t2.x){
                        return 1;
                    }
                }
                return -1;
            }
        });
        yCoords = new TreeSet<Vector2d>(new Comparator<Vector2d>(){
            @Override
            public int compare(Vector2d t1, Vector2d t2) {
                if (t1.y == t2.y){
                    if(t1.x > t2.x){
                        return 1;
                    }
                    if(t1.x == t2.x){
                        return 0;
                    }
                }else{
                    if(t1.y > t2.y){
                        return 1;
                    }
                }
                return -1;
            }
        });
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        xCoords.remove(newPosition);
        xCoords.remove(oldPosition);
        xCoords.add(newPosition);

        yCoords.remove(newPosition);
        yCoords.remove(oldPosition);
        yCoords.add(newPosition);
    }

    public void addPosition(Vector2d position){
        xCoords.add(position);
        yCoords.add(position);
    }

}

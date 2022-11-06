package agh.ics.oop;

public class Grass implements IMapElement{

    private Vector2d position;
    public Grass(Vector2d initialPosition){
        this.position = initialPosition;
    }

    public Vector2d getPosition(){
        return position;
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    @Override
    public String toString() {
        return "*";
    }
}



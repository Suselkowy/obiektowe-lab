package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    private final Vector2d topLeft = new Vector2d(4,4);
    private final Vector2d bottomRight = new Vector2d(0,0 );

    public String toString() {
        return String.format("Zwierzak znajduje sie na pozycji %s i jest zwrócony na %s",
                position.toString(), orientation.toString());
    }

    public boolean isAt(Vector2d position){
        return position.equals(this.position);
    }

    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();

            //default handles forward and backward
            default -> {
                Vector2d newPosition = (direction.equals(MoveDirection.FORWARD)) ?
                        position.add(orientation.toUnitVector()) : position.add(orientation.toUnitVector().opposite());
                if (bottomRight.precedes(newPosition) && topLeft.follows(newPosition))
                    position = newPosition;
            }
        }
    }
}

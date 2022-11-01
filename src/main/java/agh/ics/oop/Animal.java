package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    private final Vector2d topLeft = new Vector2d(4,4);
    private final Vector2d bottomRight = new Vector2d(0,0 );

    private final IWorldMap map;
    public Animal(IWorldMap map){
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }

    public String toString() {
        return switch(orientation){
            case NORTH -> "N";
            case WEST -> "W";
            case EAST -> "E";
            case SOUTH -> "S";
        };
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
                if (map.canMoveTo(newPosition))
                    position = newPosition;
            }
        }
    }

    public Vector2d getPosition(){
        return this.position;
    }
}

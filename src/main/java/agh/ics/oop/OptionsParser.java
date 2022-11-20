package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    public MoveDirection[] parse(String[] stringDirections) throws IllegalArgumentException{
        MoveDirection[] moveDirections= new MoveDirection[stringDirections.length];
        int i = 0;
        for (String direction: stringDirections) {
            switch (direction) {
                case "f" -> moveDirections[i] = MoveDirection.FORWARD;
                case "b" -> moveDirections[i] = MoveDirection.BACKWARD;
                case "r" -> moveDirections[i] = MoveDirection.RIGHT;
                case "l" -> moveDirections[i] = MoveDirection.LEFT;
                case "forward" -> moveDirections[i] = MoveDirection.FORWARD;
                case "backward" -> moveDirections[i] = MoveDirection.BACKWARD;
                case "right" -> moveDirections[i] = MoveDirection.RIGHT;
                case "left" -> moveDirections[i] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(direction + " is not legal move specification");
            }
            ++i;
        }
        return Arrays.copyOfRange(moveDirections, 0, i);
    }

}

package agh.ics.oop;

import java.util.Arrays;

public class OptionsParser {
    static public MoveDirection[] parse(String[] stringDirections){
        MoveDirection[] moveDirections= new MoveDirection[stringDirections.length];
        int i = 0;
        for (String direction: stringDirections) {
            switch (direction) {
                case "f" -> moveDirections[i] = MoveDirection.FORWARD;
                case "b" -> moveDirections[i] = MoveDirection.BACKWARD;
                case "r" -> moveDirections[i] = MoveDirection.RIGHT;
                case "l" -> moveDirections[i] = MoveDirection.LEFT;
                default -> --i;
            }
            ++i;
        }
        return Arrays.copyOfRange(moveDirections, 0, i);
    }

}

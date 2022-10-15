package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MapDirectionTest {

    @Test
    public void givenMapDirection_whenNext_thenReturnNextMapDirection(){
        MapDirection[] input = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        MapDirection[] expectedOutput = {MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST,MapDirection.NORTH};
        MapDirection[] actualOutput = new MapDirection[4];

        for (int i = 0; i < input.length; i++) {
            actualOutput[i] = input[i].next();
        }
        assertArrayEquals(expectedOutput, actualOutput);

    }

    @Test
    public void givenMapDirection_whenPrevious_thenReturnPreviousMapDirection(){
        MapDirection[] input = {MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH, MapDirection.WEST};
        MapDirection[] expectedOutput = {MapDirection.WEST, MapDirection.NORTH, MapDirection.EAST, MapDirection.SOUTH};
        MapDirection[] actualOutput = new MapDirection[4];

        for (int i = 0; i < input.length; i++) {
            actualOutput[i] = input[i].previous();
        }
        assertArrayEquals(expectedOutput, actualOutput);

    }
}

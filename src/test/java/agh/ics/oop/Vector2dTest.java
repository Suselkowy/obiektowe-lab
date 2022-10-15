package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void givenVector2d_whenEquals_thenReturnIfEqual(){
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(1,2);
        assertEquals(v1, v1);
        assertEquals(v1, v2);
    }

    @Test
    public void givenVector2d_whenToString_thenReturnString(){
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-1,-2);
        assertEquals(v1.toString(), "(1,2)");
        assertEquals(v2.toString(), "(-1,-2)");
    }

    @Test
    public void testPrecedes() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(0,1);
        Vector2d v4 = new Vector2d(1, 10);
        assertTrue(v1.precedes(v1));
        assertTrue(v1.precedes(v2));
        assertFalse(v1.precedes(v3));
        assertTrue(v1.precedes(v4));
    }

    @Test
    public void testFollows() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(0,1);
        Vector2d v4 = new Vector2d(1, 10);
        assertTrue(v1.follows(v1));
        assertFalse(v1.follows(v2));
        assertTrue(v1.follows(v3));
        assertFalse(v1.follows(v4));
    }

    @Test
    public void testUpperRight() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(0,1);
        Vector2d v4 = new Vector2d(1, -10);
        assertEquals(v1.upperRight(v2), new Vector2d(2,3));
        assertEquals(v3.upperRight(v4), new Vector2d(1, 1));
    }

    @Test
    public void testLowerRight() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(0,1);
        Vector2d v4 = new Vector2d(1, -10);
        assertEquals(v1.lowerLeft(v2), new Vector2d(1,2));
        assertEquals(v3.lowerLeft(v4), new Vector2d(0, -10));
    }

    @Test
    public void testAdd() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(0,1);
        Vector2d v4 = new Vector2d(1, -10);
        assertEquals(v1.add(v2), new Vector2d(3,5));
        assertEquals(v3.add(v4), new Vector2d(1, -9));
    }

    @Test
    public void testSubtract() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(2,3);
        Vector2d v3 = new Vector2d(0,1);
        Vector2d v4 = new Vector2d(1, -10);
        assertEquals(v1.subtract(v2), new Vector2d(-1,-1));
        assertEquals(v3.subtract(v4), new Vector2d(-1, 11));
    }

    @Test
    public void testOpposite() {
        Vector2d v1 = new Vector2d(1,2);
        Vector2d v2 = new Vector2d(-1,-11);
        Vector2d v3 = new Vector2d(0,0);
        assertEquals(v1.opposite(), new Vector2d(-1,-2));
        assertEquals(v2.opposite(), new Vector2d(1, 11));
        assertEquals(v3.opposite(), new Vector2d(0,0));
    }

}

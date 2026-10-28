package sk.upjs.paz1a.tutoring.recap.exercise07;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    private static final double DELTA = 1e-9;

    @Test
    void constructorStoresBothSides() {
        Rectangle rectangle = new Rectangle(3.0, 4.5);
        assertEquals(3.0, rectangle.getWidth(), DELTA);
        assertEquals(4.5, rectangle.getHeight(), DELTA);
    }

    @Test
    void areaIsWidthTimesHeight() {
        assertEquals(12.0, new Rectangle(3, 4).area(), DELTA);
    }

    @Test
    void perimeterIsTheLengthOfTheBorder() {
        assertEquals(14.0, new Rectangle(3, 4).perimeter(), DELTA);
    }

    @Test
    void unitSquare() {
        Rectangle unit = new Rectangle(1, 1);
        assertEquals(1.0, unit.area(), DELTA);
        assertEquals(4.0, unit.perimeter(), DELTA);
        assertTrue(unit.isSquare());
    }

    @Test
    void equalSidesMakeASquare() {
        assertTrue(new Rectangle(5, 5).isSquare());
    }

    @Test
    void differentSidesAreNotASquare() {
        assertFalse(new Rectangle(5, 6).isSquare());
    }

    @Test
    void settersChangeTheRectangle() {
        Rectangle rectangle = new Rectangle(3, 4);

        rectangle.setWidth(10);
        assertEquals(10.0, rectangle.getWidth(), DELTA);
        assertEquals(40.0, rectangle.area(), DELTA, "Compute the area from the fields - don't store it");

        rectangle.setHeight(2);
        assertEquals(2.0, rectangle.getHeight(), DELTA);
        assertEquals(24.0, rectangle.perimeter(), DELTA);
    }

    @Test
    void toStringShowsBothSides() {
        assertEquals("Rectangle 3.0 x 4.5", new Rectangle(3, 4.5).toString());
    }

    @Test
    void toStringShowsTheCurrentSides() {
        Rectangle rectangle = new Rectangle(3, 4.5);
        rectangle.setWidth(2);
        assertEquals("Rectangle 2.0 x 4.5", rectangle.toString());
    }

    @Test
    void constructorRejectsZeroOrNegativeSides() {
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(1, 0));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-2, 3));
    }

    @Test
    void settersRejectInvalidValuesAndKeepTheOldOnes() {
        Rectangle rectangle = new Rectangle(3, 4);
        assertThrows(IllegalArgumentException.class, () -> rectangle.setWidth(0));
        assertThrows(IllegalArgumentException.class, () -> rectangle.setHeight(-1));
        assertEquals(3.0, rectangle.getWidth(), DELTA);
        assertEquals(4.0, rectangle.getHeight(), DELTA);
    }

    @Test
    void allInstanceFieldsArePrivate() {
        for (Field field : Rectangle.class.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue; // constants and tool-generated fields are not part of a rectangle's state
            }
            assertTrue(Modifier.isPrivate(field.getModifiers()),
                    "Field '" + field.getName() + "' must be private");
        }
    }
}

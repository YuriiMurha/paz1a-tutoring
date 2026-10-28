package sk.upjs.paz1a.tutoring.recap.exercise08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointToolsTest {

    // ---------- read this test first - it already passes ----------

    @Test
    void javaPassesACopyOfTheReference() {
        Point point = new Point(1, 2);

        replaceWithOrigin(point); // assigns a new object to the method's OWN copy of the reference...
        assertEquals(1, point.getX(), "...so our variable still points to the old point");

        moveToOrigin(point); // changes the object that both references point to...
        assertEquals(0, point.getX(), "...so we see the change");
    }

    private static void replaceWithOrigin(Point point) {
        point = new Point(0, 0);
    }

    private static void moveToOrigin(Point point) {
        point.moveTo(0, 0);
    }

    // ---------- Point ----------

    @Test
    void moveByChangesBothCoordinates() {
        Point point = new Point(1, 2);
        point.moveBy(3, -5);
        assertEquals(4, point.getX());
        assertEquals(-3, point.getY());
    }

    @Test
    void copyIsANewPointAtTheSamePosition() {
        Point point = new Point(1, 2);
        Point copy = point.copy();
        assertNotNull(copy);
        assertNotSame(point, copy, "copy() must create a new object");
        assertEquals(1, copy.getX());
        assertEquals(2, copy.getY());
    }

    @Test
    void movingTheCopyDoesNotMoveTheOriginal() {
        Point point = new Point(1, 2);
        Point copy = point.copy();
        assertNotNull(copy);
        copy.moveTo(9, 9);
        assertEquals(1, point.getX());
        assertEquals(2, point.getY());
    }

    @Test
    void samePositionIsNotTheSameObject() {
        Point a = new Point(3, 4);
        Point b = new Point(3, 4);
        assertNotSame(a, b); // a == b is false: these are two different objects...
        assertTrue(a.hasSameCoordinates(b), "...but they are at the same position");
    }

    @Test
    void differentPositions() {
        assertFalse(new Point(3, 4).hasSameCoordinates(new Point(4, 3)));
    }

    @Test
    void hasSameCoordinatesRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Point(0, 0).hasSameCoordinates(null));
    }

    // ---------- PointTools.moveAll ----------

    @Test
    void moveAllMovesThePointsTheCallerHolds() {
        Point a = new Point(0, 0);
        Point b = new Point(5, 5);
        Point[] points = {a, b};

        PointTools.moveAll(points, 1, 2);

        assertEquals(1, a.getX()); // a and points[0] refer to the same object
        assertEquals(2, a.getY());
        assertEquals(6, b.getX());
        assertEquals(7, b.getY());
    }

    @Test
    void moveAllOnEmptyArrayDoesNothing() {
        assertDoesNotThrow(() -> PointTools.moveAll(new Point[0], 1, 1));
    }

    @Test
    void moveAllRejectsNullArrayOrNullPoint() {
        assertThrows(IllegalArgumentException.class, () -> PointTools.moveAll(null, 1, 1));
        assertThrows(IllegalArgumentException.class,
                () -> PointTools.moveAll(new Point[]{new Point(0, 0), null}, 1, 1));
    }

    // ---------- PointTools.shallowCopy ----------

    @Test
    void shallowCopyIsANewArrayWithTheSamePoints() {
        Point[] points = {new Point(1, 1), new Point(2, 2)};
        Point[] copy = PointTools.shallowCopy(points);
        assertNotNull(copy);
        assertNotSame(points, copy, "Create a new array");
        assertEquals(2, copy.length);
        assertSame(points[0], copy[0]);
        assertSame(points[1], copy[1]);
    }

    @Test
    void shallowCopySharesThePoints() {
        Point[] points = {new Point(1, 1)};
        Point[] copy = PointTools.shallowCopy(points);
        assertNotNull(copy);
        points[0].moveTo(10, 10);
        assertEquals(10, copy[0].getX(), "A shallow copy shares the Point objects");
    }

    @Test
    void shallowCopyArrayItselfIsIndependent() {
        Point[] points = {new Point(1, 1)};
        Point[] copy = PointTools.shallowCopy(points);
        assertNotNull(copy);
        copy[0] = new Point(9, 9); // replaces the reference in the copy only
        assertEquals(1, points[0].getX());
    }

    @Test
    void shallowCopyRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> PointTools.shallowCopy(null));
    }

    // ---------- PointTools.deepCopy ----------

    @Test
    void deepCopyHasNewPointsAtTheSamePositions() {
        Point[] points = {new Point(1, 2), new Point(3, 4)};
        Point[] copy = PointTools.deepCopy(points);
        assertNotNull(copy);
        assertNotSame(points, copy);
        assertEquals(2, copy.length);
        for (int i = 0; i < points.length; i++) {
            assertNotSame(points[i], copy[i], "Point " + i + " is shared - copy every point");
            assertEquals(points[i].getX(), copy[i].getX());
            assertEquals(points[i].getY(), copy[i].getY());
        }
    }

    @Test
    void movingAnOriginalPointDoesNotMoveItsDeepCopy() {
        Point[] points = {new Point(1, 2)};
        Point[] copy = PointTools.deepCopy(points);
        assertNotNull(copy);
        points[0].moveTo(10, 10);
        assertEquals(1, copy[0].getX());
        assertEquals(2, copy[0].getY());
    }

    @Test
    void deepCopyRejectsNullArrayOrNullPoint() {
        assertThrows(IllegalArgumentException.class, () -> PointTools.deepCopy(null));
        assertThrows(IllegalArgumentException.class, () -> PointTools.deepCopy(new Point[]{null}));
    }

    // ---------- PointTools.swapCoordinates ----------

    @Test
    void swapCoordinatesSwapsThePositions() {
        Point a = new Point(1, 2);
        Point b = new Point(3, 4);

        PointTools.swapCoordinates(a, b);

        assertEquals(3, a.getX());
        assertEquals(4, a.getY());
        assertEquals(1, b.getX());
        assertEquals(2, b.getY());
    }

    @Test
    void swapCoordinatesRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> PointTools.swapCoordinates(null, new Point(1, 1)));
        assertThrows(IllegalArgumentException.class, () -> PointTools.swapCoordinates(new Point(1, 1), null));
    }
}

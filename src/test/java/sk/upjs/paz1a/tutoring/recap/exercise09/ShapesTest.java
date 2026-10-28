package sk.upjs.paz1a.tutoring.recap.exercise09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapesTest {

    private static final double DELTA = 1e-9;

    // ---------- Circle ----------

    @Test
    void circleArea() {
        assertEquals(Math.PI * 4, new Circle(2).area(), DELTA);
    }

    @Test
    void circlePerimeter() {
        assertEquals(Math.PI * 4, new Circle(2).perimeter(), DELTA);
    }

    @Test
    void circleRejectsZeroOrNegativeRadius() {
        assertThrows(IllegalArgumentException.class, () -> new Circle(0));
        assertThrows(IllegalArgumentException.class, () -> new Circle(-1));
    }

    // ---------- Square ----------

    @Test
    void squareAreaAndPerimeter() {
        Square square = new Square(3);
        assertEquals(9.0, square.area(), DELTA);
        assertEquals(12.0, square.perimeter(), DELTA);
    }

    @Test
    void squareRejectsZeroOrNegativeSide() {
        assertThrows(IllegalArgumentException.class, () -> new Square(0));
        assertThrows(IllegalArgumentException.class, () -> new Square(-3));
    }

    // ---------- RightTriangle ----------

    @Test
    void rightTriangleArea() {
        assertEquals(6.0, new RightTriangle(3, 4).area(), DELTA);
    }

    @Test
    void rightTrianglePerimeterIncludesTheHypotenuse() {
        assertEquals(12.0, new RightTriangle(3, 4).perimeter(), DELTA, "3 + 4 + 5: the third side is the hypotenuse");
    }

    @Test
    void rightTriangleRejectsZeroOrNegativeLegs() {
        assertThrows(IllegalArgumentException.class, () -> new RightTriangle(0, 4));
        assertThrows(IllegalArgumentException.class, () -> new RightTriangle(3, -1));
    }

    // ---------- isLargerThan (written once, in Shape) ----------

    @Test
    void largerAreaIsLarger() {
        Shape square = new Square(3); // area 9
        Shape circle = new Circle(1); // area 3.14...
        assertTrue(square.isLargerThan(circle));
        assertFalse(circle.isLargerThan(square));
    }

    @Test
    void equalAreasAreNotLarger() {
        Shape square = new Square(2);             // area 4
        Shape triangle = new RightTriangle(2, 4); // area 4
        assertFalse(square.isLargerThan(triangle));
        assertFalse(triangle.isLargerThan(square));
    }

    @Test
    void isLargerThanRejectsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Square(1).isLargerThan(null));
    }

    // ---------- dynamic dispatch ----------

    @Test
    void eachShapeComputesItsOwnArea() {
        Shape[] shapes = {new Circle(1), new Square(2), new RightTriangle(3, 4)};
        double[] expected = {Math.PI, 4.0, 6.0};
        for (int i = 0; i < shapes.length; i++) {
            // the variable type is Shape, but Java runs the area() of the actual object
            assertEquals(expected[i], shapes[i].area(), DELTA, shapes[i].getName());
        }
    }

    // ---------- ShapeTools ----------

    @Test
    void totalAreaAddsUpAllShapes() {
        Shape[] shapes = {new Square(2), new RightTriangle(3, 4)};
        assertEquals(10.0, ShapeTools.totalArea(shapes), DELTA);
    }

    @Test
    void totalAreaOfEmptyArrayIsZero() {
        assertEquals(0.0, ShapeTools.totalArea(new Shape[0]), DELTA);
    }

    @Test
    void findLargestReturnsTheShapeWithTheLargestArea() {
        Shape largest = new Square(5);
        Shape[] shapes = {new Circle(1), largest, new RightTriangle(3, 4)};
        assertSame(largest, ShapeTools.findLargest(shapes));
    }

    @Test
    void findLargestPrefersTheFirstOnATie() {
        Shape square = new Square(2);
        Shape[] shapes = {square, new RightTriangle(2, 4)};
        assertSame(square, ShapeTools.findLargest(shapes));
    }

    @Test
    void findLargestOfEmptyArrayIsNull() {
        assertNull(ShapeTools.findLargest(new Shape[0]));
    }

    @Test
    void listNamesInOrder() {
        Shape[] shapes = {new Circle(1), new Square(2), new RightTriangle(3, 4)};
        assertEquals("Circle, Square, Right triangle", ShapeTools.listNames(shapes));
    }

    @Test
    void listNamesOfEmptyArrayIsEmpty() {
        assertEquals("", ShapeTools.listNames(new Shape[0]));
    }

    @Test
    void toolsRejectNullArrayOrNullShape() {
        assertThrows(IllegalArgumentException.class, () -> ShapeTools.totalArea(null));
        assertThrows(IllegalArgumentException.class, () -> ShapeTools.findLargest(new Shape[]{new Square(1), null}));
        assertThrows(IllegalArgumentException.class, () -> ShapeTools.listNames(null));
    }

    @Test
    void toolsWorkWithAnyKindOfShape() {
        // a shape type ShapeTools has never heard of - this works only through polymorphism
        Shape blob = new Shape("Blob") {
            @Override
            public double area() {
                return 100.0;
            }

            @Override
            public double perimeter() {
                return 40.0;
            }
        };
        Shape[] shapes = {new Square(2), blob};
        assertSame(blob, ShapeTools.findLargest(shapes));
        assertEquals("Square, Blob", ShapeTools.listNames(shapes));
    }
}

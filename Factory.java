// Step 1: Create Interface to implement (Shape) -> Must have a method in it.
// Step 2: Create enum to hold different implementations of the interface.(ShapeType)
// Step 3: Create relevant classes that implement the interface.
// Step 4: Create Factory, That has no constructor, a single public class,
//      Using switch-case to return the correct "new" Object.

enum ShapeType {
    CIRCLE, SQUARE, TRIANGLE;
}

interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing a Circle");
    }
}

class Square implements Shape {
    public void draw() {
        System.out.println("Drawing a Square");
    }
}

class Triangle implements Shape {
    public void draw() {
        System.out.println("Drawing a Triangle");
    }
}

public class Factory {
    public Shape getShape(ShapeType shapeType) {
        if (shapeType == null) {
            return null;
        }
        switch (shapeType) {
            case CIRCLE:
                return new Circle();
            case SQUARE:
                return new Square();
            case TRIANGLE:
                return new Triangle();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        // NOTICE: new Factory() -> .getShape();
        Shape shape = new Factory().getShape(ShapeType.CIRCLE);
        shape.draw();
    }
}

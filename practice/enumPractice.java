package practice;

public class enumPractice {
    enum ShapeType {
        CIRCLE("circle"),
        SQUARE("square");

        private final String shape;

        ShapeType(String shape) {
            this.shape = shape;
        }

        public String getShape() {
            return this.shape;
        }
    }

    public static void main(String[] args) {
        for (ShapeType shape : ShapeType.values()){
            System.out.println(shape);
            System.out.println(shape.getShape());
        }
    }
}

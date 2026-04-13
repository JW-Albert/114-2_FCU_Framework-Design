package org.example;

public class Main {

    public static void main(String[] args) {
        // 創建不同形狀的物件
        Shape[] shapes = {
                new Circle(),
                new Rectangle(),
                new Triangle()
        };

        // 使用 ShapeDrawer 繪製所有形狀
        ShapeDrawer drawer = new ShapeDrawer();

        for (Shape shape : shapes) {
            drawer.draw(shape);
        }
    }
}

// ============ Shape 介面 ============
interface Shape {
    void draw();
}

// ============ Circle 實現 ============
class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle");
    }
}

// ============ Rectangle 實現 ============
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Rectangle");
    }
}

// ============ Triangle 實現 ============
class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}

// ============ ShapeDrawer 類 ============
class ShapeDrawer {
    public void draw(Shape shape) {
        shape.draw();
    }
}
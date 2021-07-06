package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        Square s = new Square(5);
        System.out.println(s.area());

        Rectangle r = new Rectangle(4,6);
        System.out.println(r.area());

        Point p1 = new Point(-1,-2);
        Point p2 = new Point(4,4);
        //используем статический метод для рассчета расстояния
        System.out.println("Расстояние между точками = "+distance(p1,p2));

        //используем метод из класса Point
        System.out.println("Расстояние между точками по методу из класса = "+p1.distance(p2));

    }
    public static void hello(String somebody){
        System.out.println("Hello, "+somebody+" !!!!");
    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt ((p2.x-p1.x)*(p2.x-p1.x)+(p2.y- p1.y)*(p2.y- p1.y));
    }
}
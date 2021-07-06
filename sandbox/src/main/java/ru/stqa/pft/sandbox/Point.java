package ru.stqa.pft.sandbox;

public class Point {
    public int x,y;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
    public double distance(Point p){
        return Math.sqrt ((this.x-p.x)*(this.x-p.x)+(this.y- p.y)*(this.y- p.y));
    }
}

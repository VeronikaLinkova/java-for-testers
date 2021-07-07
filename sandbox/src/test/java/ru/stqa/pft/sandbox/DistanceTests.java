package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTests {
    @Test
    public void testDistanceWithRound(){
        Point p1 = new Point(4,5);
        Point p2 = new Point(0,0);
        Assert.assertEquals((Math.round((p1.distance(p2)*100.0)))/100.0 ,6.40);
    }
    @Test
    public void testDistanceWithoutRound(){
        Point p1 = new Point(-1,-1);
        Point p2 = new Point(2,2);
        Assert.assertEquals(p1.distance(p2),4.242640687119285);
    }
    @Test
    public void testDistance(){
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Assert.assertEquals(p1.distance(p2),0.0);
    }
}

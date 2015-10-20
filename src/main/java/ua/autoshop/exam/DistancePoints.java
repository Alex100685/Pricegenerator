package ua.autoshop.exam;

import java.util.List;

/**
 * Created by Пользователь on 20.10.2015.
 */
public class DistancePoints {

    public double getAverageDistanceOfThreePoints(Point firstPoint, Point secondPoint, Point thirdPoint){
        int pointQuantity = 3;
        double firstDistance = getDistance(firstPoint, secondPoint);
        double secondDistance = getDistance(firstPoint, thirdPoint);
        double thirdDistance = getDistance(secondPoint, thirdPoint);
        return (firstDistance+secondDistance+thirdDistance)/pointQuantity;
    }

    private double getDistance(Point firstPoint, Point secondPoint){
        return Math.sqrt(Math.pow((firstPoint.getxCoordinate()-secondPoint.getxCoordinate()),2)+(Math.pow((firstPoint.getyCoordinate()-secondPoint.getyCoordinate()),2)));
    }

    public static void main (String [] args){
        Point firstPoint = new Point(1.0,1.0);
        Point secondPoint = new Point(1.0,5.0);
        Point thirdPoint = new Point(5.0,1.0);
        DistancePoints dp = new DistancePoints();
        double result = dp.getAverageDistanceOfThreePoints(firstPoint, secondPoint, thirdPoint);
        System.out.println(result);

    }



}

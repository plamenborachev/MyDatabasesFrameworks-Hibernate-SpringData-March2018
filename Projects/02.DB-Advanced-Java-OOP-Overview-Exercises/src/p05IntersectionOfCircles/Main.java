package p05IntersectionOfCircles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstCircleData = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();
        int[] secondCircleData = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        Circle circle1 = new Circle(firstCircleData[0], firstCircleData[1], firstCircleData[2]);
        Circle circle2 = new Circle(secondCircleData[0], secondCircleData[1], secondCircleData[2]);

        if (Circle.intersect(circle1, circle2)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}

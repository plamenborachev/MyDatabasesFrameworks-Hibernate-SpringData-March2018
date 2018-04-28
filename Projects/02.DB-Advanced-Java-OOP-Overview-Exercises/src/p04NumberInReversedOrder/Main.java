package p04NumberInReversedOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        double num = Double.parseDouble(reader.readLine());
        DecimalNumber decimalNumber = new DecimalNumber(num);
        System.out.println(decimalNumber.numberInReversedOrder());
    }
}

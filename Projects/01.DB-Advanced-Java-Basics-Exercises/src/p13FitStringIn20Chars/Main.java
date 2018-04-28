package p13FitStringIn20Chars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        if (input.length() > 20){
            System.out.println(input.substring(0, 20));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(input);
            for (int i = 0; i < (20 - input.length()); i++) {
                sb.append("*");
            }
            System.out.println(sb.toString());
        }
    }
}

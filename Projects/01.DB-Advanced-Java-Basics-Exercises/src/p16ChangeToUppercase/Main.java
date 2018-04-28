package p16ChangeToUppercase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine();
        String pattern = "<upcase>(.+?)<\\/upcase>";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);

        while (matcher.find()){
            String toUpperCase = matcher.group(1).toUpperCase();
            text = text.replace(matcher.group(0), toUpperCase);
        }

        System.out.println(text);
    }
}

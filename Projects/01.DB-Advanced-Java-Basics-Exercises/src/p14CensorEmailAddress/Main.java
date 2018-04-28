package p14CensorEmailAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String email = reader.readLine();
        String text = reader.readLine();

        String[] emailTokens = email.split("@");
        String username = emailTokens[0];
        String domain = emailTokens[1];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < username.length(); i++) {
            sb.append("*");
        }
        sb.append("@").append(domain);

        System.out.println(text.replace(email, sb.toString()));
    }
}

package p15_URL_parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputTokens = reader.readLine().split("://");

        Map<String, String> urlParts = new LinkedHashMap<>();

        if (inputTokens.length == 1){
            urlParts.putIfAbsent("[protocol]", "");
            extractServerAndResource(inputTokens[0], urlParts);
        } else {
            urlParts.putIfAbsent("[protocol]", inputTokens[0]);
            extractServerAndResource(inputTokens[1], urlParts);
        }

        urlParts.entrySet().stream()
                .forEach(kvp -> System.out.println(String.format("%s = \"%s\"", kvp.getKey(), kvp.getValue())));
    }

    private static void extractServerAndResource(String inputToken, Map<String, String> urlParts) {
        String[] serverResource = inputToken.split("/");
        if (serverResource.length == 1){
            urlParts.putIfAbsent("[server]", serverResource[0]);
            urlParts.putIfAbsent("[resource]", "");
        } else {
            urlParts.putIfAbsent("[server]", serverResource[0]);
            urlParts.putIfAbsent("[resource]", inputToken.substring(inputToken.indexOf('/') + 1));
        }
    }
}

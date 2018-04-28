package p09MostFrequentNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        Map<Integer, Integer> numbersCount = new LinkedHashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (!numbersCount.containsKey(numbers[i])){
                numbersCount.put(numbers[i], 0);
            }
            numbersCount.put(numbers[i], numbersCount.get(numbers[i]) + 1);
        }

        numbersCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .forEach(kvp -> System.out.println(kvp.getKey()));
    }
}

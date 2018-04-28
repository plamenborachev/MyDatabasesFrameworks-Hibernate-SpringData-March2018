package p07MaxSequenceOfEqualElements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        List<Integer> currentSequence = new ArrayList<>();

        Map<Integer, Integer> sequences = new LinkedHashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            if (currentSequence.isEmpty() || currentSequence.get(0) == numbers[i]){
                currentSequence.add(numbers[i]);
            } else {
                if (!sequences.containsKey(currentSequence.get(0)) ||
                        sequences.get(currentSequence.get(0)) < currentSequence.size()){
                    sequences.put(currentSequence.get(0), currentSequence.size());
                }
                currentSequence.clear();
                currentSequence.add(numbers[i]);
            }
        }

        if (!sequences.containsKey(currentSequence.get(0)) ||
                sequences.get(currentSequence.get(0)) < currentSequence.size()){
            sequences.put(currentSequence.get(0), currentSequence.size());
        }

        sequences.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .forEach(kvp -> {
                    for (int i = 0; i < kvp.getValue(); i++) {
                        System.out.print(kvp.getKey() + " ");
                    }
                });
    }
}

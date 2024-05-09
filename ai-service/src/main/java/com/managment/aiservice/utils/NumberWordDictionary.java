package com.managment.aiservice.utils;

import java.util.HashMap;
import java.util.Map;

public class NumberWordDictionary {
    public static Long toNumber(String text) {
        Map<String, Long> dictionary = new HashMap<>();
        dictionary.put("zero", 0L);
        dictionary.put("one", 1L);
        dictionary.put("two", 2L);
        dictionary.put("three", 3L);
        dictionary.put("four", 4L);
        dictionary.put("five", 5L);
        dictionary.put("six", 6L);
        dictionary.put("seven", 7L);
        dictionary.put("eight", 8L);
        dictionary.put("nine", 9L);

        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (dictionary.containsKey(word.toLowerCase().replaceAll("[^a-zA-Z]", ""))) {
                result.append(dictionary.get(word.toLowerCase().replaceAll("[^a-zA-Z]", "")));
            }
        }
        long finalResult;
        try {
            finalResult = Long.parseLong(result.toString());
        } catch (NumberFormatException e) {
            finalResult = 0L;
        }

        return finalResult;
    }
}

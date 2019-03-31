package validator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class PolishIdValidator implements Validator {

    private static Map<Character, Integer> lettersToIntegerMap;
    private static final int[] weights = {7, 3, 1, 9, 7, 3, 1, 7, 3};

    public PolishIdValidator() {
        initializeMap();
    }

    @Override
    public boolean validate(String id) {
        if (!isIdCorrect(id)) {
            return false;
        }

        int[] ints = convertToIntArray(id.toCharArray());
        int checkSum = computeCheckSum(ints);
        return checkSum % 10 == 0;
    }

    private int computeCheckSum(int[] numbers) {
        return IntStream.range(0, numbers.length)
                .map(i -> numbers[i] * weights[i])
                .sum();
    }

    private int[] convertToIntArray(char[] charArray) {
        return IntStream.range(0, charArray.length)
                .map(i -> lettersToIntegerMap.get(charArray[i]))
                .toArray();
    }

    private boolean isIdCorrect(String id) {
        return id.matches("[A-Z]{3}\\d{6}");
    }

    private void initializeMap() {
        lettersToIntegerMap = new HashMap<>();
        int counter = 0;
        for (char digit = '0'; digit <= '9'; digit++) {
            lettersToIntegerMap.put(digit, counter);
            counter++;
        }

        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            lettersToIntegerMap.put(alphabet, counter);
            counter++;
        }
    }

}

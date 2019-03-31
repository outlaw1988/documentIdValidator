package validator;

import java.util.HashMap;
import java.util.Map;

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
        //System.out.println("Check sum is: " + checkSum);
        return checkSum % 10 == 0;
    }

    private int computeCheckSum(int[] numbers) {
        int result = 0;
        for (int i = 0; i < numbers.length; i++) {
            result += numbers[i] * weights[i];
        }
        return result;
    }

    private int[] convertToIntArray(char[] charArray) {
        int[] output = new int[9];

        for (int i = 0; i < 3; i++) {
            output[i] = lettersToIntegerMap.get(charArray[i]);
        }

        for (int i = 3; i < 9; i++) {
            output[i] = Character.getNumericValue(charArray[i]);
        }

        return output;
    }

    private boolean isIdCorrect(String id) {
        return id.matches("[A-Z]{3}\\d{6}");
    }

    private void initializeMap() {
        int counter = 10;
        lettersToIntegerMap = new HashMap<>();

        for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
            lettersToIntegerMap.put(alphabet, counter);
            counter++;
        }
    }

}

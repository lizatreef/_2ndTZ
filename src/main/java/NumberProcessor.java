
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NumberProcessor {
    public static int[] readNumbersFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String input = scanner.nextLine();
        scanner.close();

        String[] numbersStr = input.split(" ");
        int[] numbers = new int[numbersStr.length];
        for (int i = 0; i < numbersStr.length; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i]);
        }
        return numbers;
    }

    public static int findMin(int[] numbers) {
        int min = Integer.MAX_VALUE;
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static int findMax(int[] numbers) {
        int max = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static int calculateSum(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static int calculateProduct(int[] numbers) {
        int product = 1;
        for (int num : numbers) {
            if (product > Integer.MAX_VALUE / num) {
                throw new ArithmeticException("Integer overflow");
            }
            product *= num;
        }
        return product;
    }
}

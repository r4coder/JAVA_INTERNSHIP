import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double result, nextValue;
        char operator;
        int totalSteps;

        System.out.print("How many values do you want to enter? ");
        totalSteps = input.nextInt();

        if (totalSteps < 2) {
            System.out.println("Need at least two values to perform operations.");
            return;
        }

        System.out.print("Enter value 1: ");
        result = input.nextDouble();

        for (int i = 2; i <= totalSteps; i++) {
            System.out.print("Enter operator for value " + i + " (+, -, *, /): ");
            operator = input.next().charAt(0);

            System.out.print("Enter value " + i + ": ");
            nextValue = input.nextDouble();

            switch (operator) {
                case '+':
                    result += nextValue;
                    break;
                case '-':
                    result -= nextValue;
                    break;
                case '*':
                    result *= nextValue;
                    break;
                case '/':
                    if (nextValue == 0) {
                        System.out.println("Error: Division by zero");
                        return;
                    }
                    result /= nextValue;
                    break;
                default:
                    System.out.println("Invalid operator");
                    return;
            }
        }

        System.out.println("Final Result: " + result);
    }
}

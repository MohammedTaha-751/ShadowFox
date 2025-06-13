package calculator;

import java.util.Scanner;

public class EnhancedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        double a, b, result;

        do {
            System.out.println("\n====== Enhanced Calculator ======");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Temperature Conversion");
            System.out.println("8. Currency Conversion");
            System.out.println("9. Exit");
            System.out.print("Choose an operation: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter first number: ");
                    a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    b = scanner.nextDouble();
                    result = a + b;
                    System.out.println("Result: " + result);
                    break;
                case 2:
                    System.out.print("Enter first number: ");
                    a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    b = scanner.nextDouble();
                    result = a - b;
                    System.out.println("Result: " + result);
                    break;
                case 3:
                    System.out.print("Enter first number: ");
                    a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    b = scanner.nextDouble();
                    result = a * b;
                    System.out.println("Result: " + result);
                    break;
                case 4:
                    System.out.print("Enter dividend: ");
                    a = scanner.nextDouble();
                    System.out.print("Enter divisor: ");
                    b = scanner.nextDouble();
                    if (b != 0) {
                        result = a / b;
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Cannot divide by zero.");
                    }
                    break;
                case 5:
                    System.out.print("Enter number: ");
                    a = scanner.nextDouble();
                    if (a >= 0) {
                        result = Math.sqrt(a);
                        System.out.println("Square root: " + result);
                    } else {
                        System.out.println("Error: Cannot find square root of negative number.");
                    }
                    break;
                case 6:
                    System.out.print("Enter base: ");
                    a = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    b = scanner.nextDouble();
                    result = Math.pow(a, b);
                    System.out.println("Result: " + result);
                    break;
                case 7:
                    System.out.println("1. Celsius to Fahrenheit");
                    System.out.println("2. Fahrenheit to Celsius");
                    int tempChoice = scanner.nextInt();
                    System.out.print("Enter temperature: ");
                    double temp = scanner.nextDouble();

                    if (tempChoice == 1) {
                        result = (temp * 9 / 5) + 32;
                        System.out.println("Temperature in Fahrenheit: " + result);
                    } else if (tempChoice == 2) {
                        result = (temp - 32) * 5 / 9;
                        System.out.println("Temperature in Celsius: " + result);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 8:
                    System.out.println("1. INR to USD");
                    System.out.println("2. USD to INR");
                    int currencyChoice = scanner.nextInt();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();

                    final double exchangeRate = 83.0; // Example rate

                    if (currencyChoice == 1) {
                        result = amount / exchangeRate;
                        System.out.println("USD: " + result);
                    } else if (currencyChoice == 2) {
                        result = amount * exchangeRate;
                        System.out.println("INR: " + result);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 9);

        scanner.close();
    }
}

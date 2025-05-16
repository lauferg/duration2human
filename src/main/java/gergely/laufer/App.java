package gergely.laufer;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String duration = "";

        while (true) {
            System.out.print("Please input a duration in seconds as an integer or press [Q] to quit: ");
            duration = inputScanner.nextLine();

            if (!duration.matches("\\d+") && !duration.equalsIgnoreCase("q")) {
                System.out.println("Unable to recognize that input as a number. Please input a number without thousands separators!");
            } else if (duration.equalsIgnoreCase("q")) {
                System.exit(1);
            } else {
                long seconds = Long.parseLong(duration);
                String msg = String.format("Converting the duration of [%d] seconds into human readable form...", seconds);
                System.out.println(msg);
                String conversionResults = Duration2Human.toHumanReadable(seconds);
                msg = String.format("An interval of %,d seconds is %s.", seconds, conversionResults);
                System.out.println(msg);
            }
        }

    }
}

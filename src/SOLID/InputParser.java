package SOLID;

import java.util.Scanner;

public class InputParser {

    public String readLoggerInfo(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        StringBuilder stringBuilder = new StringBuilder();

        while (n-- > 0) {
            stringBuilder.append(scanner.nextLine()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}

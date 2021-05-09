package task2_oop;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InOut {

    public String read() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the expression");
        String input = scanner.nextLine();
        return validate(input);
    }

    public void write (int out) {
        System.out.println(out);
    }

    public void write (String out) {
        System.out.println(out);
    }

    private String validate(String input) {
        input = input.replaceAll("\\s+", "");

        if (!input.matches("^[0-9//(//)//+//*/////-]*$")) {
            throw new RuntimeException("Expression is not valid. " + "Only 0-9, (, ), " + "+, -, *, / are allowed.");
        }

        int openingBraceQty = countSymbolOccurrences(input, "\\(");
        int closingBraceQty = countSymbolOccurrences(input, "\\)");

        if (closingBraceQty != openingBraceQty) {
            throw new RuntimeException("Expression is not valid. " + "\"(\" quantity " + "is" + " " + closingBraceQty + " while \")\" quantity is " + openingBraceQty);
        }

        return input;
    }

    private int countSymbolOccurrences(String input, String s) {
        Pattern openingBracePattern = Pattern.compile(s);
        Matcher openingBraceMatcher = openingBracePattern.matcher(input);
        int openingBraceQty = 0;
        while (openingBraceMatcher.find()) {
            openingBraceQty++;
        }
        return openingBraceQty;
    }
}

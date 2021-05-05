package task1_Dijkstra;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dijkstra {

    static String highPriority = "*/";
    static String lowPriority = "+-";

    public static void main(String[] args) {
        String input = getInput();
        input = validateInput(input);
        String output = sortingStation(input);
        System.out.println(output);
        System.out.println(getResultFromRPN(output));
    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write the expression");
        return scanner.nextLine();
    }

    private static String validateInput(String input) {
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

    private static int countSymbolOccurrences(String input, String s) {
        Pattern openingBracePattern = Pattern.compile(s);
        Matcher openingBraceMatcher = openingBracePattern.matcher(input);
        int openingBraceQty = 0;
        while (openingBraceMatcher.find()) {
            openingBraceQty++;
        }
        return openingBraceQty;
    }

    private static String sortingStation(String input) {
        String stack = new String();
        String out = new String();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);

            if (Character.isDigit(current)) {
                out += current;
            } else if (isUnaryPlus(input, i)) {
                //do not track unary plus
            } else if (isUnaryMinus(input, i)) {
                current = input.charAt(++i);
                out += "0" + current + "-";
            } else if (Character.valueOf('(').equals(current)) {
                stack += current;
            } else if (Character.valueOf(')').equals(current)) {
                for (int j = stack.length() - 1; j >= 0; j--) {
                    char charInStack = stack.charAt(j);
                    stack = stack.substring(0, stack.length() - 1);
                    if (Character.valueOf('(').equals(charInStack)) {
//                        j = -1;
                        break;
                    } else {
                        out += charInStack;
                    }
                }
            } else if (isBinaryOperation(current)) {
                while (stack.length() > 0) {
                    if ((stackHasHigherOrSamePriority(current, stack))) {
                        char lastInStack = stack.charAt(stack.length() - 1);
                        out += lastInStack;
                        stack = stack.substring(0, stack.length() - 1);
                    } else {
                        break;
                    }
                }
                stack += current;
            }
        }

        if (stack.length() > 0) {
            out = pushStackToOut(stack, out);
        }
        return out;
    }

    private static boolean isUnaryPlus(String string, int i) {
        // it is "+", it is the first symbol or goes after "("
        if (Character.valueOf('+').equals(string.charAt(i))) {
            if ((i == 0) || (i > 0) && Character.valueOf('(').equals(string.charAt(i - 1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isUnaryMinus(String string, int i) {
        // it is "-", it is the first symbol or goes after "("
        if (Character.valueOf('-').equals(string.charAt(i))) {
            if ((i == 0) || (i > 0) && Character.valueOf('(').equals(string.charAt(i - 1))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBinaryOperation(char c) {
        return (lowPriority.indexOf(c) > -1 || highPriority.indexOf(c) > -1);
    }

    private static boolean stackHasHigherOrSamePriority(char current, String stack) {
        char lastInStack = stack.charAt(stack.length() - 1);
        if (highPriority.indexOf(lastInStack) > -1) {
            return true;
        }
        if (lowPriority.indexOf(lastInStack) > -1 && lowPriority.indexOf(current) > -1) {
            return true;
        }
        return false;
    }

    private static String pushStackToOut(String stack, String out) {
        for (int i = stack.length() - 1; i >= 0; i--) {
            out += stack.charAt(i);
        }
        return out;
    }

    private static int getResultFromRPN(String input) {
        List<Integer> stack = new ArrayList<>();
        char[] charArray = input.toCharArray();
        for (char c : charArray) {
            if (highPriority.indexOf(c) > -1 || lowPriority.indexOf(c) > -1) {
                int first = stack.get(stack.size() - 2);
                int second = stack.get(stack.size() - 1);
                char operation = c;
                stack.remove(stack.size() - 1);
                stack.remove(stack.size() - 1);

                stack.add(count(first, second, operation));

            } else {
                stack.add(Character.getNumericValue(c));
            }
        }
        return stack.get(0);
    }

    private static int count(int first, int second, char operation) {
        if (Character.valueOf('+').equals(operation)) {
            return first + second;
        } else if (Character.valueOf('-').equals(operation)) {
            return first - second;
        } else if (Character.valueOf('*').equals(operation)) {
            return first * second;
        } else if (Character.valueOf('/').equals(operation)) {
            return first / second;
        }
        throw new RuntimeException("Unsupported operation " + operation);
    }
}
